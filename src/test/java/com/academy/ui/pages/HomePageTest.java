package com.academy.ui.pages;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends BaseTestRunner {

    private CarouselCardComponent carouselCardComponent;
    private SoftAssert softAssert;

    @BeforeMethod
    public void homePageSetUp() {
        carouselCardComponent = homePage.getCarouselCardComponent();
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-856")
    public void check_allClubsButton_openClubsPage_ok() {
        carouselCardComponent = homePage.getCarouselCardComponent();
        WebElement carouselCardComponentWebElement = homePage.getCarouselCardComponentWebElement();

        Actions action = new Actions(driver);
        action.moveToElement(carouselCardComponentWebElement).perform();
        action.sendKeys(Keys.PAGE_DOWN).perform();

        softAssert.assertTrue(carouselCardComponent.isElementPresent(carouselCardComponentWebElement));

        ClubsPage clubsPage = new ClubsPage(driver);
        carouselCardComponent.getCarouselCardAllClubsButton().click();
        clubsPage.waitUntilClubsPageIsLoaded(10);

        String actual = driver.getCurrentUrl();
        String expected = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";

        softAssert.assertEquals(expected, actual);
        softAssert.assertAll();
    }
}
