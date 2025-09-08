package api_test;

import api_endpoints.UserEndPoints;
import api_endpoints.UserEndPoints2;
import api_payload.User;
import api_utilities.Log;
import api_utilities.XLUtils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class UserTest {
    Faker faker;
    User userPayload;

//    public Logger logger; //for Logs

    @BeforeTest
    public void setup(){
        //Logs
//        logger = LogManager.getLogger(this.getClass());
//        logger.info("******************* Setting up payload util  **********************");

        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setUsername(faker.name().username());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password());

    }


    @Test (priority = 1)
    public void createTest(){
//        logger.info("******************* Creating User  **********************");

        Response res = UserEndPoints.createUser(userPayload);
        res.then().log().body();
        Assert.assertEquals(res.getStatusCode() , 200);
//        logger.info("******************* User Created **********************");
    }

    @Test (priority = 2)
    public void loginTest(){
//        logger.info("******************* Logging in User  **********************");
        Response res = UserEndPoints.userLogin(userPayload.getUsername(), userPayload.getPassword());
        res.then().log().body();
        String msg = res.jsonPath().get("message");

        Assert.assertTrue(msg.contains("logged in user session"));
        Assert.assertEquals(res.getStatusCode() , 200);
//        logger.info("******************* logged in successfully  **********************");
    }
    /*
    @Test (priority = 3)
    public void testGetUserByName(){
        Response res = UserEndPoints.readUser(userPayload.getUsername());
        res.then().log().body();
        Assert.assertEquals(res.getStatusCode() , 200);
    }



    @Test (priority = 4)
    public void updateTest(){
        userPayload.setUsername(faker.name().username());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        Response res = UserEndPoints.updateUser(userPayload, userPayload.getUsername());
        res.then().log().body();
        Assert.assertEquals(res.getStatusCode() , 200);
    }

    @Test (priority = 5)
    public void deleteTest(){
        Response res = UserEndPoints.deleteUser(userPayload.getUsername());
        Assert.assertEquals(res.getStatusCode() , 200);
    }
        */


}
