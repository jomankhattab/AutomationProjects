package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver ;
    private WebDriverWait wait;

    public CartPage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver , Duration.ofSeconds(15));
    }

    private By cartItems = By.className("cart_item");
    private By itemName = By.className("inventory_item_name");
    private By continueShoppingBtn = By.id("continue-shopping");


    private By removeButton(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'remove')]");
    }


    public boolean isItemInCart(String itemNameText) {
        List <WebElement> items = driver.findElements(itemName);
        for (var item : items) {
            if (item.getText().equals(itemNameText))
                return true;
        }
        return false;
    }

    public void removeItem(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button[contains(@id,'remove')]");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }



    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
        continueBtn.click();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForPageToLoad();
    }


    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }
}
