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

public class F13_view_specific_assignment {
  private static final String BASE_URL = "http://localhost:4567/";

  @When("a student views a project with id")
  public void whenViewProject() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + String.format("%s", AssignmentCommonStepDefinitions.getGivenId())))
        .header("Content-Type", "application/json")
        .GET()
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    AssignmentCommonStepDefinitions.setResponse(response);
  }

  @Then("the system displays a project with title {string}, complete {string}, active {string}, and description {string}")
  public void thenDisplayProject(String title, String complete, String active, String description) throws Exception {
    HttpResponse<String> response = AssignmentCommonStepDefinitions.getResponse();

    assertEquals(200, response.statusCode());
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.body());
    assertTrue(response.body().contains(title));
    assertTrue(response.body().contains(complete));
    assertTrue(response.body().contains(active));
    assertTrue(response.body().contains(description));

  }

  @When("a student views a project with invalid id {int}")
  public void whenViewProjectInvalidId(int id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL + "projects/" + id))
        .header("Content-Type", "application/json")
        .GET()
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 404);
    AssignmentCommonStepDefinitions.setResponse(response);
  }

}