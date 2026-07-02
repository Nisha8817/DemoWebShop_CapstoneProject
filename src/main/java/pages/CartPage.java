package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "ico-cart") private WebElement cartLink;
    @FindBy(id = "termsofservice") private WebElement termsCheckbox;
    @FindBy(id = "checkout") private WebElement checkoutBtn;
    @FindBy(xpath = "//input[@value='Checkout as Guest']") private WebElement guestCheckoutBtn;

    // SCENARIO 5 & 6 LOCATORS
    @FindBy(className = "product-name") private List<WebElement> productNamesInCart;
    @FindBy(css = "span.product-price") private WebElement cartTotalPrice;
    @FindBy(name = "discountcouponcode") private WebElement couponInputCode;
    @FindBy(name = "applydiscountcouponcode") private WebElement applyCouponBtn;
    @FindBy(className = "discount-total") private WebElement discountSummaryRow;
    
    // --- SCENARIO 9 LOCATORS ---
    @FindBy(className = "qty-input") 
    private WebElement quantityField;

    @FindBy(name = "updatecart") 
    private WebElement updateCartBtn;

    @FindBy(name = "removefromcart") 
    private WebElement removeFromCartCheckbox;

    @FindBy(className = "order-summary-content") 
    private WebElement emptyCartMessage;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        try {
            // 1. Wait briefly for any active overlay banner animations to fade out
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(
                org.openqa.selenium.By.id("bar-notification")));
            
            wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        } catch (Exception e) {
            // 2. FALLBACK: Execute a direct JavaScript click if a banner still intercepts the action
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", cartLink);
        }
    }


    public void acceptTermsAndCheckout() {
        if (!termsCheckbox.isSelected()) { termsCheckbox.click(); }
        checkoutBtn.click();
    }

    public void checkoutAsGuest() { guestCheckoutBtn.click(); }

    // NEW UTILITY METHODS
    public boolean verifyProductIsPresent(String name) {
        // Uses lowercase matching to prevent minor case formatting variations from failing tests
        return productNamesInCart.stream()
            .anyMatch(element -> element.getText().toLowerCase().contains(name.toLowerCase()));
    }


    public String getCartTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOf(cartTotalPrice)).getText();
    }

    public void applyCoupon(String code) {
        wait.until(ExpectedConditions.visibilityOf(couponInputCode)).clear();
        couponInputCode.sendKeys(code);
        applyCouponBtn.click();
    }

    public boolean isDiscountRowDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(discountSummaryRow)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
 // --- SCENARIO 9 ACTIONS ---
    public void updateQuantity(String qty) {
        wait.until(ExpectedConditions.visibilityOf(quantityField)).clear();
        quantityField.sendKeys(qty);
    }

    public void clickUpdateCart() {
        wait.until(ExpectedConditions.elementToBeClickable(updateCartBtn)).click();
    }

    public void checkRemoveProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartCheckbox));
        if (!removeFromCartCheckbox.isSelected()) {
            removeFromCartCheckbox.click();
        }
    }

    public String getEmptyCartMessageText() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCartMessage)).getText().trim();
    }
}
