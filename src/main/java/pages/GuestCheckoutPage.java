package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GuestCheckoutPage {
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

    @FindBy(xpath = "//input[@onclick='Billing.save()']") private WebElement billingContinueBtn;
    @FindBy(xpath = "//input[@onclick='Shipping.save()']") private WebElement shippingContinueBtn;
    @FindBy(xpath = "//input[@onclick='ShippingMethod.save()']") private WebElement shippingMethodContinueBtn;
    @FindBy(xpath = "//input[@onclick='PaymentMethod.save()']") private WebElement paymentMethodContinueBtn;
    @FindBy(xpath = "//input[@onclick='PaymentInfo.save()']") private WebElement paymentInfoContinueBtn;
    @FindBy(xpath = "//input[@onclick='ConfirmOrder.save()']") private WebElement confirmOrderBtn;
    
    @FindBy(className = "field-validation-error") private WebElement validationError;
    @FindBy(xpath = "//div[@class='title']/strong") private WebElement orderSuccessMessage;

    public GuestCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickBillingContinue() { 
        wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click(); 
    }

    public boolean isValidationErrorDisplayed() { 
        return wait.until(ExpectedConditions.visibilityOf(validationError)).isDisplayed(); 
    }

    public void fillGuestBillingDetails() {
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys("johndoe@test.com");
        new Select(countryDropdown).selectByVisibleText("United States");
        city.sendKeys("New York");
        address1.sendKeys("123 Test Street");
        zipCode.sendKeys("10001");
        phoneNumber.sendKeys("1234567890");
        clickBillingContinue();
    }

    public void processGuestSteps() throws InterruptedException {
        Thread.sleep(2000); wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
        Thread.sleep(2000); wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn)).click();
        Thread.sleep(2000); wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
        Thread.sleep(2000); wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn)).click();
        Thread.sleep(2000); wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
    }

    public String getConfirmationMessage() { 
        return wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage)).getText(); 
    }
}
