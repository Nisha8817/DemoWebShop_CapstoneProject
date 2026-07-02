package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CompareProductsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//tr[@class='product-name']/td/a")
    private List<WebElement> comparedProductLinks;

    public CompareProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isProductInComparisonGrid(String productName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(comparedProductLinks));
        return comparedProductLinks.stream()
            .anyMatch(item -> item.getText().toLowerCase().trim().contains(productName.toLowerCase().trim()));
    }
}
