package api_endpoints;

import api_payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    // Read Data URLs From .properties file
    static ResourceBundle getURL(){
        // Load properties file
        return ResourceBundle.getBundle("routes");
    }

    public static Response createUser (User payload){
        return given().header("x-api-key", "special-key")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                //.when().post(Routes.post_url);
                .when().post(getURL().getString("post_url"));
    }

    public static Response readUser (String userName){

        return given().header("x-api-key", "special-key")
                .pathParams("username", userName)
                .when().get(getURL().getString("get_url"));
    }

    public static Response updateUser (User payload, String userName){
        return given().header("x-api-key", "special-key")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(payload)
                .when().put(getURL().getString("update_url"));
    }

    public static Response deleteUser (String userName){
        return given().header("x-api-key", "special-key")
                .pathParams("username", userName)
                .when().get(getURL().getString("delete_url"));
    }

    public static Response userLogin (String username, String password){
        return given().header("x-api-key", "special-key")
                .queryParam("username",username)
                .queryParam("password",password)
                .when().get(getURL().getString("login_url"));
    }

    public static Response userLogout (){
        return given().header("x-api-key", "special-key")
                .when().get(getURL().getString("logout_url"));
    }
}
