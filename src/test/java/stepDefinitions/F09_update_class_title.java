package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class F09_update_class_title {

    private final String BASE_URL = "http://localhost:4567/";

    @When("a student updates the existing category with new title {string}")
    public void a_student_updates_the_existing_category_with_new_title(String title) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "categories/" + ClassCommonStepDefinition.getGivenId()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"title\": \"" + title + "\"}"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        ClassCommonStepDefinition.setResponse(response);
    }

    @Then("the category will have updated title {string} and keep description {string}")
    public void the_category_will_have_updated_title_and_keep_description(String title, String description)
            throws Exception {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "categories/" + ClassCommonStepDefinition.getGivenId()))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
        assertTrue(getResponse.body().contains(title));
        assertTrue(getResponse.body().contains(description));
    }
}
