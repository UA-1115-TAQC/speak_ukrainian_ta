package com.academy.ui.homePage;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCarouselSliders extends BaseTestRunner {

    @Test(description = "[Home page] [user is logged in] Verify that on the carousel slides are changing automatically")
    @Issue("TUA-834")
    public void checkThatCarouselSlidersAreChangingAutomatically(){
        String text = homePage.getCarouselImgComponent().getActiveCarouselImgCard().getCardLinkText();
        System.out.println(text);

        String changedItemText = homePage.getCarouselImgComponent().getActiveCarouselImgCard().getCardText().getText();
        System.out.println(changedItemText);
    }

}
