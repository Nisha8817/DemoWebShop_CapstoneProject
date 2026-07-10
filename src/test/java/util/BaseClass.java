package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class BaseClass {
    public static WebDriver driver;

    public static void invokeBrowser(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            System.out.println("Launching browser...");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        if(browser.equalsIgnoreCase("edge")){
            System.out.println("Launching browser...");
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

    }
    public static void closeBrowser(){
        driver.quit();
    }

}
 