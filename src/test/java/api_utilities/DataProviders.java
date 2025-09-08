package api_utilities;

import api_payload.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
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
    /*[101, tester1, John, Bosh, q@example.com, Test%1, 123456789]
[102, tester2, Kim, Trump, w@example.com, Test&2, 123456789]
[103, tester3, Steve, Kenedy, t@example.com, Test&4, 123456789]*/


}
