package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductFilterPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductFilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToCategory(String category) {

        if (category.equalsIgnoreCase("Desktops")) {

            driver.get("https://demowebshop.tricentis.com/desktops");

            wait.until(ExpectedConditions.urlContains("desktops"));
        }
        else if (category.equalsIgnoreCase("Computers")) {

            driver.get("https://demowebshop.tricentis.com/computers");

            wait.until(ExpectedConditions.urlContains("computers"));
        }
        else if (category.equalsIgnoreCase("Notebooks")) {

            driver.get("https://demowebshop.tricentis.com/notebooks");

            wait.until(ExpectedConditions.urlContains("notebooks"));
        }
        else if (category.equalsIgnoreCase("Accessories")) {

            driver.get("https://demowebshop.tricentis.com/accessories");

            wait.until(ExpectedConditions.urlContains("accessories"));
        }
    }

    public int getProductCount() {

        List<WebElement> products =
                driver.findElements(By.cssSelector(".item-box"));

        return products.size();
    }

    public void applyFilter() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-grid")));
    }

    public void clearFilter() {

        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-grid")));
    }
}