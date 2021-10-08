package testDataBuild;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import pojo.AddPlace;
import pojo.Location;
import readData.ReadExcelData;

public class PlaceTestDataBuild {

	public AddPlace addPlace(String sheetName, String testCaseName) throws IOException {
		AddPlace addPlace = new AddPlace();
		ReadExcelData readExcelData = new ReadExcelData();
		ArrayList<String> testData = readExcelData.getExcelData(testCaseName, sheetName);
		
		addPlace.setAccuracy(Integer.parseInt(testData.get(5)));
		addPlace.setAddress(testData.get(2));
		addPlace.setLanguage(testData.get(3));
		addPlace.setPhone_number(testData.get(4));
		addPlace.setWebsite(testData.get(6));
		addPlace.setName(testData.get(1));
		List<String> myList = new ArrayList<String>();
		
		String[] landMarkList = testData.get(7).split(",");
		
		for(int index=0; index<landMarkList.length; index++) {
			myList.add(landMarkList[index]);
		}
		
//		myList.add("shoe park");
//		myList.add("shop");
		
		addPlace.setTypes(myList);
		Location location = new Location();
		location.setLat(Double.parseDouble(testData.get(8)));
		location.setLng(Double.parseDouble(testData.get(9)));
		addPlace.setLocation(location);
		return addPlace;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{\r\n \"place_id\":\""+placeID+"\"\r\n}";
	}
	
}
