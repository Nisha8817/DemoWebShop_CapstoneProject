package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;
    

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickProduct(String productName) {
        //  Construct the locator and wait explicitly until it is attached and clickable
        String xpathExpression = String.format("//h2[@class='product-title']/a[contains(text(),'%s')]", productName);
        
        // Wait for the stale/loading DOM state to settle down completely
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        productLink.click();
    }
}
