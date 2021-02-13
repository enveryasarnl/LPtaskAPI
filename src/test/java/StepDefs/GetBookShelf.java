package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBookShelf {
    private Response response;
    private RequestSpecification request;
    private String usersEndpoint = ConfigurationReader.getProperty("usersEndpoint");
    private ValidatableResponse json;

    @Given("^call the endpoint with (\\d+) shelf ID$")
    public void GivenCallTheEndpointWithUserIDAndShelfID(int shelfID)
    {
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(usersEndpoint+"/102095640414510446878/bookshelves/"+shelfID);

    }
    @Then("^the response status code for shelf is (\\d+)$")
    public void theResponseStatusCodeForShelfIs(int statusCode) {
        json = response.then().statusCode(statusCode);

    }

    @Then("^the response header \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void ThenTheHeaderShouldBe(String header, String headerValue)
    {
        json = response.then().header(header,headerValue);
    }

    @Then("^the shelf title should be \"([^\"]*)\"$")
    public void ThenTheShelfTitleShouldBe(String shelfTitle)
    {
        json = response.then().body("title",equalTo(shelfTitle));

    }

    @Then("^the shelf kind should be \"([^\"]*)\"$")
    public void ThenTheShelfKindShouldBe(String kind)
    {
        json = response.then().body("kind",equalTo(kind));
    }

    @Then("^the shelf volume count should be (\\d+)$")
    public void ThenTheShelfVolumeCountShouldBe(int volumeCount)
    {
        json = response.then().body("volumeCount",equalTo(volumeCount));
    }

    @Then("^the shelf access level is \"([^\"]*)\"$")
    public void ThenTheShelfAccessLevelIs(String accessLevel)
    {
        json = response.then().body("access",equalTo(accessLevel));
    }

}
