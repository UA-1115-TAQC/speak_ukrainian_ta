package com.academy.ui.homePage;

import com.academy.ui.runners.HomePageTestRunner;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Buttons_OnTheBody_OutsideComponents_Test extends HomePageTestRunner {
    @Test(description = "TUA-21")
    @Issue("TUA-21")
    public void checkFindOutMoreButtonOnTheBody(){
       WebElement challengeImageText = home.clickChallengeFindOutMoreButton().getChallengeImageText();
       softAssert.assertTrue(driver.getCurrentUrl().contains("challenge"), "The challenge page isn't opened");
       softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"));
        softAssert.assertAll();
    }
    @Test(description = "TUA-21")
    @Issue("TUA-21")
    public void checkClubHeadingButton(){
        WebElement challengeImageText = home.clickSpeakingClubHeading().getChallengeImageText();
        softAssert.assertTrue(driver.getCurrentUrl().contains("speakingclub"),
                "The speaking club page isn't opened");
        softAssert.assertTrue(challengeImageText.getText().contains("Клуб української мови \"Розмовляй\""));
        softAssert.assertAll();
    }
    @Test(description = "TUA-21")
    @Issue("TUA-21")
    public void checkImageFacebookLink(){
        softAssert.assertTrue(home.clickSpeakingClubImage().getFacebookLogo().isDisplayed(),
                "The facebook page isn't opened");
        softAssert.assertAll();
    }
}
