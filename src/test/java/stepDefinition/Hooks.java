package stepDefinition;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		PlaceapiStepDefinition placeapiStepDefinition = new PlaceapiStepDefinition();
		
		if(PlaceapiStepDefinition.place_id == null) {
			placeapiStepDefinition.addPlaceWithNameAndAdress("GooglePlaceData", "Case_1");
			placeapiStepDefinition.usersCallsHTTPRequest("AddPlaceAPI", "POST");
			placeapiStepDefinition.verifyCreatedPlcaseGetApi("Thanga", "GetPlaceAPI");
		}
	}
}
