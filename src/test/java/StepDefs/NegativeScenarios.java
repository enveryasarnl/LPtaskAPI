package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NegativeScenarios {

    private Response response;
    private RequestSpecification request;
    private String usersEndpoint = ConfigurationReader.getProperty("usersEndpoint");
    private ValidatableResponse json;

    @Given("^call the endpoint with nonexistent shelf ID$")
    public void call_the_endpoint_with_nonexistent_shelf_ID() {
     request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
     response = request.when().get(usersEndpoint+"/102095640414510446878/bookshelves/1003");
    }

    @Then("^the response status code for nonexistent shelf request is (\\d+)$")
    public void the_response_status_code_for_nonexistent_shelf_request_is(Integer statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @Then("^the message should be \"([^\"]*)\"$")
    public void the_message_should_be(String expectedMessage) {
        json = response.then().body("error.message",equalTo(expectedMessage));
    }

    @Given("^call the endpoint with invalid userID$")
    public void call_the_endpoint_with_invalid_userID() {
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(usersEndpoint+"/102095640414510446/bookshelves/1001");

    }

    @Then("^the status code for invalid userID request is (\\d+)$")
    public void the_status_code_for_invalid_userID_request_is(Integer statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @Then("^the received message is \"([^\"]*)\"$")
    public void the_received_message_is(String expectedMessage) {
        json = response.then().body("error.message",equalTo(expectedMessage));
    }


}
