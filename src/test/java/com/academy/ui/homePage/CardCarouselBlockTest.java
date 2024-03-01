package com.academy.ui.homePage;

import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CardCarouselBlockTest extends BaseTestRunner {

    @Test(description = "TUA-863")
    public void checkLoggedinClickableBlockAndButton(){
//        TODO login
        homePage.scrollToCarouselCardComponentWebElement();
        ClubDirectionCard cc = homePage.getCarouselCardComponent().getActiveCarouselCardByIndex(0);
        ClubsPage cp = cc.clickCard();
        driver.navigate().back();
        homePage = homePage.waitUntilHomePageIsLoaded();


        cc = homePage.getCarouselCardComponent().getActiveCarouselCardByIndex(0);
        cp = cc.clickClubCardButton();
        driver.navigate().back();
    }

    //        TODO delete


}
