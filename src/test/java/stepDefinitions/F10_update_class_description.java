package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class F10_update_class_description {

        private final String BASE_URL = "http://localhost:4567/";

        @When("a student updates the existing category with new description {string}")
        public void a_student_updates_the_existing_category_with_new_description(String description) throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "categories/" + ClassCommonStepDefinition.getGivenId()))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                                "{\"description\": \"" + description + "\"}"))
                                .build();

                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                ClassCommonStepDefinition.setResponse(response);
        }

        @Then("the category will have updated description {string} and keep title {string}")
        public void the_category_will_have_updated_description_and_keep_title(String description, String title)
                        throws Exception {
                HttpRequest getRequest = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "categories/" + ClassCommonStepDefinition.getGivenId()))
                                .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
                assertTrue(getResponse.body().contains(title));
                assertTrue(getResponse.body().contains(description));
        }

        @When("a student updates category with id {int} with description {string}")
        public void a_student_updates_category_with_id_0_with_description(int zero, String description)
                        throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "categories/" + zero))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                                "{\"description\": \"" + description + "\"}"))
                                .build();

                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                ClassCommonStepDefinition.setResponse(response);
        }
}
