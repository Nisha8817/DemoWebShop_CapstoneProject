package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "ico-login") private WebElement loginLink;
    @FindBy(id = "Email") private WebElement emailField;
    @FindBy(id = "Password") private WebElement passwordField;
    @FindBy(xpath = "//input[@value='Log in']") private WebElement loginBtn;
    @FindBy(className = "validation-summary-errors") private WebElement loginSummaryError;
    
    // Exact class 'account' link element used on the Demo Web Shop portal layout
    @FindBy(className = "account") private WebElement myAccountLink;
    @FindBy(className = "ico-logout") private WebElement logoutLink;
    
    // --- SCENARIO 8 LOCATORS ---
    @FindBy(linkText = "Forgot password?") 
    private WebElement forgotPasswordLink;

    @FindBy(id = "Email") 
    private WebElement recoveryEmailField; // Uses the same ID as login email

    @FindBy(name = "send-email") 
    private WebElement passwordRecoveryBtn;

    @FindBy(className = "result") 
    private WebElement notificationBarResult;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLogin() {
        // Force an active sign-out session check to clear residual states
        try {
            if (logoutLink.isDisplayed()) { 
                logoutLink.click(); 
            }
        } catch (Exception e) { 
            /* Not authenticated; ignore and proceed safely */ 
        }
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void enterCredentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginBtn.click();
    }

    public boolean isSummaryErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginSummaryError));
        return loginSummaryError.isDisplayed();
    }

    public boolean isMyAccountDisplayed() {
        try {
            // Short explicit wait to confirm dashboard state without throwing full timeout blocks
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return shortWait.until(ExpectedConditions.visibilityOf(myAccountLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ADDED FOR SCENARIO 7: LOGOUT EXECUTION AND POST-LOGOUT VISIBILITY CHECKS

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public boolean isLoginLinkDisplayed() {
        // Once logged out successfully, the header "Log in" link becomes visible again
        return wait.until(ExpectedConditions.visibilityOf(loginLink)).isDisplayed();
    }
    
    // --- SCENARIO 8 ACTIONS ---
    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    public void enterRecoveryEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(recoveryEmailField)).clear();
        recoveryEmailField.sendKeys(email);
    }

    public void clickRecoverButton() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordRecoveryBtn)).click();
    }

    public String getRecoveryNotificationText() {
        return wait.until(ExpectedConditions.visibilityOf(notificationBarResult)).getText();
    }
}
