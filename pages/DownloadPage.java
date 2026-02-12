package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadPage {

    private WebDriver driver;

    private By pageHeading = By.xpath("//h1[normalize-space(text())='Get WordPress']");

    public DownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeadingText() {
        return driver.findElement(pageHeading).getText();
    }
}

