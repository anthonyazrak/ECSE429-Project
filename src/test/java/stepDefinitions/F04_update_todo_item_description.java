package stepDefinitions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.When;

public class F04_update_todo_item_description {

    private static final String BASE_URL = "http://localhost:4567/";

    @When("a student edits a todo item description with id {string} and description {string}")
    public void whenEditTodoDescription(String id, String description) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + String.format("%s", id)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"description\": \"" + description + "\"}"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);

    }

    @When("a student edits a todo item description with id and description {string}")
    public void whenEditTodoDescription(String description) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + String.format("%s", TodoCommonStepDefinitions.getGivenId())))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"description\": \"" + description + "\"}"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);

    }

    @When("a student edits a todo item description with invalid id {int} and description {string}")
    public void whenEditTodoDescriptionInvalidId(int id, String description) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + id))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"description\": \"" + description + "\"}"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);

    }

}
