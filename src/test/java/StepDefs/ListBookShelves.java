package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ListBookShelves {
    private Response response;
    private RequestSpecification request;
    private String usersEndpoint = ConfigurationReader.getProperty("usersEndpoint");
    private ValidatableResponse json;

    @Given("call the endpoint with userID")
    public void call_the_endpoint_with_userID() {
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(usersEndpoint+"/102095640414510446878/bookshelves");
    }

    @Then("^the response status code for bookshelves is (\\d+)$")
    public void the_response_status_code_for_bookshelves_is(Integer statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @Then("^in the response header \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void in_the_response_header_should_be(String header, String headerValue) {
        json = response.then().header(header,headerValue);
    }

    @Then("^the booksShelves IDs are (\\d+) and (\\d+)$")
    public void the_booksShelves_IDs_are_and(Integer int1, Integer int2) {
        json = response.then().body("items.id[0]",equalTo(int1));
        json = response.then().body("items.id[1]",equalTo(int2));
    }

    @Then("^the bookShelves access levels are \"([^\"]*)\"$")
    public void the_bookShelves_access_levels_are(String accessLevel) {
        json = response.then().body("items.access[0]",equalTo(accessLevel));
        json = response.then().body("items.access[1]",equalTo(accessLevel));
    }

    @Then("^the first bookShelf has (\\d+) books$")
    public void the_first_bookShelf_has_books(Integer count) {
        json = response.then().body("items.volumeCount[0]",equalTo(count));
    }

    @Then("^the second bookShelf has (\\d+) books$")
    public void the_second_bookShelf_has_books(Integer count) {
        json = response.then().body("items.volumeCount[1]",equalTo(count));
    }

    @Then("^the booksShelves titles are \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_booksShelves_titles_are_and(String title1, String title2) {
        json = response.then().body("items.title[0]",equalTo(title1));
        json = response.then().body("items.title[1]",equalTo(title2));
    }

}
