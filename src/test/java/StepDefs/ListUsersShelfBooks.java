package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ListUsersShelfBooks {
    private Response response;
    private RequestSpecification request;
    private String usersEndpoint = ConfigurationReader.getProperty("usersEndpoint");
    private ValidatableResponse json;

    @Given("^call the endpoint with userID and (\\d+) shelf ID$")
    public void GivenCallTheEndpointWithUserIDAndShelfID(int shelfID)
    {
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(usersEndpoint+"/102095640414510446878/bookshelves/"+shelfID+"/volumes");

    }


    @Then("^the status code for shelf is (\\d+)$")
    public void theStatusCodeForShelfIs(int statusCode) {
        json = response.then().statusCode(statusCode);

    }


    @Then("^the header \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void ThenTheHeaderShouldBe(String header, String headerValue)
    {
        json = response.then().header(header,headerValue);
    }

    @Then("^the shelf has (\\d+) books$")
    public void ThenTheShelfHasBooks(int bookCount)
    {
        json = response.then().body("totalItems",equalTo(bookCount));
    }

    @Then("^the books \"([^\"]*)\" should be \"([^\"]*)\"as follows$")
    public void ThenTheBooksTitlesShouldBeAsFollows(String title, int rowNumber)
    {

            json = response.then().body("items.volumeInfo.title["+rowNumber+"]",equalTo(title));

    }

}
