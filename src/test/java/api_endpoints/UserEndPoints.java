package api_endpoints;

import api_payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {

    // Implement CRUD Operations
    public static Response createUser (User payload){
        return given().header("x-api-key", "special-key")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().post(Routes.post_url);
    }

    public static Response readUser (String userName){

        return given().header("x-api-key", "special-key")
                .pathParams("username", userName)
                .when().get(Routes.get_url);
    }

    public static Response updateUser (User payload, String userName){
        return given().header("x-api-key", "special-key")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(payload)
                .when().put(Routes.update_url);
    }

    public static Response deleteUser (String userName){
        return given().header("x-api-key", "special-key")
                .pathParams("username", userName)
                .when().get(Routes.delete_url);
    }

    public static Response userLogin (String username, String password){
        return given().header("x-api-key", "special-key")
                .queryParam("username",username)
                .queryParam("password",password)
                .when().get(Routes.login_url);
    }

    public static Response userLogout (){
        return given().header("x-api-key", "special-key")
                .when().get(Routes.logout_url);
    }
}
