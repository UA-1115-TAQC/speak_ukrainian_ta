package com.academy.ui.directionsComponent;

import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration.seconds;

public class DirectionsInHomePageAndClubPageTest extends BaseTestRunner {

    @Test(description = "TUA-827")
    public void directionsCardsDisplayedProperlyInHomeAndClubsPages() {
        ClubsPage clubsPage = new ClubsPage(driver);
        String firstPageURL = driver.getCurrentUrl();
        List<ClubDirectionCard> clubDirectionCardList = homePage.carouselCardComponent.getAllCarouselCards();
        clubDirectionCardList.get(0).clickClubCardButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getSearchSider().getLabel().getText(), "Розширений пошук");
        softAssert.assertTrue(clubsPage.getSearchSider().getDirectionsCheckBox().get(0).isSelected());

        driver.navigate().back();
        softAssert.assertEquals(firstPageURL, driver.getCurrentUrl());

        //reassigning of these variables is used after back() on previous page
        //so we get new access to this elements
        homePage = new HomePage(driver);
        clubDirectionCardList = homePage.carouselCardComponent.getAllCarouselCards();
        clubDirectionCardList.get(0).clickClubCardButton();
        softAssert.assertEquals(clubsPage.getSearchSider().getLabel().getText(), "Розширений пошук");
    }
}