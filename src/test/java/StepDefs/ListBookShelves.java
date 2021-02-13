package StepDefs;

import Utils.ConfigurationReader;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ListBookShelves {
    private Response response;
    private RequestSpecification request;
    private String endpointVolumes = ConfigurationReader.getProperty("endpointVolumes");
    private ValidatableResponse json;
}
