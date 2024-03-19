package com.academy.ui.homePage;

import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage_Button_AllClubs extends LogInWithUserTestRunner {

    @Test(description = "The user is logged in")
    @Description("Verify the 'Всі гуртки' button near the carousel with suggested clubs on the right")
    @Issue("TUA-862")
    public void testButtonAllClubs(){
        homePage.scrollToAllClubsButton();
        homePage.carouselCardComponent.clickCarouselCardAllClubsButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
    }
}
