package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CartPage;


import java.util.ArrayList;


public class InventoryTests extends BaseTest {

    @Test(description = "Verify Inventory Page")
    public void verifyInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);

        try {
            Assert.assertEquals(inventoryPage.getTitleText(), "Swag Labs");

        } catch (AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        try {
            Assert.assertTrue(inventoryPage.isCartIconDisplayed());
        } catch (AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        try {
            Assert.assertEquals(inventoryPage.inventoryItemsCounter(), 6);
        } catch (AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    @Test(description = "Verify social links URLs")
    public void VerifySocialLinksURLs()throws InterruptedException {
       // loginPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        InventoryPage inventory = new InventoryPage(driver);
        inventory.clickLinkedInIcon();
        Thread.sleep(1500); // allow new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));
        driver.close();
        driver.switchTo().window(tabs.get(0));

        // Facebook
        inventory.clickFacebookIcon();
        Thread.sleep(1500);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
        driver.close();
        driver.switchTo().window(tabs.get(0));

        // Twitter / X
        inventory.clickTwitterIcon();
        Thread.sleep(1500);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertTrue(
                driver.getCurrentUrl().contains("twitter") ||
                        driver.getCurrentUrl().contains("x.com")
        );
        driver.close();
        driver.switchTo().window(tabs.get(0));
//
//        verifySocialLink(inventory::clickTwitterIcon,"x");
//        verifySocialLink(inventory::clickFacebookIcon,"facebook");
//        verifySocialLink(inventory::clickLinkedInIcon,"linkedin");
//


    }


//    public void verifySocialLink(Runnable clickAction, String... expectedKeywords) throws InterruptedException
//    {
//        clickAction.run();
//        Thread.sleep(1500);
//
//        ArrayList<String> tabs =new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(tabs.size() - 1));
//        String currentURL = driver.getCurrentUrl().toLowerCase();
//        System.out.println("Opened URL: " + currentURL);
//
//
//        boolean match = false;
//        for (String word : expectedKeywords){
//            if (currentURL.contains(word.toLowerCase())){
//                match = true;
//                break;
//            }
//        }
//
//        Assert.assertTrue(match, "URL does not match expected platform!");
//
//        driver.close();
//
//        try {
//            driver.switchTo().alert().accept();
//        } catch (Exception e) {
//
//        }
//
//        driver.switchTo().window(tabs.get(0));
//    }

    @Test(description = "Add 3 specific products")
    public void testAddThreeProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        InventoryPage inventory = new InventoryPage(driver);

        String p1 = "Sauce Labs Backpack";
        String p2 = "Sauce Labs Bolt T-Shirt";
        String p3 = "Sauce Labs Onesie";

        inventory.clickProductButton(p1);
        inventory.clickProductButton(p2);
        inventory.clickProductButton(p3);

        inventory.clickCartIcon();

        CartPage cartPage = new CartPage(driver);



    }
}




