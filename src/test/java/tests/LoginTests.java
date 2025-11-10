package tests;

import base.BaseTest;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.DataDriven;

public class LoginTests extends BaseTest {

    @Test(description = "Verify successful login with valid username and password ")
    public  void testValidLogin (){

        LoginPage login = new LoginPage(driver);
        JsonObject data = DataDriven.jsonReader();
        String username = data.getAsJsonObject("Valid").get("username").getAsString();
        String password = data.getAsJsonObject("Valid").get("Password").getAsString();

        login.verifyingLogin();
        login.validLoggingin(username,password);

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "User is not on Inventory page after login");

    }


    @Test (description = "Verify unsuccessful login with invalid data ")
    public void testInvalidLogin(){

        LoginPage login = new LoginPage(driver);
        JsonObject data = DataDriven.jsonReader();
        String username = data.getAsJsonObject("inValid").get("username").getAsString();
        String password = data.getAsJsonObject("inValid").get("Password").getAsString();

        login.verifyingLogin();
        login.validLoggingin(username,password);

        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"));
    }




    @Test (description = "Verify unsuccessful login with empty password ")
    public void testLoginWithEmptyPass() throws InterruptedException {

        LoginPage login = new LoginPage(driver);
        JsonObject data = DataDriven.jsonReader();
        String username = data.getAsJsonObject("inValid").get("username").getAsString();

        login.verifyingLogin();
        login.enterUsername(username);
        login.enterPassword("");
        login.clickLoginButtonAndGetError();
       // Thread.sleep(15000);

        System.out.println("Error message: " + login.getErrorMessage());
        Assert.assertTrue(login.getErrorMessage().contains("Password is required"));

    }


}
