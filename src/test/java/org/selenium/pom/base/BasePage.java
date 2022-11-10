package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitShort;
    protected WebDriverWait waitLong;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /*waitShort = new WebDriverWait(driver, Duration.ofSeconds(8));
        waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));*/
    }
    public void load(String endpoint) {
        driver.get("https://askomdch.com/" + endpoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        if(overlays.size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
        }
    }

    /*public WebElement getElement(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }*/
}
