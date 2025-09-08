package api_test;

import api_endpoints.UserEndPoints;
import api_payload.User;
import api_utilities.XLUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;


public class DataDrivenTest {

    @Test(priority = 1, dataProvider = "provider")
    public void createUserTest(String id, String userName, String fName, String lName,
                           String email, String pass, String phone){
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(id));
        userPayload.setEmail(email);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setUsername(userName);
        userPayload.setPhone(phone);
        userPayload.setPassword(pass);

        Response res = UserEndPoints.createUser(userPayload);
        res.then().log().body();
        Assert.assertEquals(res.getStatusCode() , 200);
    }

    @Test(priority = 2, dataProvider = "userNameProvider")
    public void deleteUserTest(String userName) {
        Response res = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @DataProvider
    public String[] userNameProvider() throws IOException {

        File file = new File(".\\userData.xlsx");
        int rows = XLUtils.getRowCount(String.valueOf(file), "Sheet1");
        String[] data = new String[rows - 1];
        for (int i = 1; i <= rows - 1; i++) {
            data[i - 1] = XLUtils.getCellData(String.valueOf(file), "Sheet1", i, 1);
        }
        return data;
    }

    @DataProvider
    public String[][] provider() throws IOException {
        File file = new File(".\\userData.xlsx");
        int rows = XLUtils.getRowCount(String.valueOf(file), "Sheet1");
        int cols = XLUtils.getCellCount(String.valueOf(file), "Sheet1",1) ;
        String[][] data = new String[rows-1][cols];

        for (int i = 1; i <= rows-1 ; i++) {
            for (int j = 0; j<cols ; j++) {
                data[i-1][j] = XLUtils.getCellData(String.valueOf(file), "Sheet1",i,j);
            }
        }

        return data;
    }


}
