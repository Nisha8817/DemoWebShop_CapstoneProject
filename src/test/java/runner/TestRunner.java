package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import stepdefinitions.ProductSearchSteps;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions","hooks"},
    name = {
    	    "Search for a product and add it to the cart",
    	    "Checkout Process with Invalid Data Handling",
    	    "User Registration with Invalid Data Handling",
   	        "Login with Valid & Invalid Credentials",
    		"Data Driven Login Validation",
    	    "Adding Multiple Products to Cart & Validating Cart Summary",
    	    "Perform Check-out with Coupon Discount Verification",
    	    "Logging Out and Verifying Session End",
    	    "Forgot Password Password Recovery Process",
    		"Update Shopping Cart Quantity Controls and Product Removal",
    		"Wishlist Operations for Authenticated Users",
    		"Product Comparison Engine Operations",
    		"Registered User Checkout Process Completion via Gateway Registration",
    		"Checkout with New Shipping Address Configuration",
    		"Checkout with Different Billing & Shipping Address Setup",
    		"Verify Order History and Invoice Validation after placing order",
    		"My Account Management - Update Personal Details",
    		"Newsletter Subscription Management",
    		"Submit Contact Us Form Successfully",
    		"Category Navigation Through Categories and Subcategories",
    		"Product Sorting Validation",
    	    "Product Filter Validation",
    	    "Recently Viewed Products Validation Engine",
    		"Product Return Request Submission for Eligible Items" 
    	},
   
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "json:target/cucumber-reports/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false) // Must remain false to maintain execution order
    public Object[][] scenarios() {
        return super.scenarios();
    }

   /* @AfterSuite(alwaysRun = true)
    public void closeGlobalBrowserSession() {
        WebDriver driver = ProductSearchSteps.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }*/
}
