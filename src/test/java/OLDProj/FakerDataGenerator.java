package OLDProj;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {

    String base = "https://petstore.swagger.io/v2";
    String endP = "";
    int id;

    @Test()
    public void fakerDataGenerator() {
        endP = "/pet/10";

        Faker faker = new Faker();

        faker.name().username();
        faker.internet().emailAddress();
        faker.internet().password();

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