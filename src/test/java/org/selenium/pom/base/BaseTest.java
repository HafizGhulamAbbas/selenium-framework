package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    @BeforeTest
    public void initDriver() {
        driver = new DriverManager().initializeDriver();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
