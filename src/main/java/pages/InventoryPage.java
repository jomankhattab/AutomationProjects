package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private WebDriver driver ;
    private WebDriverWait wait;

    public InventoryPage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver , Duration.ofSeconds(15));
    }

   // locators of the elements :
    private  final  By inventoryItemsLocator = By.id("inventory_container");
    private  final  By cartIconLocator =  By.cssSelector("a[data-test='shopping-cart-link']");
    private  final  By pageTitleLocator = By.className("app_logo");
    // New Features :
    private  final   By twitterIconLocator = By.cssSelector("a[data-test='social-twitter']");
    private  final   By facebookIconLocator = By.cssSelector("a[data-test='social-facebook']");
    private  final   By linkedInIconLocator = By.cssSelector("a[data-test='social-linkedin']");

    private By productButton(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]");
    }


    //Methods of the elements :

    public String getTitleText (){
        WebElement title = wait .until(ExpectedConditions.visibilityOfElementLocated(pageTitleLocator));
        return title.getText();
    }

    public boolean isCartIconDisplayed (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(cartIconLocator));
        return  response.isDisplayed();
    }

    public CartPage clickCartIcon (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(cartIconLocator));
        response.click();
        return  new CartPage(driver);
    }

    public int inventoryItemsCounter(){
        List<WebElement> items = driver.findElements(inventoryItemsLocator);
        return items.size();
    }

    public void clickFacebookIcon (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(facebookIconLocator));
         response.click();
    }

    public void clickLinkedInIcon (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(linkedInIconLocator));
        response.click();
    }

    public void clickTwitterIcon (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(twitterIconLocator));
        response.click();
    }

    public void clickProductButton(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By buttonLocator = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
    }

    public String getButtonText(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By buttonLocator = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator)).getText();
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
    }

}
