package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderButton = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.className("showlogin");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.className("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }
    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(addressLineOneField).clear();
        driver.findElement(addressLineOneField).sendKeys(addressLineOne);
        return this;
    }
    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityField).clear();
        driver.findElement(billingCityField).sendKeys(city);
        return this;
    }
    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPostCodeField).clear();
        driver.findElement(billingPostCodeField).sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterEmail(String email) {
        driver.findElement(billingEmailField).clear();
        driver.findElement(billingEmailField).sendKeys(email);
        return this;
    }
    public CheckoutPage placeOrder() {
        List<WebElement> overlays = driver.findElements(overlay);
        if(overlays.size() > 0) {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
        }
        driver.findElement(placeOrderButton).click();
        return this;
    }
    public String getNotice() {
        return driver.findElement(successNotice).getText();
    }
    public CheckoutPage clickHereToLoginLink() {
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }
    public CheckoutPage enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }
    public CheckoutPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }
    public CheckoutPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }
    public CheckoutPage login(User user) {
        return enterUsername(user.getUsername())
                .enterPassword(user.getPassword())
                .clickLoginButton();
    }

    public CheckoutPage fillBillingInformation(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }
}
