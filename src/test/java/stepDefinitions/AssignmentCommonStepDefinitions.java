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

public class AssignmentCommonStepDefinitions {

  private static final String BASE_URL = "http://localhost:4567/";

  private static int givenId = 0;
  private static HttpResponse<String> response;

  public static int getGivenId() {
    return givenId;
  }

  public static void setResponse(HttpResponse<String> newResponse) {
    response = newResponse;
  }

  public static HttpResponse<String> getResponse() {
    return response;
  }

  @When("a student adds a project with a title {string}, a complete {string}, an active {string}, and a description {string}")
  public void whenAddProject(String title,
      String complete, String active, String description) throws Exception {
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

  @Then("the student will get notified by an error message")
  public void thenErrorMessage() throws Exception {
    assertNotNull(response.body());
  }

  @Then("the project with id {string} has description {string}")
  public void thenEditProjectDescription(String id, String description) throws Exception {

    assertTrue(response.body().contains(id));
    assertTrue(response.body().contains(description));
  }

  @Then("the project with id has description {string}")
  public void thenEditProjectDescription(String description) throws Exception {

    assertTrue(response.body().contains(Integer.toString(givenId)));
    assertTrue(response.body().contains(description));
  }

}
