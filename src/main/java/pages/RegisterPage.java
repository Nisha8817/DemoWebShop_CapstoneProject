package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "ico-register") private WebElement registerLink;
    @FindBy(id = "gender-male") private WebElement genderMaleRadio;
    @FindBy(id = "FirstName") private WebElement firstNameField;
    @FindBy(id = "LastName") private WebElement lastNameField;
    @FindBy(id = "Email") private WebElement emailField;
    @FindBy(id = "Password") private WebElement passwordField;
    @FindBy(id = "ConfirmPassword") private WebElement confirmPasswordField;
    @FindBy(id = "register-button") private WebElement registerBtn;
    @FindBy(className = "field-validation-error") private WebElement registrationFieldError;
    @FindBy(className = "result") private WebElement successResultMsg;
    @FindBy(className = "ico-logout") private WebElement logoutLink;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToRegister() {
        // If logged in from a previous run, log out first to access the registration links cleanly
        try {
            if (logoutLink.isDisplayed()) { logoutLink.click(); }
        } catch (Exception e) { /* Not logged in; proceed safely */ }
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
    }

    public boolean isFieldErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(registrationFieldError));
        return registrationFieldError.isDisplayed();
    }

    public void fillRegistrationDetails(String fName, String lName, String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(genderMaleRadio)).click();
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        clickRegisterButton();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successResultMsg));
        return successResultMsg.getText();
    }
}
