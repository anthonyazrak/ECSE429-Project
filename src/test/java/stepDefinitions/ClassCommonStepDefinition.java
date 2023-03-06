package stepDefinitions;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClassCommonStepDefinition {

    private final String BASE_URL = "http://localhost:4567/";

    private static int givenId = 0;

    public static int getGivenId() {
        return givenId;
    }

    private static HttpResponse<String> response;

    public static HttpResponse<String> getResponse() {
        return response;
    }

    public static void setResponse(HttpResponse<String> newResponse) {
        response = newResponse;
    }

    @Given("the following category exists in the system")
    public void the_following_category_exists_in_the_system(io.cucumber.datatable.DataTable dataTable)
            throws Exception {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String title = row.get("title");
            String description = row.get("description");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "categories"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{\"title\": \"" + title + "\", \"description\": \"" + description + "\"}  "))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());

            givenId = jsonNode.get("id").asInt();
        }
    }

    @When("a student deletes a category with id of the existing category")
    public void a_student_deletes_a_category_with_id_of_the_existing() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        BASE_URL + "categories/" + String.format("%s", givenId)))
                .DELETE()
                .build();

        response = client.send(request, BodyHandlers.ofString());
    }

    @When("a student adds a category with a title {string} and a description {string}")
    public void a_student_adds_a_category_with_a_title_and_a_description(String title, String description)
            throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "categories"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"title\": \"" + title + "\", \"description\": \"" + description + "\"}  "))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        response = client.send(request, BodyHandlers.ofString());

        if (title.equals("")) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());

        givenId = jsonNode.get("id").asInt();
    }

    @Then("the response status code will be 400")
    public void the_response_status_code_will_be_400() {
        assertEquals(400, response.statusCode());
    }

    @Then("the response status code will be 404")
    public void the_response_status_code_will_be_404() {
        assertEquals(404, response.statusCode());
    }

}
