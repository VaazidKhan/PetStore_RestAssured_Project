package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// To perform CRUD operations for user API
public class UserEndPoints2 {
	
	
	//getting data from properties file
	static ResourceBundle getUrl()
	{
		//Rescource bundle gets the file path automatically
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //routes.properties
		return routes;
	}
	
	
	
	// Create user operation
    public static Response createUser(User payload) {
    	
    	//getting url from properties file
    	 
    	String post_url = getUrl().getString("post_url");
    	
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)  // Send the User payload
            .when()
                .post(post_url);
        
        return response;
    }

    // Read user operation
    public static Response readUser(String userName) {
    	
    	String get_url = getUrl().getString("get_url");
    	
        Response response = given()
                .pathParam("userName", userName)
            .when()
                .get(get_url);
        
        return response;
    }

    // Update user operation
    public static Response updateUser(String userName, User payload) {
    	
    	String update_url = getUrl().getString("update_url");
    	
    	
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", userName)
                .body(payload)  // Send the updated User payload
            .when()
                .put(update_url);
        
        return response;
    }
    
    public static Response deleteUser(String userName) {
    	
    	String delete_url = getUrl().getString("delete_url");
    	
        Response response = given()
                .pathParam("userName", userName)
            .when()
                .delete(delete_url);
   
        return response;
    }
}
