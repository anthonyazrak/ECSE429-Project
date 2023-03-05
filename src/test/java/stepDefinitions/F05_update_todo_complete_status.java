package stepDefinitions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.When;

public class F05_update_todo_complete_status {
    private static final String BASE_URL = "http://localhost:4567/";

    @When("a student edits a todo item status with id {string} and complete status {string}")
    public void whenEditTodoStatus(String id, String complete) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(
                        "{\"completed\": " + complete + "}"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);
    }

    @When("a student edits a todo item status with id and complete status {string}")
    public void whenEditTodoStatus(String complete) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + TodoCommonStepDefinitions.getGivenId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(
                        "{\"completed\": " + complete + "}"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);
    }

    @When("a student edits a todo item complete status with id {string} and invalid complete status {string}")
    public void whenEditTodoStatusInvalid(String id, String complete) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "todos/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(
                        "{\"completed\": " + complete + "}"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        TodoCommonStepDefinitions.setResponse(response);
    }
}
