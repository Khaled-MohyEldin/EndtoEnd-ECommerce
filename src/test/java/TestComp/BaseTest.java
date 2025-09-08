package TestComp;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public InputStream inputStream = null;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //setUp global Properties
        Properties prop = new Properties();

        inputStream = getClass().getResourceAsStream("/GlobalData.properties");
        prop.load(inputStream);

        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") : prop.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name not specified in GlobalData.properties");}

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("-headless");
            driver = new FirefoxDriver(options);
            // Ensure in even headless mode it will be maximized
            //driver.manage().window().setSize(new Dimension(1440,900));//full screen

        } else if (browserName.equalsIgnoreCase("Edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);

        } else { throw new IllegalArgumentException("Unsupported browser: " + browserName); }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    public List<HashMap<Object, Object>> getJsonData(String fileName) throws IOException {
        //using Jackson Databind Library
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, new TypeReference<List<HashMap<Object, Object>>>() {
        });

    }

    public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {
        // this method is taking screenshot and returning a path into your local system
        //Cast you driver into TakesScreenShot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png";
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void attachScreenshot(WebDriver driver, String attachmentName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Attach the screenshot using Allure.addAttachment
            Allure.addAttachment(attachmentName,
                    "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
        } catch (Exception e) {
            System.out.println("Could not capture and attach screenshot: " + e.getMessage());
        }
    }

}
