package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Computers')]")
    private WebElement computersMenuLink;

    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Desktops')]")
    private WebElement desktopsSubMenuLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://demowebshop.tricentis.com/");
    }
    
    public void navigateToDesktopsCategory() {
        // Actions API or direct navigation to match your screenshot layout precisely
        driver.get("https://demowebshop.tricentis.com/");
    }

    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }
}
