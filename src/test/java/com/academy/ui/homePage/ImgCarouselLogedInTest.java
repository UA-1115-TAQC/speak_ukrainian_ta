package com.academy.ui.homePage;

import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.challenges.ChallengeTeachInUkrainian;
import com.academy.ui.pages.challenges.ChallengeUnited;
import com.academy.ui.runners.LogInWithUserTestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ImgCarouselLogedInTest extends LogInWithUserTestRunner {
    SoftAssert softAssert;
    WebDriverWait wait;
    Actions actions;
    @BeforeMethod
    public void init(){
        softAssert=new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        actions = new Actions(driver);
        homePage = new HomePage(driver);
    }
    @Test(description = "TUA-845")
    public void checkThatSlickDotsContainerIsCentered(){
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotsContainer().getCssValue("justify-content").contains("center"));
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void checkSizeOfImagesInCarousel(){
        for(int i = 0; i<homePage.carouselImgComponent.getCarouselImgCards().size(); i++){
            verifyImgCardSize(i);
            homePage.carouselImgComponent.clickRightArrowButton();
        }
        softAssert.assertAll();
    }
    private void verifyImgCardSize(int i){
        softAssert.assertEquals(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getBackgroundImage().getSize().getWidth(), 1268,
                "The active image width doesn't equal 1268 in the card with data index "+i);
        softAssert.assertEquals(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getBackgroundImage().getSize().getHeight(), 400,
                "The active image height doesn't equal 400 in the card with data index "+i);
    }
    @Test(description = "TUA-845")
    public void checkColoringOfActiveOrInactiveIndicatorUnderCarousel(){
        homePage.carouselImgComponent.clickSlickDotByIndex(0);
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotByIndex(0).getCssValue("background").contains("rgb(250, 140, 22)"),
                "The first dot indicator under carousel isn't highlighted in orange by default when active");
        homePage.carouselImgComponent.clickSlickDotByIndex(1);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.carouselImgComponent.getSlickDotByIndex(1)));
        wait.until(ExpectedConditions.attributeContains(homePage.carouselImgComponent.getSlickDotByIndex(1), "background", "rgb(250, 140, 22)"));
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotByIndex(0).getCssValue("background").contains("rgb(196, 196, 196)"),
                "The first dot indicator under carousel isn't highlighted in grey by default when inactive");
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void checkThatCarouselIsCentered(){
        softAssert.assertTrue(homePage.carouselImgComponent.getWebElement().getCssValue("align-items").contains("center"));
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void checkThatTextOnCarouselImgCardIsCentered(){
        for(int i = 0; i<homePage.carouselImgComponent.getCarouselImgCards().size(); i++) {
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardHeading().getCssValue("text-align").contains("center"));
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardText().getCssValue("text-align").contains("center"));
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButtonText().getCssValue("text-align").contains("center"));
            homePage.carouselImgComponent.clickRightArrowButton();
        }
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void checkThatAllElementsArePresentImgCarouselAccordingToMockup(){
        softAssert.assertTrue(homePage.carouselImgComponent.getLeftArrowButton().isDisplayed());
        softAssert.assertTrue(homePage.carouselImgComponent.getRightArrowButton().isDisplayed());
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotsContainer().isDisplayed());
        for(int i = 0; i<homePage.carouselImgComponent.getCarouselImgCards().size(); i++){
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardHeading().isDisplayed());
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardText().isDisplayed());
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButton().isDisplayed());
            homePage.carouselImgComponent.clickRightArrowButton();
        }
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void checkHoverEffectsFindOutMoreButton(){
        for(int i = 0; i<homePage.carouselImgComponent.getCarouselImgCards().size(); i++) {
            if (!homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButton().isDisplayed()) {
                wait.until(ExpectedConditions.visibilityOf(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButton()));
            }
            softAssert.assertTrue(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButtonText().getCssValue("color").contains("rgba(255, 255, 255, 1)"),
                    "The card button text isn't white by default");
            homePage.carouselImgComponent.clickSlickDotByIndex(i);
            if (!homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButton().isDisplayed()) {
                wait.until(ExpectedConditions.visibilityOf(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButton()));
            }
            actions.moveToElement(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButtonText());
            actions.build().perform();
            softAssert.assertFalse(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(i).getCardButtonText().getCssValue("background").contains("rgba(255, 255, 255, 1)"),
                    "The card button text hasn't changed after hover");
            homePage.carouselImgComponent.clickRightArrowButton();
        }
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void verifyImgcarouselButton1() {
        homePage.carouselImgComponent.getCarouselImgCardByDataIndex(0).clickCardButton();
        ChallengeUnited challengeUnited = new ChallengeUnited(driver);
        WebElement challengeImageText = challengeUnited.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertEquals(challengeImageText.getText(), "\"Єдині\" - це 28 днів підтримки у переході на українську","The united page isn't opened");
        softAssert.assertAll();
    }

    @Test(description = "TUA-845")
    public void verifyImgcarouselButton2() {
        homePage.carouselImgComponent.clickRightArrowButton();
        homePage.carouselImgComponent.getCarouselImgCardByDataIndex(1).clickCardButton();
        ClubsPage clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(15);
        softAssert.assertTrue(clubsPage.getAdvancedSearchClubHeader().getAdvancedSearchTextHeading().getText().contains("Гуртки "),"The clubs page isn't opened");
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void verifyImgcarouselButton3() {
        homePage.carouselImgComponent.clickRightArrowButton();
        homePage.carouselImgComponent.clickRightArrowButton();
        homePage.carouselImgComponent.getCarouselImgCardByDataIndex(2).clickCardButton();
        ChallengeTeachInUkrainian challengeTeachInUkrainian =new ChallengeTeachInUkrainian(driver);
        WebElement challengeImageText=challengeTeachInUkrainian.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"),"The TeachInUkrainian page isn't opened");
        softAssert.assertAll();
    }
}
