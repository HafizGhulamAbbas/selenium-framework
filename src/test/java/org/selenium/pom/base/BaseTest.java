package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    @Parameters("browser")
    @BeforeTest
    public void initDriver(String browser) {
        driver = new DriverManager().initializeDriver(browser);
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
