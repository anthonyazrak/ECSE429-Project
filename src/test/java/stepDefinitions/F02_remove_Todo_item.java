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

public class F02_remove_Todo_item {
  private static final String BASE_URL = "http://localhost:4567/";

  @Then("a todo item with id is removed")
  public void thenRemoveTodo() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("todos/%s", TodoCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    assertEquals(404, response.statusCode());
    assertFalse(response.body().contains(Integer.toString(TodoCommonStepDefinitions.getGivenId())));
  }

  @When("a student removes a todo item with invalid id {string}")
  public void whenRemoveTodoInvalidId(String id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + String.format("todos/%s", id)))
        .header("Content-Type", "application/json")
        .DELETE()
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 404);
    TodoCommonStepDefinitions.setResponse(response);

  }
}
