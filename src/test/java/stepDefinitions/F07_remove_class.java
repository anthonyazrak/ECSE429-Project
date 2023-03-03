package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F07_remove_class {

    private final String BASE_URL = "http://localhost:4567/";

    @Then("a category with title {string} and description {string} will be deleted")
    public void a_category_with_title_and_description_will_be_deleted(String title, String description)
            throws Exception {
        assertFalse(ClassCommonStepDefinition.getResponse().body().contains(title));
        assertFalse(ClassCommonStepDefinition.getResponse().body().contains(description));
    }

    @When("a student deletes a category with id {int}")
    public void a_student_deletes_a_category_with_id(int zero) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        BASE_URL + "categories/" + zero))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        ClassCommonStepDefinition.setResponse(response);
    }
}
