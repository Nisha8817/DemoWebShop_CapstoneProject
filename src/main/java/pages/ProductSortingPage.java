package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSortingPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "products-orderby") 
    private WebElement sortByDropdown;

    @FindBy(className = "product-title") 
    private List<WebElement> productTitles;

    @FindBy(css = "span.price.actual-price") 
    private List<WebElement> productPrices;

    public ProductSortingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void selectSortOption(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(sortByDropdown));
        new Select(sortByDropdown).selectByVisibleText(optionText);
    }

    public List<String> getProductNamesList() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
        return productTitles.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getProductPricesList() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        return productPrices.stream()
            .map(el -> Double.parseDouble(el.getText().replaceAll("[^0-9.]", "")))
            .collect(Collectors.toList());
    }
}
