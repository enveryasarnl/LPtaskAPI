package StepDefs;

import Utils.ConfigurationReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetTheBookByItsId {
    private Response response;
    private RequestSpecification request;
    private String volumesEndpoint = ConfigurationReader.getProperty("volumesEndpoint");
    private ValidatableResponse json;

    @Given("^call the endpoint with \"([^\"]*)\"$")
    public void call_the_endpoint_with(String bookID)
    {
        request = given().param("key", "AIzaSyBRLx2MXHlbTIemtHtanuQKSf3Cfni8RDk");
        response = request.when().get(volumesEndpoint+"/"+bookID);
    }

    @Then("^the response status code is success$")
    public void the_response_status_code_is_success()
    {
        json = response.then().statusCode(200);
    }

    @Then("^in the header Content-Type should be application/json$")
    public void in_the_header_Content_Type_should_be_application_json()
    {
        json = response.then().header("Content-Type","application/json; charset=UTF-8");
    }

    @Then("^in the header Content-Encoding should be gzip$")
    public void in_the_header_Content_Encoding_should_be_gzip()
    {
        json = response.then().header("Content-Encoding","gzip");
    }

    @Then("^the title \"([^\"]*)\" is follows$")
    public void the_title_is_as_follows(String title)
    {
        json = response.then().body("volumeInfo.title",equalTo(title));
    }

    @Then("^the page count \"([^\"]*)\" is as in the list below$")
    public void the_page_count_is_as_follows(Integer pageCount)
    {
        json = response.then().body("volumeInfo.pageCount",equalTo(pageCount));
    }

    @And("^the published date \"([^\"]*)\" is as in the list below$")
    public void the_published_date_is_as_follows(String publishedDate)
    {
        json = response.then().body("volumeInfo.publishedDate",equalTo(publishedDate));
    }
}
