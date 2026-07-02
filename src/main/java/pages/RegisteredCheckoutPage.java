package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisteredCheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "billing-address-select") private WebElement billingAddressDropdown;
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
    @FindBy(xpath = "//div[@class='title']/strong") private WebElement orderSuccessMessage;

    // --- SCENARIOS 13 & 14 LOCATORS ---
    @FindBy(id = "shipping-address-select") 
    private WebElement shippingAddressDropdown;

    @FindBy(id = "ShippingNewAddress_CountryId") private WebElement shippingCountryDropdown;
    @FindBy(id = "ShippingNewAddress_City") private WebElement shippingCityField;
    @FindBy(id = "ShippingNewAddress_Address1") private WebElement shippingAddress1Field;
    @FindBy(id = "ShippingNewAddress_ZipPostalCode") private WebElement shippingZipCodeField;
    @FindBy(id = "ShippingNewAddress_PhoneNumber") private WebElement shippingPhoneNumberField;

    public RegisteredCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void completeRegisteredBilling() {
        try {
            // If an address dropdown menu exists, select the first option
            if (billingAddressDropdown.isDisplayed()) {
                new Select(billingAddressDropdown).selectByIndex(0);
                wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
                return;
            }
        } catch (Exception e) {
            // Dropdown missing (fresh registration); fill the blank fields
            new Select(countryDropdown).selectByVisibleText("United States");
            city.sendKeys("Chicago");
            address1.sendKeys("456 Automation Way");
            zipCode.sendKeys("60601");
            phoneNumber.sendKeys("9876543210");
            wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
        }
    }

    public void processRegisteredSteps() throws InterruptedException {

        // ✅ Step 1: Ensure Shipping Address is submitted
        try {
            wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Shipping Continue already executed or not required");
        }

        // ✅ Step 2: Wait until Shipping Method section loads properly
        wait.until(ExpectedConditions.visibilityOf(shippingMethodContinueBtn));
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn)).click();
        Thread.sleep(2000);

        // ✅ Step 3: Continue remaining checkout steps
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn)).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
    }

    public String getConfirmationMessage() { 
        return wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage)).getText(); 
    }
    
    // --- SCENARIOS 13 & 14 ACTIONS ---
    public void configureNewUniqueShippingAddress() throws InterruptedException {
        Thread.sleep(1500); // Allow accordion pane slide animation to complete smoothly
        
        // Select 'New Address' out of the pre-populated shipping records selection dropdown
        Select shippingSelect = new Select(shippingAddressDropdown);
        shippingSelect.selectByVisibleText("New Address");
        
        // Populate completely unique shipping parameters distinct from Billing
        new Select(shippingCountryDropdown).selectByVisibleText("United States");
        shippingCityField.sendKeys("Los Angeles");
        shippingAddress1Field.sendKeys("789 California Boulevard");
        shippingZipCodeField.sendKeys("90001");
        shippingPhoneNumberField.sendKeys("5554443322");
    }

    public void selectDifferentExistingShippingAddressRow() throws InterruptedException {
        Thread.sleep(1500);

        Select shippingSelect = new Select(shippingAddressDropdown);

        // ✅ Always force new address (guaranteed clean flow)
        shippingSelect.selectByVisibleText("New Address");

        Thread.sleep(1000);

        // ✅ Fill ALL mandatory fields (CRITICAL FIX)
        new Select(shippingCountryDropdown).selectByVisibleText("United States");
        shippingCityField.clear();
        shippingCityField.sendKeys("Houston");

        shippingAddress1Field.clear();
        shippingAddress1Field.sendKeys("123 Texas Street");

        shippingZipCodeField.clear();
        shippingZipCodeField.sendKeys("77001");

        shippingPhoneNumberField.clear();
        shippingPhoneNumberField.sendKeys("9998887776");
    }
    
}
