package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F08_view_specific_class {

    private final String BASE_URL = "http://localhost:4567/";

    @When("a student views a category with id of the existing category")
    public void a_student_views_a_category_with_id_of_the_existing_category() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        BASE_URL + "categories/" + ClassCommonStepDefinition.getGivenId()))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        ClassCommonStepDefinition.setResponse(response);
    }
}
