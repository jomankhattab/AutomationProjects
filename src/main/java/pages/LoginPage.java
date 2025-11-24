package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

      private WebDriver driver ;
      private WebDriverWait wait;

 //login constructor :

 public LoginPage (WebDriver driver){
     this.driver = driver;
     wait = new WebDriverWait(driver , Duration.ofSeconds(15));
 }



    // locators of the elements:

    private final By loginVerificationLocator = By.className("login_logo");
    private final By usernameInputLocator = By.id("user-name");
    private final By passwordInputLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMesssageLocator = By.cssSelector("h3[data-test='error']");



    //Methods of the locators

    public boolean verifyingLogin() {
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(loginVerificationLocator));
        return element.isDisplayed();
    }

   public void enterUsername (String username){
     WebElement element = wait .until(ExpectedConditions.visibilityOfElementLocated(usernameInputLocator));
       element.clear();
       element.sendKeys(username);
    }

    public void enterPassword (String password){
        WebElement element= wait .until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
        element.clear();
        element.sendKeys(password);
    }

    public InventoryPage clickLoginButton (){
        WebElement button= wait .until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        button.click();
        return new InventoryPage(driver) ;
    }

    public String clickLoginButtonAndGetError() {
        clickLoginButton();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMesssageLocator));
        return message.getText();
    }


    public InventoryPage validLoggingin(String username , String password ){
        enterUsername( username);
        enterPassword (password);
        clickLoginButton();
        return new InventoryPage(driver);

    }

    public String getErrorMessage() {
        WebElement message  =wait.until(ExpectedConditions.visibilityOfElementLocated(errorMesssageLocator));
        return message.getText();
    }
}