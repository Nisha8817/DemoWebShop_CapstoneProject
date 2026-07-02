package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait; 

    @FindBy(id = "BillingNewAddress_FirstName") private WebElement firstName;
    @FindBy(id = "BillingNewAddress_LastName") private WebElement lastName;
    @FindBy(id = "BillingNewAddress_Email") private WebElement email;
    @FindBy(id = "BillingNewAddress_CountryId") private WebElement countryDropdown;
    @FindBy(id = "BillingNewAddress_City") private WebElement city;
    @FindBy(id = "BillingNewAddress_Address1") private WebElement address1;
    @FindBy(id = "BillingNewAddress_ZipPostalCode") private WebElement zipCode;
    @FindBy(id = "BillingNewAddress_PhoneNumber") private WebElement phoneNumber;

    @FindBy(xpath = "//input[@button-id='checkout-shipping-address-load-next-step' or @onclick='Billing.save()' or contains(@class,'new-address-next-step-button')]") 
    private WebElement billingContinueBtn;
    
    @FindBy(xpath = "//input[@onclick='Shipping.save()']") private WebElement shippingContinueBtn;
    @FindBy(xpath = "//input[@onclick='ShippingMethod.save()']") private WebElement shippingMethodContinueBtn;
    @FindBy(xpath = "//input[@onclick='PaymentMethod.save()']") private WebElement paymentMethodContinueBtn;
    @FindBy(xpath = "//input[@onclick='PaymentInfo.save()']") private WebElement paymentInfoContinueBtn;
    @FindBy(xpath = "//input[@onclick='ConfirmOrder.save()']") private WebElement confirmOrderBtn;

    @FindBy(className = "field-validation-error") private WebElement validationError;
    @FindBy(xpath = "//div[@class='title']/strong") private WebElement orderSuccessMessage;
    @FindBy(id = "billing-address-select") private WebElement billingAddressDropdown;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); 
        PageFactory.initElements(driver, this);
    }

    public void clickBillingContinue() { 
        wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn));
        billingContinueBtn.click(); 
    }

    public boolean isValidationErrorDisplayed() { 
        wait.until(ExpectedConditions.visibilityOf(validationError));
        return validationError.isDisplayed(); 
    }

    // UPDATE 1: Safe form filler that clears and populates fields without failing on pre-filled text
    public void fillValidBillingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        new Select(countryDropdown).selectByVisibleText("United States");
        
        // Safety checks to ensure fields are empty before typing
        if(city.getAttribute("value").isEmpty()) city.sendKeys("New York");
        if(address1.getAttribute("value").isEmpty()) address1.sendKeys("123 Test Street");
        if(zipCode.getAttribute("value").isEmpty()) zipCode.sendKeys("10001");
        if(phoneNumber.getAttribute("value").isEmpty()) phoneNumber.sendKeys("1234567890");
        
        clickBillingContinue();
    }

    public void processRemainingSteps() throws InterruptedException {
        // Step 2: Wait until the Shipping panel expands and its save button is visible and clickable
        try {
            wait.until(ExpectedConditions.visibilityOf(shippingContinueBtn));
            wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
        } catch (Exception e) {
            Thread.sleep(2000); // Animation fallback
            wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
        }
        
        // Step 3: Wait for Shipping Method panel expansion
        try {
            wait.until(ExpectedConditions.visibilityOf(shippingMethodContinueBtn));
            wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn)).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn)).click();
        }
        
        // Step 4: Wait for Payment Method panel expansion
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentMethodContinueBtn));
            wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
        }
        
        // Step 5: Wait for Payment Information panel expansion
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentInfoContinueBtn));
            wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn)).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn)).click();
        }
        
        // Step 6: Wait for Confirm Order panel expansion
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmOrderBtn));
            wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
        } catch (Exception e) {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
        }
        
        Thread.sleep(2000); // Brief final pause to allow the transaction confirmation screen to load
    }

    public String getConfirmationMessage() { 
        wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        return orderSuccessMessage.getText(); 
    }

    public void selectExistingBillingAddressOrFill() {
        try {
            if (billingAddressDropdown.isDisplayed()) {
                new Select(billingAddressDropdown).selectByIndex(0);
                clickBillingContinue();
            } else {
                fillValidBillingDetails();
            }
        } catch (Exception e) {
            fillValidBillingDetails();
        }
    }
}
