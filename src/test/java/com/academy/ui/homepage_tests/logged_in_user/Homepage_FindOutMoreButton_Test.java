package com.academy.ui.homepage_tests.logged_in_user;

import com.academy.ui.runners.LogInWithUserTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Homepage_FindOutMoreButton_Test extends LogInWithUserTestRunner {
   private WebDriverWait wait;
   private SoftAssert softAssert;
   private Actions actions;
    @BeforeMethod
    public void init(){
        wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        softAssert = new SoftAssert();
        actions = new Actions(driver);
    }
    @Test(description = "TUA-860")
    public void checkHoverEffects(){
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(250, 140, 22)"),
                "The button color isn't up to a mockup");
        actions.moveToElement(homePage.getChallengeFindOutMoreButton()).build().perform();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(homePage.getChallengeFindOutMoreButton(), "background","rgb(250, 140, 22)")));
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(255, 169, 64)"),
                "The button color isn't changed after a mouse hover");
        softAssert.assertAll();
    }

    @Test(description = "TUA-860")
    public void checkFindOutMoreButtonOnTheBody(){
       WebElement challengeImageText = homePage.clickChallengeFindOutMoreButton().getChallengeImageText();
        softAssert.assertTrue(driver.getCurrentUrl().contains("challenge"),
                "The challenge page isn't opened, therefore the link hasn't been changed");
        softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"),"The challenge page isn't opened");
        softAssert.assertAll();
    }
}
