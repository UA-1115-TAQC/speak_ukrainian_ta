package com.academy.ui.homePage;

import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Buttons_OnTheBody_OutsideComponents_Test extends HomePageTestRunner {
    @Test
    public void checkFindOutMoreButtonOnTheBody(){
       WebElement challengeImageText = home.clickChallengeFindOutMoreButton().getChallengeImageText();
       Assert.assertTrue(driver.getCurrentUrl().contains("challenge"));
       Assert.assertTrue(challengeImageText.getText().contains("Навчай українською"));

    }
    @Test
    public void checkClubHeadingButton(){
        WebElement challengeImageText = home.clickSpeakingClubHeading().getChallengeImageText();
        Assert.assertTrue(driver.getCurrentUrl().contains("speakingclub"));
        Assert.assertTrue(challengeImageText.getText().contains("Клуб української мови \"Розмовляй\""));
    }
    @Test
    public void checkImageFacebookLink(){
        Assert.assertTrue(home.clickSpeakingClubImage().getFacebookLogo().isDisplayed());
    }
}
