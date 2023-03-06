package stepDefinitions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.When;

public class F05_update_todo_complete_status {
        private static final String BASE_URL = "http://localhost:4567/";

        @When("a student edits a todo item status with id {string} and doneStatus {string}")
        public void whenEditTodoStatus(String id, String doneStatus) throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "todos/" + String.format("%s", id)))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                                "{\"doneStatus\": " + doneStatus + "}"))
                                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                TodoCommonStepDefinitions.setResponse(response);
                System.out.println(response);

        }

        @When("a student edits a todo item status with id and doneStatus {string}")
        public void whenEditTodoStatus(String doneStatus) throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "todos/" + TodoCommonStepDefinitions.getGivenId()))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                                "{\"doneStatus\": " + doneStatus + "}"))
                                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                TodoCommonStepDefinitions.setResponse(response);
        }

        @When("a student edits a todo item doneStatus with id {string} and invalid doneStatus {string}")
        public void whenEditTodoStatusInvalid(String id, String doneStatus) throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "todos/" + id))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                                "{\"doneStatus\": \"" + doneStatus + "\"}"))
                                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                TodoCommonStepDefinitions.setResponse(response);
        }
}
