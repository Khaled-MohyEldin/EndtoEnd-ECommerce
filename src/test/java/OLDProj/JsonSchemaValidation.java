package OLDProj;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidation {

    String base = "https://petstore.swagger.io/v2";
    String endP = "";
    int id;

    //Keep the schema file inside resources in (.json) formate
    @Test()
    public void validateSchema() {
        endP = "/pet/10";
        given().baseUri(base)
        .when().get(endP)

        .then()
                .assertThat() // here we give him file name in resources
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetStoreSchema.json"));


    }
}

/*
        given().baseUri(base)

        .when().get(endP)

        .then()
                HashMap<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "1&Two&Three");
* */