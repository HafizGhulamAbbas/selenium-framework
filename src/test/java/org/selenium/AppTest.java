package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com/");

        HomePage homePage = new HomePage(driver);
        // Benefit of Fluent Interface
        StorePage storePage = homePage.navigateToStoreUsingMenu(); // homePage.clickStoreMenuLink() will return a StorePage object. So, no need to instantiate StorePage object.

        // Structural Page Objects
//        storePage
//                .enterProductInSearchField("Blue")
//                .clickSearchButton();

        // Functional Page Objects
        storePage.searchProduct("Blue");

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.clickAddToCardButton("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
        CheckoutPage checkoutPage = cartPage.checkout();

        // Functional Page Objects
        // checkoutPage.fillBillingInformation("John", "Adam", "San Francisco", "New York", "94148", "askomdch@gmail.com"); // not implemented

        // Structural Page Objects using Builder Pattern
        checkoutPage
                .enterFirstName("John")
                .enterLastName("Adam")
                .enterAddressLineOne("San Francisco")
                .enterCity("New York")
                .enterPostCode("94148")
                .enterEmail("askomdch@gmail.com")
                .placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(
                checkoutPage.getNotice(),
                "Thank you. Your order has been received."
        );
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com/");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.searchProduct("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        storePage.clickAddToCardButton("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(3000);
        checkoutPage
                .login("demouser2", "demopwd")
                .enterFirstName("John")
                .enterLastName("Adam")
                .enterAddressLineOne("San Francisco")
                .enterCity("New York")
                .enterPostCode("94148")
                .enterEmail("askomdch@gmail.com")
                .placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(
                checkoutPage.getNotice(),
                "Thank you. Your order has been received."
        );


//        driver.findElement(By.id("username")).sendKeys("demouser2");
//        driver.findElement(By.id("password")).sendKeys("demopwd");
//        driver.findElement(By.className("login")).click();
//        driver.findElement(By.id("billing_first_name")).sendKeys("John");
//        driver.findElement(By.id("billing_last_name")).sendKeys("Adam");
//        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
//        driver.findElement(By.id("billing_city")).sendKeys("New York");
//        driver.findElement(By.id("billing_postcode")).sendKeys("94148");
//        driver.findElement(By.id("billing_email")).clear();
//        driver.findElement(By.id("billing_email")).sendKeys("askomdch@gmail.com");
//        driver.findElement(By.id("place_order")).click();
    }
}
