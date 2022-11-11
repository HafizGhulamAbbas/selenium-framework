package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchInput = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");
    public StorePage(WebDriver driver) {
        super(driver);
    }
    public StorePage enterProductInSearchField(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(name);
        return this;
    }
    public StorePage clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return this;
    }
    public String getTitle() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }
    private By getAddToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }
    public StorePage clickAddToCardButton(String productName) {
        By addToCartButton = getAddToCartButtonElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return this;
    }
    public StorePage searchProduct(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return this;
    }

    // OR make the enterProductInSearchField and clickSearchButton as private and call them in a public class method

    public StorePage searchAProduct(String name) {
        enterProductInSearchField(name).clickSearchButton();
        return this;
    }
    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
