package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManager {
    public WebDriver initializeDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser");
        switch (DriverType.valueOf(browser.toLowerCase())) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException("Invalid Browser name " + browser);
        }
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
