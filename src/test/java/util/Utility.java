package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	
	public static RequestSpecification reqSpecification;
	
	public RequestSpecification getRequestSpecification() throws IOException {
			
			if(reqSpecification == null) {
				PrintStream logFile = new PrintStream(new FileOutputStream("logging.txt"));
				reqSpecification = new RequestSpecBuilder().setBaseUri(getProperty("baseUrl")).addQueryParam("key", "qaclick123")
						.addFilter(RequestLoggingFilter.logRequestTo(logFile))
						.addFilter(ResponseLoggingFilter.logResponseTo(logFile))
						.setContentType(ContentType.JSON).build();
			}
		return reqSpecification;
		
	}
	
	public static String getProperty(String key) throws IOException {
		String propertyValue;
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\thangaraj_worksppace\\APIAutomation\\src\\test\\java\\config\\config.properties");
		prop.load(fis);
		propertyValue = (String) prop.get(key);
		return propertyValue;
	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
