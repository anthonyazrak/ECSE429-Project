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

public class F14_update_assignment_description {

  private static final String BASE_URL = "http://localhost:4567/";

  @When("a student edits a project description with id {string} and description {string}")
  public void whenEditProjectDescription(String id, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + String.format("%s", id)))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"description\": \"" + description + "\"}"))
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);

  }

  @When("a student edits a project description with id and description {string}")
  public void whenEditProjectDescription(String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + String.format("%s", AssignmentCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(
            "{\"description\": \"" + description + "\"}"))
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);

  }

  @When("a student edits a project description with invalid id {int} and description {string}")
  public void whenEditProjectDescriptionInvalidId(int id, String description) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + id))
        .header("Content-Type", "application/json")
        .PUT(HttpRequest.BodyPublishers.ofString(
            "{\"description\": \"" + description + "\"}"))
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);

  }

}
