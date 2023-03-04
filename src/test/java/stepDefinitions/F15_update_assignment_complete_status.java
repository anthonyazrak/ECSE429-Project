package stepDefinitions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import io.cucumber.java.en.When;

public class F15_update_assignment_complete_status {
  private static final String BASE_URL = "http://localhost:4567/";

  @When("a student edits a project status with id {string} and complete status {string}")
  public void whenEditProjectStatus(String id, String complete) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + id))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(
            "{\"completed\": " + complete + "}"))
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);
  }

  @When("a student edits a project status with id and complete status {string}")
  public void whenEditProjectStatus(String complete) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + AssignmentCommonStepDefinitions.getGivenId()))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(
            "{\"completed\": " + complete + "}"))
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);
  }

  @When("a student edits a project complete status with id {string} and invalid complete status {string}")
  public void whenEditProjectStatusInvalid(String id, String complete) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + id))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(
            "{\"completed\": " + complete + "}"))
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);
  }
}
