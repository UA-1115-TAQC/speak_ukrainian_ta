package com.academy.ui.homePage;

import com.academy.ui.pages.AboutUsPage;
import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestAboutUsRedirectAndUnderlineInHeader extends HomePageTestRunner {

    @Test(description = "TUA-309")
    public void testAboutUsRedirectAndUnderlineInHeader(){
        wait.until(ExpectedConditions.elementToBeClickable(home.header.getAboutUsButton()));
        actions.moveToElement(home.header.getAboutUsButton()).build().perform();
        softAssert.assertTrue(home.header.getAboutUsButtonContainer().getCssValue("border-bottom").contains("rgb(255, 255, 255)"),
                "The about us container element isn't underlined after a mouse hover");
        AboutUsPage aboutUsPage = home.header.clickAboutUsButton();
        wait.until(ExpectedConditions.visibilityOf(aboutUsPage.getBannerFirstRowText()));
        softAssert.assertTrue(driver.getCurrentUrl().contains("about"),"The AboutUs page isn't opened");
       home = aboutUsPage.header.clickTeachInUkrainianLogo();
       wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("about")));
        softAssert.assertTrue(home.header.getAboutUsButtonContainer().getCssValue("border-bottom").contains("0px none rgba(0, 0, 0, 0.88)"),
                "The about us container element is still underlined after opening a home page");
        softAssert.assertAll();
    }
}
