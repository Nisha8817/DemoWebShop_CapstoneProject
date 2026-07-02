package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WishlistPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "ico-wishlist") private WebElement wishlistHeaderLink;
    
    //  Robust tag-agnostic column locator to grab any item link inside the wishlist table grid row
    @FindBy(xpath = "//table[@class='cart']//td[@class='product']/a") 
    private List<WebElement> wishlistItems;
    
    @FindBy(name = "removefromcart") private WebElement removeFromWishlistCheckbox;
    @FindBy(name = "updatecart") private WebElement updateWishlistBtn;
    @FindBy(className = "wishlist-content") private WebElement emptyWishlistMsg;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistHeaderLink)).click();
    }

    public boolean isProductInWishlist(String productName) {
        // Wait briefly until table rows are visible on screen
        wait.until(ExpectedConditions.visibilityOfAllElements(wishlistItems));
        return wishlistItems.stream()
            .anyMatch(item -> item.getText().toLowerCase().trim().contains(productName.toLowerCase().trim()));
    }

    public void removeProductFromWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromWishlistCheckbox));
        if (!removeFromWishlistCheckbox.isSelected()) {
            removeFromWishlistCheckbox.click();
        }
        updateWishlistBtn.click();
    }

    public String getEmptyWishlistMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emptyWishlistMsg)).getText().trim();
    }
}
