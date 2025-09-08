package OLDProj;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.allOf;
import static io.restassured.RestAssured.given;

public class QueryParam {

    String base = "https://petstore.swagger.io/v2";
    String endP = "";
    int id;


    @Test()
    public void testJsonResponse() throws FileNotFoundException {
        endP = "/pet/3";

        Response res = given().baseUri(base).header("x-api-key", "special-key")
                .contentType("application/json")
                .when().get(endP);
        //.then().log().body()
        System.out.println(res.jsonPath().get("photoUrls").toString());
    }

    /*

{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}

    */

}
