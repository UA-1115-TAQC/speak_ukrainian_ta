package com.academy.ui.homePage;

import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.challenges.ChallengeTeachInUkrainian;
import com.academy.ui.pages.challenges.ChallengeUnited;
import com.academy.ui.runners.LoginWithUserTestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ImgCarouselLogedInTest extends LoginWithUserTestRunner {
    SoftAssert softAssert;
    WebDriverWait wait;
    Actions actions;
    @BeforeMethod
    public void init(){
        softAssert=new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        actions = new Actions(driver);
    }
    @Test(description = "TUA-845")
    public void checkThatSlickDotsContainerIsCentered(){
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotsContainer().getCssValue("justify-content").contains("center"));
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void verifyImgCarouselCard1(){
        performActionsToCheckElementsOnTheImgCard(0,1);
        ChallengeUnited challengeUnited = new ChallengeUnited(driver);
        softAssert.assertTrue(driver.getCurrentUrl().contains("challenges/5"));
        WebElement challengeImageText = challengeUnited.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertEquals(challengeImageText.getText(), "\"Єдині\" - це 28 днів підтримки у переході на українську");
        softAssert.assertAll();
    }
    @Test(description = "TUA-845")
    public void verifyImgCarouselCard2(){
       // homePage.carouselImgComponent.clickSlickDotByIndex(1);
        performActionsToCheckElementsOnTheImgCard(1,2);
       ClubsPage clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(20);
        softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"));
        softAssert.assertTrue(clubsPage.getAdvancedSearchClubHeader().getAdvancedSearchTextHeading().getText().contains("Гуртки "));
        System.out.println(softAssert.toString());
        softAssert.assertAll();
    }

    @Test(description = "TUA-845")
    public void verifyImgCarouselCard3(){
        performActionsToCheckElementsOnTheImgCard(2,0);
        ChallengeTeachInUkrainian challengeTeachInUkrainian = new ChallengeTeachInUkrainian(driver);
        WebElement challengeImageText=challengeTeachInUkrainian.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertTrue(driver.getCurrentUrl().contains("about"));
        softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"));
        softAssert.assertAll();
    }
    private void verifyImgCardSize(){
        softAssert.assertEquals(homePage.carouselImgComponent.getActiveCarouselImgCard().getBackgroundImage().getSize().getWidth(), 1268);
        softAssert.assertEquals(homePage.carouselImgComponent.getActiveCarouselImgCard().getBackgroundImage().getSize().getHeight(), 400);
    }
    private void verifyThatSlickDotsAreCentered(){
        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotsContainer()
                .getCssValue("justify-content").contains("center"));

    }
    private void checkThatSlickDotsChangeColor(int imgCardToCheckIndex, int imgCardAdditionalForSwitchingDuringCheckIndex){
        System.out.println(homePage.carouselImgComponent.getSlickDotByIndex(imgCardToCheckIndex).getCssValue("background"));
        if(imgCardToCheckIndex!=0){
            softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotByIndex(imgCardToCheckIndex).getCssValue("background").contains("rgb(250, 140, 22)"));
        }else{
            softAssert.assertTrue(!homePage.carouselImgComponent.getSlickDotByIndex(imgCardToCheckIndex).getCssValue("background").contains("rgb(196, 196, 196)"));
        }
        homePage.carouselImgComponent.clickSlickDotByIndex(imgCardAdditionalForSwitchingDuringCheckIndex);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.carouselImgComponent.getSlickDotByIndex(imgCardToCheckIndex)));
        wait.until(ExpectedConditions.attributeContains(homePage.carouselImgComponent.getSlickDotByIndex(imgCardAdditionalForSwitchingDuringCheckIndex), "background", "rgb(250, 140, 22)"));

        softAssert.assertTrue(homePage.carouselImgComponent.getSlickDotByIndex(imgCardToCheckIndex).getCssValue("background").contains("rgb(196, 196, 196)"));
    }
    private void performActionsToCheckElementsOnTheImgCard(int imgCardToCheckIndex, int imgCardAdditionalForSwitchingDuringCheckIndex){
       if(imgCardToCheckIndex!=0) {
           homePage.carouselImgComponent.clickSlickDotByIndex(imgCardToCheckIndex);
       }
        verifyImgCardSize();
        checkThatSlickDotsChangeColor(imgCardToCheckIndex,imgCardAdditionalForSwitchingDuringCheckIndex);
        verifyThatSlickDotsAreCentered();
        //CHECK ALIGNMENT?????? MOCKUP?????

        softAssert.assertTrue(homePage.carouselImgComponent.getWebElement().getCssValue("align-items").contains("center"));
        homePage.carouselImgComponent.clickSlickDotByIndex(imgCardToCheckIndex);
        if(! homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton().isDisplayed()){
        wait.until(ExpectedConditions.visibilityOf(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton()));}
        homePage.carouselImgComponent.clickSlickDotByIndex(imgCardToCheckIndex);
        if(! homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton().isDisplayed()){
            wait.until(ExpectedConditions.visibilityOf(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton()));
        }
        softAssert.assertTrue(homePage.carouselImgComponent.getActiveCarouselImgCard().getCardButtonText().getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        homePage.carouselImgComponent.clickSlickDotByIndex(imgCardToCheckIndex);
        if(! homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton().isDisplayed()){
            wait.until(ExpectedConditions.visibilityOf(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButton()));
        }else{
            actions.moveToElement(homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).getCardButtonText());
            actions.build().perform();
        }
        softAssert.assertTrue(homePage.carouselImgComponent.getActiveCarouselImgCard().getCardButton().getCssValue("background").contains("rgb(250, 140, 22)"));
        System.out.println(homePage.carouselImgComponent.getActiveCarouselImgCard().getCardButtonText().getCssValue("color"));
        //where to see the color
        //what happens during 2nd hover????
        softAssert.assertTrue(homePage.carouselImgComponent.getActiveCarouselImgCard().getCardButtonText().getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        homePage.carouselImgComponent.getCarouselImgCardByDataIndex(imgCardToCheckIndex).clickCardButton();
    }
}
