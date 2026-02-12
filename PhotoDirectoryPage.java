package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhotoDirectoryPage {

    private WebDriver driver;

    private By searchBox = By.id("wp-block-search__input-8");
    private By searchButton = By.xpath("(//button[@aria-label='Search'])[2]");
    private By imageResults = By.xpath("//ul[contains(@class,'wp-block-post-template')]//li//a");
    private By altTextParagraph = By.xpath("//p[span[text()='Alternative Text:']]");

    public PhotoDirectoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchPhoto(String photoName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(photoName);
        driver.findElement(searchButton).click();
    }

    public boolean areImagesDisplayed() {
        List<WebElement> images = driver.findElements(imageResults);
        return images.size() > 0;
    }

    public int getImageCount() {
        return driver.findElements(imageResults).size();
    }

  
    public String getAlternativeTextOfFirstImage() {
        List<WebElement> images = driver.findElements(imageResults);
        if (images.isEmpty()) return "";

        WebElement firstImage = images.get(0);
        firstImage.click();

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement altTextElement = driver.findElement(altTextParagraph);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", altTextElement);

        return altTextElement.getText();
    }
}
