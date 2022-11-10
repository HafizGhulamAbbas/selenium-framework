package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(searchInput).sendKeys(name);
        return this;
    }
    public StorePage clickSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }
    public String getTitle() {
        return driver.findElement(title).getText();
    }
    private By getAddToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }
    public StorePage clickAddToCardButton(String productName) {
        By addToCartButton = getAddToCartButtonElement(productName);
        driver.findElement(addToCartButton).click();
        return this;
    }
    public StorePage searchProduct(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(searchInput).sendKeys(name);
        driver.findElement(searchButton).click();
        return this;
    }

    // OR make the enterProductInSearchField and clickSearchButton as private and call them in a public class method

    public StorePage searchAProduct(String name) {
        enterProductInSearchField(name).clickSearchButton();
        return this;
    }
    public CartPage clickViewCart() {
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
}
