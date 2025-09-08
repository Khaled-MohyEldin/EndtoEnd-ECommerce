package OLDProj;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class APIChaining {
    String token = "a794be9230f9c504e89622a35c4cc6fbcc942a295ed9c6dea02b30670d1de473";
    String base = "https://gorest.co.in/public/v2";
    String endP ,name, email;
    int userId, postId, commId;
    Faker faker = new Faker();


    //@Test()
    public void getAllUsers() {
        endP = "/users";
        given().baseUri(base).header("Authorization", "Bearer "+token)
                .when().get(endP)
                .then().statusCode(200).log().all();

    }

    @Test(priority = 1)
    public void createUser() {
        endP = "/users";
        name = faker.name().firstName();
        email = faker.internet().safeEmailAddress();

        JSONObject data = new JSONObject();
        data.put("name",name);
        data.put("email",email);
        data.put("gender","male");
        data.put("status","active");

        userId = given().baseUri(base).header("Authorization", "Bearer "+token)
                .contentType("application/json; charset=utf-8")
                .body(data.toString())
                .when().post(endP)
                .jsonPath().getInt("id");

        System.out.println("userID = " + userId + "\nname: " + name+"\nemail: "+ email);

    }

    @Test(priority = 2)
    public void getUsers() {
        endP = "/users/" + userId;
        given().baseUri(base).header("Authorization", "Bearer "+token)
                .contentType("application/json; charset=utf-8")
                .when().get(endP)
                .then().statusCode(200);

    }

    @Test(priority = 3)
    public void createUserPost() {
        endP = "/users/"+ userId +"/posts" ;

        JSONObject data = new JSONObject();
        data.put("title",faker.lorem().sentence());
        data.put("body", faker.lorem().paragraph());

        postId = given().baseUri(base).header("Authorization", "Bearer "+token)
                .contentType("application/json")
                .body(data.toString())
                .when().post(endP)
                .jsonPath().getInt("id");

        System.out.println("PostID = " + postId );

    }

    @Test(priority = 4)
    public void getUserPosts() {
        endP = "/users/"+ userId +"/posts";
        given().baseUri(base).header("Authorization", "Bearer "+token)
                .contentType("application/json; charset=utf-8")
                .when().get(endP)
                .then().statusCode(200);

    }

    @Test(priority = 5)
    public void createPostComment() {
        endP = "/posts/"+ postId +"/comments" ;

        JSONObject data = new JSONObject();
        data.put("name", faker.lorem().sentence());
        data.put("email", faker.internet().safeEmailAddress());
        data.put("body",faker.lorem().paragraph());

        commId = given().baseUri(base).header("Authorization", "Bearer "+token)
                .contentType("application/json")
                .body(data.toString())
                .when().post(endP)
                .jsonPath().getInt("id");

        System.out.println("Comment ID = " + commId );

    }

}


/*
        given().baseUri(base)
        .header("Authorization", )

        .when().get(endP)

        .then()
                HashMap<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "1&Two&Three");
* */