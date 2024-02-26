package com.academy.ui.homepage_tests;

import com.academy.ui.runners.LoginWithUserTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Button_AllClubs extends LoginWithUserTestRunner {

    @Test(description = "TUA-862")
    public void testbuttonAllClubs(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", homePage.carouselCardComponent.getCarouselCardAllClubsButton());

        homePage.carouselCardComponent.clickCarouselCardAllClubsButton().waitUntilClubsPageIsLoaded(15);

        Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
    }
}
