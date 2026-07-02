package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class RecentlyViewedPage {
    WebDriver driver;
    WebDriverWait wait;

    // Footer shortcut link to Recently Viewed Products page view
    @FindBy(linkText = "Recently viewed products") 
    private WebElement recentlyViewedFooterLink;

    // Captures the title strings of all items listed inside the history log view
    @FindBy(xpath = "//div[@class='page recently-viewed-products-page']//h2[@class='product-title']/a") 
    private List<WebElement> historyProductTitles;

    public RecentlyViewedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToRecentlyViewed() {
        wait.until(ExpectedConditions.elementToBeClickable(recentlyViewedFooterLink)).click();
    }

    public boolean isProductVisibleInHistory(String productName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(historyProductTitles));
        return historyProductTitles.stream()
            .anyMatch(item -> item.getText().toLowerCase().trim().contains(productName.toLowerCase().trim()));
    }
}
