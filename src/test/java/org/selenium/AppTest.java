package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AppTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                searchProduct(searchFor);

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        storePage.clickAddToCardButton(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.
                checkout().
                fillBillingInformation(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(
                checkoutPage.getNotice(),
                "Thank you. Your order has been received."
        );
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("demouser2", "demopwd");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                searchProduct(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");

        storePage.clickAddToCardButton(product.getName());
        CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.
                login(user).
                fillBillingInformation(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(
                checkoutPage.getNotice(),
                "Thank you. Your order has been received."
        );
    }
}
