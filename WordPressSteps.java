package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.DownloadPage;
import pages.PhotoDirectoryPage;

public class WordPressSteps extends BaseTest {

    private HomePage homePage;
    private DownloadPage downloadPage;
    private PhotoDirectoryPage photoPage;
    private String lastSearchKeyword;

    @Given("I launch WordPress")
    public void i_launch_wordpress() {
        launchBrowser();             
        driver.get("https://wordpress.org/");
        homePage = new HomePage(driver);
    }

    @Then("I verify the home page title")
    public void i_verify_the_home_page_title() {
        String title = homePage.getTitle();
        Assert.assertTrue(title.contains("WordPress"), "Home page title does not contain 'WordPress'");
        System.out.println("Home page title verified: " + title);
    }

    @When("I hover on Extend and click Get WordPress")
    public void i_hover_on_extend_and_click_get_wordpress() {
        homePage.clickGetWordPress();
        downloadPage = new DownloadPage(driver);
    }

    @Then("I verify heading on Get WordPress page")
    public void i_verify_heading_on_get_wordpress_page() {
        String heading = downloadPage.getHeadingText();
        Assert.assertEquals(heading, "Get WordPress", "Heading mismatch!");
        System.out.println("Get WordPress heading verified: " + heading);
    }

    @When("I navigate to Photo Directory")
    public void i_navigate_to_photo_directory() {
        homePage.goToPhotoDirectory();
        photoPage = new PhotoDirectoryPage(driver);
    }

    @And("I search for {string}")
    public void i_search_for(String picName) {
        lastSearchKeyword = picName;
        photoPage.searchPhoto(picName);
    }

    @Then("I verify images are displayed")
    public void i_verify_images_are_displayed() throws InterruptedException {
        Assert.assertTrue(photoPage.areImagesDisplayed(), "No images found!");
        System.out.println("Images displayed successfully. Count = " + photoPage.getImageCount());

       
        String altText = photoPage.getAlternativeTextOfFirstImage();
        System.out.println("Alternative Text: " + altText);
        Assert.assertTrue(
            altText.toLowerCase().contains(lastSearchKeyword.toLowerCase()),
            "Alternative text does not contain the searched keyword!"
        );
        System.out.println("Alternative text contains search keyword: " + lastSearchKeyword);
        Thread.sleep(2000);
        closeBrowser();
    }
}
