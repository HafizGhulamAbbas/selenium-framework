package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class AppTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu()
                .searchProduct("Blue");

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.clickAddToCardButton(product.getName());
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.
                checkout().
                fillBillingInformation(billingAddress)
                .placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(
                checkoutPage.getNotice(),
                "Thank you. Your order has been received."
        );
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu()
                .searchProduct("Blue");
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
    }
}
