package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F03_view_specific_todo_item {
    private static final String BASE_URL = "http://localhost:4567/";

    @When("a student views a todo item with id")
    public void whenViewTodo() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + String.format("%s", TodoCommonStepDefinitions.getGivenId())))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);
    }

    @Then("the system displays a todo item with title {string}, complete {string}, active {string}, and description {string}")
    public void thenDisplayTodo(String title, String complete, String active, String description) throws Exception {
        HttpResponse<String> response = TodoCommonStepDefinitions.getResponse();

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains(title));
        assertTrue(response.body().contains(complete));
        assertTrue(response.body().contains(active));
        assertTrue(response.body().contains(description));

    }

    @When("a student views a todo item with invalid id {int}")
    public void whenViewTodoInvalidId(int id) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + id))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        assertEquals(response.statusCode(), 404);
        TodoCommonStepDefinitions.setResponse(response);
    }

}