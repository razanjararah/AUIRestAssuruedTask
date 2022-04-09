import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class User_API_Tests {

	
	@Test
	void test_Post_User() { 
		
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", "Razan");
		requestParams.put("job", "QA Engineer");
		
		RequestSpecification request = RestAssured.given(); 
		request.body(requestParams.toJSONString());
		Response response = request.post("https://reqres.in/api/users"); 
		
		Assert.assertEquals(response.statusCode(),201);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		String id = jsonPathEvaluator.get("id"); 
 		System.out.println("The returned id is " + id); 
 		
	}
	
	@Test
	void test_Get_User_By_Id() {
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("https://reqres.in/api/users/7");
		
		int statusCode = response.statusCode();
 		Assert.assertEquals(statusCode,200); 
 		
		JsonPath jsonPathEvaluator = response.jsonPath();
		String firstName = jsonPathEvaluator.get("data.first_name"); 
		String lastName = jsonPathEvaluator.get("data.last_name"); 
 		System.out.println("The user with ID #7 is " + firstName + " " + lastName); 
	}
	
	@Test
	void test_Get_User_By_NOT_Existing_Id() {
			
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("https://reqres.in/api/users/-1");
			
		int statusCode = response.statusCode();
	 	Assert.assertEquals(statusCode,404); 
	 		 
	}
	
}
