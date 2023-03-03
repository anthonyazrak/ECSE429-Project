package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.Given;

public class CommonStepDefinitions {

  private Process runTodoManagerRestApi;

  @Given("the server is running")
  public void the_server_is_running() throws InterruptedException {
    try {
      runTodoManagerRestApi = Runtime.getRuntime().exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Make sure application is running
    System.out.println("Starting tests in...\n");
    for (int i = 3; i > 0; i--) {
      System.out.println(i);
      Thread.sleep(100);
    }
  }
}