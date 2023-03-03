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
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F11_add_assignment {

  private static final String BASE_URL = "http://localhost:4567/";

  // Normal flow

  // When in AssignmentCommonStepDefinitions.java:

  @Then("a project with a title {string}, a complete {string}, an active {string}, and a description {string} is added")
  public void thenAddProject(String title, String complete, String active, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects"))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    assertEquals(200, response.statusCode());
    assertTrue(response.body().contains(title));
    assertTrue(response.body().contains(complete));
    assertTrue(response.body().contains(active));
    assertTrue(response.body().contains(description));

  }

  // Alternate flow

  // Given in AssignmentCommonStepDefinitions.java:

  @When("a student removes a project with id")
  public void whenRemoveProject() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("projects/%s", AssignmentCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .DELETE()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);

  }

  // And, Then are covered by previous step

  // Error flow

  // When is covered by previous step
  @When("a student adds a project with a title {string}, an invalid complete {string}, an active {string}, and a description {string}")
  public void whenAddProjectInvalid(String title,
      String complete, String active, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"title\": \"" + title
                + "\", \"completed\": " + complete + ", \"active\": " + active
                + ", \"description\": \"" + description + "\"}"))
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 400);
    AssignmentCommonStepDefinitions.setResponse(response);
  }

  @Then("the project will not be added")
  public void thenProjectNotAdded() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects"))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertFalse(response.body().contains("G5"));

  }

}