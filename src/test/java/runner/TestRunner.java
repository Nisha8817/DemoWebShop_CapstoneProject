package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import stepdefinitions.ProductSearchSteps;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    		// name = "Search for a product and add it to the cart",

    		// name = "Checkout Process with Invalid Data Handling",

    		// name = "User Registration with Invalid Data Handling",

    		// name = "Login with Valid & Invalid Credentials",

    		// name = "Adding Multiple Products to Cart & Validating Cart Summary",

    		// name = "Perform Check-out with Coupon Discount Verification",

    		// name = "Logging Out and Verifying Session End",

    		// name = "Forgot Password Password Recovery Process",

    		// name = "Update Shopping Cart Quantity Controls and Product Removal",

    		// name = "Wishlist Operations for Authenticated Users",

    		// name = "Product Comparison Engine Operations",

    		// name = "Registered User Checkout Process Completion via Gateway Registration",

    		// name = "Checkout with New Shipping Address Configuration",

    		// name = "Checkout with Different Billing & Shipping Address Setup",

    		// name = "Verify Order History and Invoice Validation after placing order",

    		// name = "My Account Management - Update Personal Details",

    		 //name = "Newsletter Subscription Management",
        
            //name = "Submit Contact Us Form Successfully",
           
          name = "Category Navigation Through Categories and Subcategories",
           
         //  name = "Product Sorting Validation",
        //name = "Product Filter Validation",
    //name = "Recently Viewed Products Validation Engine",
   // name = "Product Return Request Submission for Eligible Items",
   
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "json:target/cucumber-reports/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false) // Must remain false to maintain execution order
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite(alwaysRun = true)
    public void closeGlobalBrowserSession() {
        WebDriver driver = ProductSearchSteps.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
