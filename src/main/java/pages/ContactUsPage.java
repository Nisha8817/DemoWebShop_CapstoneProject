package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactUsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Contact us") private WebElement contactUsFooterLink;
    @FindBy(id = "FullName") private WebElement fullNameField;
    @FindBy(id = "Email") private WebElement emailField;
    @FindBy(id = "Enquiry") private WebElement enquiryField;
    @FindBy(name = "send-email") private WebElement submitEnquiryBtn;
    @FindBy(className = "result") private WebElement successConfirmationMessage;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsFooterLink)).click();
    }

    public void fillContactForm(String name, String email, String message) {
        wait.until(ExpectedConditions.visibilityOf(fullNameField)).clear();
        fullNameField.sendKeys(name);
        
        emailField.clear();
        emailField.sendKeys(email);
        
        enquiryField.clear();
        enquiryField.sendKeys(message);
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitEnquiryBtn)).click();
    }

    public String getSuccessConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOf(successConfirmationMessage)).getText().trim();
    }
}
