package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.core.exception.ExceptionUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F06_add_class {

    private final String BASE_URL = "http://localhost:4567/";

    private int givenId = 0;

    private HttpResponse<String> response;

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
    }

    @Then("a category with title {string} and description {string} will be added")
    public void a_category_with_title_and_description_will_be_added(String title, String description) throws Exception {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "categories"))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
        assertTrue(getResponse.body().contains(title));
        assertTrue(getResponse.body().contains(description));
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
                .uri(URI.create(BASE_URL + "categories/" + String.format("%s", givenId)))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    }

    @Then("the student will get notified by an error message")
    public void the_student_will_get_notified_by_an_error_message() {
        assertNotNull(response.body());
    }

    @Then("the response status code will be 400")
    public void the_response_status_code_will_be_400() {
        assertEquals(400, response.statusCode());
    }
}
