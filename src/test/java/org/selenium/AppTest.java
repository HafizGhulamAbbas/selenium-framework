package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer(){
        driver.get("https://askomdch.com/");

        HomePage homePage = new HomePage(driver);
        // Benefit of Fluent Interface
        StorePage storePage = homePage.clickStoreMenuLink(); // homePage.clickStoreMenuLink() will return a StorePage object. So, no need to instantiate StorePage object.
        storePage.enterProductInSearchField("Blue");
        storePage.clickSearchButton();
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.clickAddToCard();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
//        driver.findElement((By.cssSelector("button[value='Search']"))).click();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(
//                driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
//                "Search results: “Blue”"
//        );
//        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes"
        );
        driver.findElement(By.cssSelector(".checkout-button")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("John");
        driver.findElement(By.id("billing_last_name")).sendKeys("Adam");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("New York");
        driver.findElement(By.id("billing_postcode")).sendKeys("94148");
        driver.findElement(By.id("billing_email")).sendKeys("askomdch@gmail.com");
        driver.findElement(By.id("place_order")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),
                "Thank you. Your order has been received."
        );
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer(){
        driver.get("https://askomdch.com/");
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement((By.cssSelector("button[value='Search']"))).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
                "Search results: “Blue”"
        );
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes"
        );
        driver.findElement(By.cssSelector(".checkout-button")).click();
        driver.findElement(By.className("showlogin")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("username")).sendKeys("demouser2");
        driver.findElement(By.id("password")).sendKeys("demopwd");
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("John");
        driver.findElement(By.id("billing_last_name")).sendKeys("Adam");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("New York");
        driver.findElement(By.id("billing_postcode")).sendKeys("94148");
        driver.findElement(By.id("billing_email")).clear();
        driver.findElement(By.id("billing_email")).sendKeys("askomdch@gmail.com");
        driver.findElement(By.id("place_order")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),
                "Thank you. Your order has been received."
        );
    }
}
