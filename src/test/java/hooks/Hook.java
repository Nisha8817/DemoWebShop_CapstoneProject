package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import util.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Hook extends BaseClass {
    public static WebDriver driver;
    public static HomePage homePage;
    public static SearchResultsPage searchResultsPage;
    public static ProductDetailsPage productDetailsPage;
    public static CartPage cartPage;
    public static RegisterPage registerPage;
    public static LoginPage loginPage;
    public static GuestCheckoutPage guestCheckoutPage;
    public static RegisteredCheckoutPage registeredCheckoutPage;
    public static AccountPage accountPage;
    public static ProductSortingPage productSortingPage;
    public static ProductFilterPage productFilterPage;
    @BeforeAll
    public static void setUp() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/java/util/data.properties");
        Properties property = new Properties();
        property.load(fis);
        if (driver == null) {
            invokeBrowser(property.getProperty("browser"));
            driver=BaseClass.driver;

            homePage = new HomePage(driver);
            searchResultsPage = new SearchResultsPage(driver);
            productDetailsPage = new ProductDetailsPage(driver);
            cartPage = new CartPage(driver);
            new CheckoutPage(driver);
            registerPage = new RegisterPage(driver);
            loginPage = new LoginPage(driver);
            guestCheckoutPage = new GuestCheckoutPage(driver);
            registeredCheckoutPage = new RegisteredCheckoutPage(driver);
        }
    }
    @After
    public void captureScreenshotAfterEveryStep(io.cucumber.java.Scenario scenario) {
        if (driver != null) {
            try {
                // Cast driver instance to capture raw screenshot bytes
                org.openqa.selenium.TakesScreenshot ts = (org.openqa.selenium.TakesScreenshot) driver;
                byte[] screenshotBytes = ts.getScreenshotAs(org.openqa.selenium.OutputType.BYTES);

                // Determine a clean label based on step status
                String statusLabel = scenario.isFailed() ? "Failed_Step_View" : "Passed_Step_View";

                // Attach the screenshot directly underneath the current step row
                scenario.attach(screenshotBytes, "image/png", statusLabel);

            } catch (Exception e) {
                System.out.println("Failed to capture step screenshot: " + e.getMessage());
            }
        }
    }
    @AfterAll
    public static void tearDown(){
        closeBrowser();
    }
}
 