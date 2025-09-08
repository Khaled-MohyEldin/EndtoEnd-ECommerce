package OLDProj;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequest {
    //gherkin-keywords given(), when(), then()
    String base = "https://reqres.in/api";
    String endP = "";
    int id;

    @Test()
    public void getUsers() {
        endP = "/users?page=1";

        given().baseUri(base).header("x-api-key", "reqres-free-v1")
                .when().get(endP)
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .log().all();
    }

    @Test(priority = 1)
    public void createUser() {
        endP = "/register";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("email", "eve.holt@reqres.in");
        hm.put("password", "1&Two&Three");

        id = given().baseUri(base).header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(hm)

                .when().post(endP)
                .jsonPath().getInt("id");
    }

    @Test(priority = 2, dependsOnMethods = {"createUser"})
    public void updateUser() {
        endP = "/users/" + id;
        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "Khaled");
        hm.put("job", "QA Engineer");

        given().baseUri(base).header("x-api-key", "reqres-free-v1")
                .body(hm)
        .when().put(endP)
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void deleteUser() {
        endP = "/users/" + id;

        given().baseUri(base).header("x-api-key", "reqres-free-v1")
                .when().delete(endP)
                .then()
                .statusCode(204);
    }



}


