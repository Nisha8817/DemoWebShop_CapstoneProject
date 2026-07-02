package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[contains(@id, 'add-to-cart-button')]")
    private WebElement addToCartBtn;

    @FindBy(css = "span.cart-qty")
    private WebElement cartQuantity;

    @FindBy(xpath = "//div[@id='bar-notification']//a[text()='shopping cart']")
    private WebElement bannerShoppingCartLink;

    // MANDATORY CONFIGURATION LOCATORS FOR SIMPLE COMPUTER
    @FindBy(xpath = "//label[contains(text(),'Slow') or contains(text(),'Fast')]/preceding-sibling::input[@type='radio']")
    private WebElement processorRadioBtn;
    
    //Scenario 10
    @FindBy(xpath = "//*[contains(@id, 'add-to-wishlist')]")
    private WebElement addToWishlistBtn;
    
    //Scenario 11
    @FindBy(xpath = "//input[@value='Add to compare list']")
    private WebElement addToCompareListBtn;

   
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void selectRequiredOptionsIfPresent() {
        try {
            // Check if a processor selection layout is present on the active product page view
            if (processorRadioBtn.isDisplayed()) {
                processorRadioBtn.click();
            }
        } catch (Exception e) {
            // Element not present on current item (e.g. cheap computer); bypass safely
        }
    }

    public void clickAddToCart() {
        // Select configurations before adding to the cart
        selectRequiredOptionsIfPresent();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }

    public void clickBannerShoppingCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(bannerShoppingCartLink));
        bannerShoppingCartLink.click();
    }

    public String getCartQuantity() {
        wait.until(ExpectedConditions.visibilityOf(cartQuantity));
        return cartQuantity.getText();
    }
    
    //Scenario 10
    public void clickAddToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistBtn)).click();
    }
    
    //Scenario 11
    public void clickAddToCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCompareListBtn)).click();
    }

}
