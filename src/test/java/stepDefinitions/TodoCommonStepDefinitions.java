package stepDefinitions;

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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TodoCommonStepDefinitions {

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

  @When("a student adds a todo item with a title {string}, a doneStatus {string}, and a description {string}")
  public void whenAddTodo(String title, String doneStatus, String description) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "todos"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"title\": \"" + title + "\", \"doneStatus\": " + doneStatus
                + ", \"description\": \"" + description + "\"}"))
        .build();

    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(response.body());
    givenId = jsonNode.get("id").asInt();
  }

  @Given("the following todo item exists in the system:")
  public void givenTodoExists(io.cucumber.datatable.DataTable dataTable) throws Exception {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String title = row.get("title");
      String doneStatus = row.get("doneStatus");
      String description = row.get("description");

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(BASE_URL + "todos"))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(
              "{\"title\": \"" + title + "\", \"doneStatus\": " + doneStatus
                  + ", \"description\": \"" + description + "\"}"))
          .build();
      HttpClient client = HttpClient.newHttpClient();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(response.body());
      givenId = jsonNode.get("id").asInt();

    }
  }

  @Then("the todo item with id {string} has description {string}")
  public void thenEditTodoDescription(String id, String description) throws Exception {

    assertTrue(response.body().contains(id));
    assertTrue(response.body().contains(description));
  }

  @Then("the todo item with id has description {string}")
  public void thenEditTodoDescription(String description) throws Exception {

    assertTrue(response.body().contains(Integer.toString(givenId)));
    assertTrue(response.body().contains(description));
  }

  @Then("the todo item with id {string} has doneStatus {string}")
  public void thenEditTodoCompleteStatus(String id, String doneStatus) throws Exception {

    assertTrue(response.body().contains(id));
    assertTrue(response.body().contains(doneStatus));
  }

  @Then("the todo item with id has doneStatus {string}")
  public void thenEditCompleteStatus(String doneStatus) throws Exception {

    assertTrue(response.body().contains(Integer.toString(givenId)));
    assertTrue(response.body().contains(doneStatus));
  }

}