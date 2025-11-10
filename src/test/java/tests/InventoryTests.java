package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;


public class InventoryTests extends BaseTest {

    @Test
    public void verifyInventoryPage (){
        InventoryPage inventoryPage = new InventoryPage(driver);

        try {
            Assert.assertEquals(inventoryPage.getTitleText(),"Swag Labs");

        }
        catch ( AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e ;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e ;
        }

        try {
            Assert.assertTrue(inventoryPage.isCartIconDisplayed());
        }
        catch ( AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e ;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e ;
        }

        try {
            Assert.assertEquals(inventoryPage.inventoryItemsCounter(), 6);        }
        catch ( AssertionError e) {
            System.out.println("Test failed : " + e.getMessage());
            throw e ;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e ;
        }

    }

}


