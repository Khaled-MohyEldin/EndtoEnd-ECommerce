package OLDProj;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class WaystoCreatPostBody {

    String base = "https://reqres.in/api";
    String endP = "";
    int id;


    @Test()
    public void getUsers() throws FileNotFoundException {
        endP = "/register";

        //Using External Json file
        //1.1 get file path
        File file = new File(".\\data.json");
        // the ".\\" means current directory rather than saying System.getPreperty("user.dir")

        //1.2 pass reader into BufferReader
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //1.3 then into JSONTokener and Then into JASONObject
        JSONTokener jt = new JSONTokener(reader);
        JSONObject data = new JSONObject(jt);

        given().baseUri(base).header("x-api-key", "reqres-free-v1")
               .contentType("application/json")
               .body(data.toString())

        .when().post(endP)

        .then()
                .log().all();
    }

}

/*
        given()

        .when()

        .then()
                HashMap<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "1&Two&Three");
* */