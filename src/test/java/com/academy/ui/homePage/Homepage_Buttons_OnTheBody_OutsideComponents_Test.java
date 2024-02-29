package com.academy.ui.homePage;

import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Buttons_OnTheBody_OutsideComponents_Test extends HomePageTestRunner {
    @Test
    public void checkFindOutMoreButtonOnTheBody(){
       WebElement challengeImageText = home.clickChallengeFindOutMoreButton().getChallengeImageText();
       softAssert.assertTrue(driver.getCurrentUrl().contains("challenge"));
       softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"));
        softAssert.assertAll();
    }
    @Test
    public void checkClubHeadingButton(){
        WebElement challengeImageText = home.clickSpeakingClubHeading().getChallengeImageText();
        softAssert.assertTrue(driver.getCurrentUrl().contains("speakingclub"));
        softAssert.assertTrue(challengeImageText.getText().contains("Клуб української мови \"Розмовляй\""));
        softAssert.assertAll();
    }
    @Test
    public void checkImageFacebookLink(){
        softAssert.assertTrue(home.clickSpeakingClubImage().getFacebookLogo().isDisplayed());
        softAssert.assertAll();
    }
}
