package com.academy.ui.homePage;

import com.academy.ui.runners.LoginWithUserTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Button_AllClubs extends LoginWithUserTestRunner {

    @Test(description = "TUA-862")
    public void testbuttonAllClubs(){
        homePage.scrollToAllClubsButton();
        homePage.carouselCardComponent.clickCarouselCardAllClubsButton().waitUntilClubsPageIsLoaded(15);
        Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
    }
}
