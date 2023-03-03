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

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F11_add_assignment {
  private static final String BASE_URL = "http://localhost:4567/";
  private int givenId = 0;
  private HttpResponse<String> response;

  @ParameterType(value = "true|True|TRUE|false|False|FALSE")
  public Boolean booleanValue(String value) {
    return Boolean.valueOf(value);
  }

  // Normal flow

  @When("a student adds a project with a title {string}, a complete {string}, an active {string}, and a description {string}")
  public void whenAddAssignment(String title,
      String complete, String active, String description) throws Exception {
    HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"title\": \"" + title
                + "\", \"completed\": " + complete + ", \"active\": " + active
                + ", \"description\": \"" + description + "\"}"))
        .build();

  }

  @Then("a project with a title {string}, a complete {string}, an active {string}, and a description {string} is added")
  public void thenAddAssignment(String title, String complete, String active, String description) throws Exception {
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

  @Given("the following project exists in the system:")
  public void givenProjectExists(io.cucumber.datatable.DataTable dataTable) throws Exception {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String title = row.get("title");
      String complete = row.get("complete");
      String active = row.get("active");
      String description = row.get("description");

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(BASE_URL + "projects"))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(
              "{\"title\": \"" + title
                  + "\", \"completed\": " + complete + ", \"active\": " + active
                  + ", \"description\": \"" + description + "\"}"))
          .build();
      HttpClient client = HttpClient.newHttpClient();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(response.body());
      givenId = jsonNode.get("id").asInt();

    }

  }

  @When("a student removes a project with id {string}")
  public void whenRemoveAssignment(String id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("projects/%s", givenId)))
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
  public void whenAddAssignmentInvalid(String title,
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

    response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 400);

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

  @Then("the student will get notified by an error message")
  public void thenErrorMessage() throws Exception {
    assertNotNull(response.body());
  }

}