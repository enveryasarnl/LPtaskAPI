package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class GetShelf {
    private Response response;
    private RequestSpecification request;
    private String endpointVolumes = ConfigurationReader.getProperty("endpointVolumes");
    private ValidatableResponse json;

    @Given("^shelves exists with an id of (\\d+)$")
    public void shelvesExistsWithAnIdOf(int id){
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
    }

    @When("^a user retrieves the shelf by id$")
    public void aUserRetrievesTheShelfById(){
        response = request.when().get(endpointVolumes);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("^the status code for shelf is (\\d+)$")
    public void theStatusCodeForShelfIs(int statusCode) {
        json = response.then().statusCode(statusCode);

    }

    @Then("^response for shelf includes the following$")
    public void responseForShelfIncludesTheFollowing(Map<String,String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

}
