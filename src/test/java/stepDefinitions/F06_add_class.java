package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import io.cucumber.java.en.Then;

public class F06_add_class {

    private final String BASE_URL = "http://localhost:4567/";

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

}
