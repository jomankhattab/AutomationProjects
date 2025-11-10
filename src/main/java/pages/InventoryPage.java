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
    private  final  By cartIconLocator =  By.cssSelector("a[data-test=/'shopping-cart-link/']");
    private  final  By pageTitleLocator = By.className("app_logo");

   //Methods of the elements :

    public String getTitleText (){
        WebElement title = wait .until(ExpectedConditions.visibilityOfElementLocated(pageTitleLocator));
        return title.getText();
    }

    public boolean isCartIconDisplayed (){
        WebElement response = wait .until(ExpectedConditions.visibilityOfElementLocated(cartIconLocator));
        return  response.isDisplayed();
    }

    public int inventoryItemsCounter(){
        List<WebElement> items = driver.findElements(inventoryItemsLocator);
        return items.size();
    }

}
