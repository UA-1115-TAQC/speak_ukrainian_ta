package com.academy.ui.pages;

import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class ClubsPageWithAdminLoginTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void clubPageSetUp() {
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that the system shows online clubs when the user chooses 'without location'")
    @Issue("TUA-880")
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

    @Test
    @Issue("TUA-851")
    public void checkSearchClubsConductedSelectedCity(){
        HeaderComponent headerComponent = homePage.header.clickCityLocation();
        ClubsPage clubsPage = headerComponent.selectClubsCityLocation("Київ");
        softAssert.assertTrue(headerComponent.getSelectedCity().getText().contains("Київ"), "Not in Київ");
        clubsPage.setTextHeaderSearch("Спортивні секції");
        clubsPage.getClubCards().forEach(item -> softAssert.assertTrue(item.getAddress().getText().contains("Київ"), "Not in that location"));

        clubsPage = headerComponent.selectClubsCityLocation("Харків");
        softAssert.assertTrue(headerComponent.getSelectedCity().getText().contains("Харків"), "Not in Харків");
        clubsPage.getClubCards().forEach(item -> softAssert.assertTrue(item.getAddress().getText().contains("Харків"), "Not in that location"));
        softAssert.assertAll();
    }

}
