package com.academy.ui.homePage;

import com.academy.ui.runners.LogInWithUserTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Button_AllClubs extends LogInWithUserTestRunner {

    @Test(description = "TUA-862")
    public void testButtonAllClubs(){
        homePage.scrollToAllClubsButton();
        homePage.carouselCardComponent.clickCarouselCardAllClubsButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
    }
}
