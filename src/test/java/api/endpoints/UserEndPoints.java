package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class UserEndPoints {

    static Logger logger = Logger.getLogger(UserEndPoints.class);

    // Create user operation
    public static Response createUser(User payload) {
        logger.info("Creating user with username: " + payload.getUsername());
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
            .when()
                .post(Routes.post_url);
        
        response.then().log().all();
        return response;
    }

    // Read user operation
    public static Response readUser(String userName) {
        logger.info("Reading user with username: " + userName);
        Response response = given()
                .pathParam("userName", userName)
            .when()
                .get(Routes.get_url);
        
        response.then().log().all();
        return response;
    }

    // Update user operation
    public static Response updateUser(String userName, User payload) {
        logger.info("Updating user with username: " + userName);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", userName)
                .body(payload)
            .when()
                .put(Routes.update_url);
        
        response.then().log().all();
        return response;
    }
    
    public static Response deleteUser(String userName) {
        logger.info("Deleting user with username: " + userName);
        Response response = given()
                .pathParam("userName", userName)
            .when()
                .delete(Routes.delete_url);
        
        response.then().log().all();
        return response;
    }
}
