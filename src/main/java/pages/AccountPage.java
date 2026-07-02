package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "account")
    private WebElement customerInfoLink;

    @FindBy(css = ".order-list .section.order-item")
    private List<WebElement> orderHistoryCardsRows;

    @FindBy(xpath = "//input[@value='Details']")
    private WebElement firstOrderDetailsBtn;

    @FindBy(className = "pdf-order-button")
    private WebElement pdfInvoiceBtn;
    
 // --- SCENARIO 16 LOCATORS ---
    @FindBy(id = "FirstName") 
    private WebElement profileFirstNameField;

    @FindBy(id = "LastName") 
    private WebElement profileLastNameField;

    @FindBy(name = "save-info-button") 
    private WebElement saveInfoBtn;
    
    // --- SCENARIO 17 LOCATORS ---
    @FindBy(id = "newsletter-email")
    private WebElement newsletterEmailBox;

    @FindBy(id = "newsletter-subscribe-button")
    private WebElement newsletterSubscribeBtn;

    @FindBy(id = "newsletter-result-block")
    private WebElement newsletterResultBlock;


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyAccount() {
        driver.get("https://demowebshop.tricentis.com/customer/info");
    }

    public void clickOrdersHistoryLink() {
        driver.get("https://demowebshop.tricentis.com/customer/orders");
        wait.until(ExpectedConditions.urlContains("/customer/orders"));
    }

    public boolean isAnyOrderVisibleInHistory() {

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(orderHistoryCardsRows));
            return orderHistoryCardsRows.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void openFirstOrderDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(firstOrderDetailsBtn)).click();
    }

    public void triggerInvoiceDownload() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfInvoiceBtn)).click();
    }
    
    // --- SCENARIO 16 ACTIONS ---
    public void modifyProfileDetails(String updatedFirstName, String updatedLastName) {
        wait.until(ExpectedConditions.visibilityOf(profileFirstNameField)).clear();
        profileFirstNameField.sendKeys(updatedFirstName);
        
        profileLastNameField.clear();
        profileLastNameField.sendKeys(updatedLastName);
    }

    public void clickSaveInfoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveInfoBtn)).click();
    }

    public String getSavedFirstNameValue() {
        return wait.until(ExpectedConditions.visibilityOf(profileFirstNameField)).getAttribute("value");
    }

    public String getSavedLastNameValue() {
        return profileLastNameField.getAttribute("value");
    }
    
 // --- SCENARIO 17 ACTIONS ---

    public void subscribeNewsletter(String email) {

        driver.get("https://demowebshop.tricentis.com/");

        wait.until(ExpectedConditions.visibilityOf(newsletterEmailBox));

        newsletterEmailBox.clear();
        newsletterEmailBox.sendKeys(email);

        newsletterSubscribeBtn.click();

        wait.until(ExpectedConditions.visibilityOf(newsletterResultBlock));
    }

    public void unsubscribeNewsletter(String email) {

        driver.get("https://demowebshop.tricentis.com/");

        wait.until(ExpectedConditions.visibilityOf(newsletterEmailBox));

        newsletterEmailBox.clear();
        newsletterEmailBox.sendKeys(email);

        newsletterSubscribeBtn.click();

        wait.until(ExpectedConditions.visibilityOf(newsletterResultBlock));
    }

    public boolean isNewsletterSuccessDisplayed() {

        return newsletterResultBlock.isDisplayed();
    }
}