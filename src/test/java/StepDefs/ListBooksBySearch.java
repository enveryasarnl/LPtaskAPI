package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ListBooksBySearch {
    private Response response;
    private RequestSpecification request;
    private String volumesEndpoint = ConfigurationReader.getProperty("volumesEndpoint");
    private ValidatableResponse json;

    @Given("^call search endpoint with \"([^\"]*)\"$")
    public void call_search_endpoint_with(String searchKeyword){
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(volumesEndpoint+"?q="+searchKeyword);
    }

    @Then("^the status code is success$")
    public void the_status_code_is_success() {
        json = response.then().statusCode(200);
    }

    @Then("^in the response header Content-Type should be application/json$")
    public void in_the_response_header_Content_Type_should_be_application_json() {
        json = response.then().header("Content-Type","application/json; charset=UTF-8");
    }

    @Then("^in the response header Content-Encoding should be gzip$")
    public void in_the_response_header_Content_Encoding_should_be_gzip() {
        json = response.then().header("Content-Encoding","gzip");
    }

    @Then("^the found books count is greater than \"([^\"]*)\"$")
    public void the_found_books_count_is_greater_than(Integer booksCount) {
        int actualBookCount = response.path("totalItems");
        Assert.assertTrue(actualBookCount>booksCount);
    }

    @Then("^the response body items should contain \"([^\"]*)\"$")
    public void the_response_body_items_should_contain(String searchKeyword) {
        String actualTitle = given().when()
                .get(volumesEndpoint+"?q="+searchKeyword)
                .path("items[0].volumeInfo.title");
        Assert.assertTrue(actualTitle.toLowerCase().contains(searchKeyword.toLowerCase()));
    }

    @Then("^the \"([^\"]*)\" should be for the kind key$")
    public void the_should_be_should_be_for_the_kind_key(String booksVolumes) {
        json = response.then().body("kind",equalTo(booksVolumes));

    }

}
