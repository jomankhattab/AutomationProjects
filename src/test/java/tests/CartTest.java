package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;


public class CartTest extends BaseTest {

    @Test(description = "Verify empty cart")
    public void testEmptyCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        InventoryPage inventory = new InventoryPage(driver);
        inventory.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getItemsCount(), 0);
    }

    @Test(description = "Add 3 specific products")
    public void testAddThreeProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        InventoryPage inventory = new InventoryPage(driver);

        // Products
        String p1 = "Sauce Labs Backpack";
        String p2 = "Sauce Labs Bolt T-Shirt";
        String p3 = "Sauce Labs Onesie";

        inventory.clickProductButton(p1);
        inventory.clickProductButton(p2);
        inventory.clickProductButton(p3);

        inventory.clickCartIcon();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isItemInCart(p1));
        Assert.assertTrue(cartPage.isItemInCart(p2));
        Assert.assertTrue(cartPage.isItemInCart(p3));
    }


    @Test(description = "Remove item and verify button states")
    public void testRemoveOneProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        InventoryPage inventory = new InventoryPage(driver);

        // Products
        String p1 = "Sauce Labs Backpack";
        String p2 = "Sauce Labs Bolt T-Shirt";
        String p3 = "Sauce Labs Onesie";

        inventory.clickProductButton(p1);
        inventory.clickProductButton(p2);
        inventory.clickProductButton(p3);

        inventory.clickCartIcon();
        CartPage cartPage = new CartPage(driver);

        cartPage.removeItem(p2);


        cartPage.clickContinueShopping();
        InventoryPage refreshedInventory = new InventoryPage(driver); // إعادة إنشاء الصفحة


        // Bolt button should be "Add to cart"
        Assert.assertEquals(refreshedInventory.getButtonText(p2), "Add to cart");

        // Other two should be "Remove"
        Assert.assertEquals(refreshedInventory.getButtonText(p1), "Remove");
        Assert.assertEquals(refreshedInventory.getButtonText(p3), "Remove");
    }

}
