package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CategoryPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Computers')]") 
    private WebElement computersMenuLink;

    @FindBy(xpath = "//div[@class='sub-category-item']//a[contains(text(),'Desktops')]") 
    private WebElement desktopsSubCategoryGridLink;

    @FindBy(xpath = "//ul[@class='top-menu']//a[contains(text(),'Books')]") 
    private WebElement booksMenuLink;

    @FindBy(className = "page-title") 
    private WebElement pageHeaderTitle;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickCategory(String categoryName) {

        if (categoryName.equalsIgnoreCase("Computers")) {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            computersMenuLink))
                    .click();
        }

        else if (categoryName.equalsIgnoreCase("Books")) {

            driver.get("https://demowebshop.tricentis.com/books");

            wait.until(ExpectedConditions.urlContains("books"));
        }
    }

    public void clickDesktopsSubCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(desktopsSubCategoryGridLink)).click();
    }

    public String getPageHeader() {
        return wait.until(ExpectedConditions.visibilityOf(pageHeaderTitle)).getText().trim();
    }
}
