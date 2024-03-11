package com.academy.ui.homePage;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.CarouselImgComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class HomePageWithoutLoginTest extends BaseTestRunner {

    private CarouselCardComponent carouselCardComponent;
    private CarouselImgComponent carouselImgComponent;
    private SoftAssert softAssert;

    @BeforeMethod()
    public void homePageSetUp() {
        carouselCardComponent = homePage.getCarouselCardComponent();
        carouselImgComponent = homePage.getCarouselImgComponent();
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify the Всі гуртки button near the carousel with suggested clubs on the right")
    @Issue("TUA-828")
    public void checkAllClubsButtonOpenClubsPage() {
        carouselCardComponent = homePage.getCarouselCardComponent();
        WebElement carouselCardComponentWebElement = homePage.getCarouselCardComponentWebElement();

        homePage.scrollToCarouselCardComponentWebElement();
        softAssert.assertTrue(carouselCardComponent.isElementPresent(carouselCardComponentWebElement));

        ClubsPage clubsPage = carouselCardComponent.clickCarouselCardAllClubsButton();
        clubsPage.waitUntilClubsPageIsLoaded(10);

        String actual = driver.getCurrentUrl();
        String expected = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/clubs";

        softAssert.assertEquals(expected, actual);
        softAssert.assertAll();
    }

    @Test(description = "Test fails because carousel imagines are absent")
    @Description("Verify that on the carousel slides are changing automatically")
    @Issue("TUA-833")
    public void checkCarouselSlidesChangingAutomatically() {
        // It's invalid test. For checking carousel with imagines use URL : https://speak-ukrainian.org.ua/
        HashMap<Integer, WebElement> carouselImgCards = carouselImgComponent.getCarouselImgCards();

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.attributeToBe(carouselImgCards.get(0), "class",
                "slick-slide slick-active slick-current"));

        actions.clickAndHold(carouselImgCards.get(0)).perform();
        softAssert.assertTrue(carouselImgComponent.getActiveCarouselImgCard()
                .getCardHeading().getText().contains("Єдині"));
        actions.release().perform();

        actions.clickAndHold(carouselImgCards.get(1)).perform();
        softAssert.assertTrue(carouselImgComponent.getActiveCarouselImgCard()
                .getCardHeading().getText().contains("Про гуртки українською"));
        actions.release().perform();

        actions.clickAndHold(carouselImgCards.get(2)).perform();
        softAssert.assertTrue(carouselImgComponent.getActiveCarouselImgCard()
                .getCardHeading().getText().contains("Навчай Українською"));
        actions.release().perform();

        softAssert.assertAll();
    }
}
