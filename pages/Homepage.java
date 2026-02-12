package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;
    private Actions actions;

    private By extendMenu = By.xpath("//span[text()='Extend']");
    private By getWordPressOption = By.xpath("//a[normalize-space(text())='Get WordPress']");
    private By communityMenu = By.xpath("//span[text()='Community']");
    private By photoDirectoryOption = By.xpath("//a//span[text()='Photo Directory']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickGetWordPress() {
        actions.moveToElement(driver.findElement(extendMenu)).perform();
        WebElement menu = driver.findElement(extendMenu);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.background='green'; arguments[0].style.border='3px solid red';",
            menu);
        driver.findElement(getWordPressOption).click();
    }

    public void goToPhotoDirectory() {
        driver.findElement(communityMenu).click();
        driver.findElement(photoDirectoryOption).click();
    }
}
