package com.academy.ui.pages;

import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ClubsPageWithAdminLoginTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void clubPageSetUp() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-880")
    public void checkClubsPageShowsOnlineClubWithoutLocation() {
        HeaderComponent headerComponent = homePage.header.clickCityLocation();
        ClubsPage clubsPage = headerComponent.selectClubsCityLocation("Без локації");
        softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"));

        List<ClubCardComponent> clubCards = clubsPage.getClubCards();
        long actualQuantityOfOnlineClubs = clubCards.stream()
                .filter(club -> club.getClubOnline().getText().equals("Гурток онлайн"))
                .count();
        softAssert.assertEquals(clubCards.size(), actualQuantityOfOnlineClubs);

        softAssert.assertAll();
    }
}
