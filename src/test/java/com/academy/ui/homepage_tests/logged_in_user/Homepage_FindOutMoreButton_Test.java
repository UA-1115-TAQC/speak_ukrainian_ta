package com.academy.ui.homepage_tests.logged_in_user;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.homepage_tests.Homepage_Buttons_OnTheBody_OutsideComponents_TestBase;
import com.academy.ui.runners.LogInWithUserTestRunner;
import com.academy.ui.runners.homepage_runners.BaseHomePageTestRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Homepage_FindOutMoreButton_Test extends BaseHomePageTestRunner{
    @BeforeMethod
    public void i(){
        LoginPopupComponent loginForm = home
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.enterEmail(configProperties.getUserEmail());
        loginForm.enterPassword(configProperties.getUserPassword());
        loginForm.clickSubmitButton();
    }
    @Test
    public void check_hover_effects(){
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", homePage.getChallengeFindOutMoreButton());
        wait.until(ExpectedConditions.visibilityOf(homePage.getChallengeFindOutMoreButton()));
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(250, 140, 22)"));
        actions.moveToElement(homePage.getChallengeFindOutMoreButton()).build().perform();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(homePage.getChallengeFindOutMoreButton(), "background","rgb(250, 140, 22)")));
        softAssert.assertTrue(homePage.getChallengeFindOutMoreButton().getCssValue("background").contains("rgb(255, 169, 64)"));
        softAssert.assertAll();
    }

    @Test
    public void check_find_out_more_button_on_the_body(){
      /*  WebElement challengeImageText = home.clickChallengeFindOutMoreButton().getChallengeImageText();
        Assert.assertTrue(driver.getCurrentUrl().contains("challenge"));
        Assert.assertTrue(challengeImageText.getText().contains("Навчай українською"));*/
        new Homepage_Buttons_OnTheBody_OutsideComponents_TestBase().check_find_out_more_button_on_the_body();
    ///?????
    }
}
