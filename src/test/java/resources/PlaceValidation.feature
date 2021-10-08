Feature: Validating place API functionality

@All @AddPlace
Scenario Outline: Verify the Place is addedd sucessfully using AddPlaceAPI
		Given Add place payload using the testdata from the sheet "GooglePlaceData" with the case "Case_1" 
		When user calls "AddPlaceAPI" with "POST" http request
		Then the API call got sucess with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify place_id created maps to "<name>" using "GetPlaceAPI"
Examples:
		|name|language|address|
		|Own Learning|English|India cross center|
		
@All @DeletePlace
Scenario: Verify delete place API functionality
	Given user loads DeletePlace payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call got sucess with status code 200 
	And "status" in response body is "OK" 