package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F01_add_Todo_item {

  private static final String BASE_URL = "http://localhost:4567/";

  // Normal flow

  // When in TodoCommonStepDefinitions.java:

  @Then("a todo item with a title {string}, a complete {string}, an active {string}, and a description {string} is added")
  public void thenAddTodo(String title, String complete, String active, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "todos"))
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

  @When("a student removes a todo with id")
  public void whenRemoveTodo() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("todos/%s", TodoCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .DELETE()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);

  }

  @When("a student adds a todo item with a title {string}, an invalid complete {string}, an active {string}, and a description {string}")
  public void whenAddTodoInvalid(String title,
      String complete, String active, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "todos"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"title\": \"" + title
                + "\", \"completed\": " + complete + ", \"active\": " + active
                + ", \"description\": \"" + description + "\"}"))
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 400);
    TodoCommonStepDefinitions.setResponse(response);
  }

  @Then("the todo item will not be added")
  public void thenProjectNotAdded() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "todos"))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertFalse(response.body().contains("G5"));

  }

}
