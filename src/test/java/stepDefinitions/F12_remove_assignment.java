package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F12_remove_assignment {
  private static final String BASE_URL = "http://localhost:4567/";
  private HttpResponse<String> response;

  @Then("a project with id {string} is removed")
  public void thenRemoveProject(String id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("projects/%s", AssignmentCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    assertEquals(404, response.statusCode());
    assertFalse(response.body().contains(Integer.toString(AssignmentCommonStepDefinitions.getGivenId())));
  }

  @When("a student removes a project with invalid id {string}")
  public void whenRemoveProjectInvalidId(String id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("projects/%s", id)))
        .header("Content-Type", "application/json")
        .DELETE()
        .build();

    response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 404);

  }
}
