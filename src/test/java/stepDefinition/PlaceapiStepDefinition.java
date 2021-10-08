package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import apiCollection.APIResources;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testDataBuild.PlaceTestDataBuild;
import util.Utility;

public class PlaceapiStepDefinition extends Utility{
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	public static String place_id;
	PlaceTestDataBuild placeTestDataBuild = new PlaceTestDataBuild();
	
	
	@Given("^Add place payload using the testdata from the sheet \"([^\"]*)\" with the case \"([^\"]*)\"$")
    public void addPlaceWithNameAndAdress(String sheetName, String testCaseName) throws Throwable {
		requestSpecification = given().spec(getRequestSpecification())
				.body(placeTestDataBuild.addPlace(sheetName, testCaseName));
	}

    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void usersCallsHTTPRequest(String apiName, String apiType) throws Throwable {
    	APIResources resourceAPI = APIResources.valueOf(apiName);
    	responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	switch(apiType) {
    	case "POST":
    		response = requestSpecification.when().post(resourceAPI.getResource());
    		break;
    	case "GET":
    		response = requestSpecification.when().get(resourceAPI.getResource());
    	break;
    	}
    }

    @Then("^the API call got sucess with status code 200$")
    public void validateAPIResponse() throws Throwable {
    	assertEquals(response.getStatusCode(), 200);
    	
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void validateResponseFromResponseBody(String responseKey, String responseValue) throws Throwable {
    	assertEquals(getJsonPath(response, responseKey), responseValue);
    }

    @And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verifyCreatedPlcaseGetApi(String name, String getApiName) throws Throwable {
    	place_id = getJsonPath(response, "place_id");
    	requestSpecification = given().spec(getRequestSpecification()).queryParam("place_id", place_id);
    	usersCallsHTTPRequest(getApiName, "GET");
    	assertEquals(getJsonPath(response, "name"), name);
    }

    @Given("^user loads DeletePlace payload$")
    public void userLoadsDeleteplacePayload() throws Throwable {
    	
    	requestSpecification = given().spec(getRequestSpecification()).body(placeTestDataBuild.deletePlacePayload(place_id));

    }


}
