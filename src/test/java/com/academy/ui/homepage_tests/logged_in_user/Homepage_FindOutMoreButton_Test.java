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
   private JavascriptExecutor jsExecutor;

    @BeforeMethod
    public void init(){
        wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        softAssert = new SoftAssert();
        actions = new Actions(driver);
    }
    @Test
    public void checkHoverEffects(){
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", homePage.getChallengeFindOutMoreButton());
        wait.until(ExpectedConditions.visibilityOf(homePage.getChallengeFindOutMoreButton()));
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(250, 140, 22)"));
        actions.moveToElement(homePage.getChallengeFindOutMoreButton()).build().perform();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(homePage.getChallengeFindOutMoreButton(), "background","rgb(250, 140, 22)")));
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(255, 169, 64)"));
        softAssert.assertAll();
    }

    @Test
    public void checkFindOutMoreButtonOnTheBody(){
       WebElement challengeImageText = homePage.clickChallengeFindOutMoreButton().getChallengeImageText();
        softAssert.assertTrue(driver.getCurrentUrl().contains("challenge"));
        softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"));
    }
}
