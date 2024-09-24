package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	//faker for creating fake data
	//user class to set data here and get data later
	Faker faker;
	User userPayload;
	
	//creating data
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}
	
	@Test (priority = 1)
	public void testPostUser() 
	{
		//passing data to userendpoints class
		Response response =  UserEndPoints.createUser(userPayload);
		//we can use then method here - given and when in endpoints
		response.then().log().all();
		
		//validations
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test (priority = 2)
	public void testGetUserByName()
	{
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}