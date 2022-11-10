package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

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
    private final By countryDropdown = By.id("billing_country");
    private final By stateDropdown = By.id("billing_state");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        // WebElement element = getElement(firstNameField);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        element.clear();
        element.sendKeys(firstName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }
    public CheckoutPage selectCountry(String countryName) {
        Select select = new Select(driver.findElement(countryDropdown));
        select.selectByVisibleText(countryName);
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
    public CheckoutPage selectState(String stateName) {
        Select select = new Select(driver.findElement(stateDropdown));
        select.selectByVisibleText(stateName);
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
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderButton).click();
        return this;
    }
    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }
    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }
    public CheckoutPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        return this;
    }
    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }
    public CheckoutPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
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
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }
}
