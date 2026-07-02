package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReturnRequestPage {

    WebDriver driver;
    WebDriverWait wait;

    public ReturnRequestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isReturnItemsButtonPresent() {

        List<WebElement> buttons =
                driver.findElements(
                        By.xpath("//input[@value='Return Items']"));

        return buttons.size() > 0;
    }

    public void clickReturnItemsButton() {

        if (isReturnItemsButtonPresent()) {

            driver.findElement(
                    By.xpath("//input[@value='Return Items']"))
                    .click();
        }
    }

    public String getCurrentPageTitle() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-title")))
                .getText();
    }
}