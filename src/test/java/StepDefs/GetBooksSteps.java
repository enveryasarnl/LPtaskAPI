package StepDefs;

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

public class GetBooksSteps {
    private Response response;
    private RequestSpecification request;
    private String endpointVolumes = "https://www.googleapis.com/books/v1/volumes";
    private String endpointVolumes1 ="https://www.googleapis.com/books/v1/users/102095640414510446878/bookshelves";
    private ValidatableResponse json;

    @Given("^a book exists with an isbn of (.*)$")
    public void aBookExistsWithAnIsbnOf(String isbn) {
        request = given().param("q", "isbn:" + isbn);
    }

    @When("^a user retrieves the book by isbn$")
    public void aUserRetrievesTheBookByIsbn() {
        response = request.when().get(endpointVolumes);
 //       System.out.println("response: " + response.prettyPrint());
 //       System.out.println("test");
    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int statusCode) {

        json = response.then().statusCode(statusCode);
    }

    @And("^response includes the following$")
    public void responseIncludesTheFollowing(Map<String,String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @And("^response includes the following in any order$")
    public void responseIncludesTheFollowingInAnyOrder(Map<String,String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), containsInAnyOrder(field.getValue()));
            }
        }
    }

    @Given("^Author \"([^\"]*)\" exists$")
    public void authorExists(String authorName) {

        request = given()
                .param("q", "inauthor:"+authorName);
    }

    @When("^A user retrieves his books$")
    public void aUserRetrievesHisBooks() throws Throwable {
        response = request.when().get(endpointVolumes);
    }

    @Then("^hhhh$")
     public void hhh(){


    }
}
