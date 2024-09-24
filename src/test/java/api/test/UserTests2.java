package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	// faker for creating fake data
	// user class to set data here and get data later
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	// creating data
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.random().nextInt(1, 1000));
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debugging__________");
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("****************Creating User******************");
		// passing data to userendpoints class
		Response response = UserEndPoints2.createUser(userPayload);
		// we can use then method here - given and when in endpoints
		response.then().log().all();
		
		// validations
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****************User is created******************");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
	    System.out.println("Testing retrieval for username: " + this.userPayload.getUsername());
	    
	    logger.info("****************Reading User Info******************");
	    
	    Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(), 200);
	    
	    logger.info("****************User info is displayed******************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("****************Updating user info******************");
		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setUserStatus(1);
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// check data is updated after update
		Response updatedResponse = UserEndPoints2.readUser(this.userPayload.getUsername());
		updatedResponse.then().log().all();
		
		Assert.assertEquals(updatedResponse.getStatusCode(), 200);
		
		logger.info("****************User Info is Updated******************");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("****************Deleting user******************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****************User is deleted******************");
	}
}

