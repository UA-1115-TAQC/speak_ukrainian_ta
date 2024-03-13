package com.academy.ui.header;

import com.academy.ui.components.header.HeaderChallengesDropdown;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.*;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class HeaderComponentTest extends BaseTestRunner {
    private HeaderComponent header;
    private SoftAssert softAssert;
    public static final String DEFAULT_CITY = "Київ";

    @BeforeMethod
    private void headerPreconditions(){
        header = homePage.getHeader();
        softAssert = new SoftAssert();
    }

    @Test
    public void checkLowerPartOfHeaderOnPages(){
        SoftAssert softAssert = new SoftAssert();

        ClubsPage clubs = header.clickClubsPageButton();
        softAssert.assertTrue(clubs.getSearchClubHeaderWebElement().isDisplayed());
        header = clubs.getHeader();

        HomePage home = header.clickTeachInUkrainianLogo();
        softAssert.assertTrue(home.getAdvancedSearchHeaderComponentNode().isDisplayed());
        header = home.getHeader();

        AllNewsPage allNewsPage = header.newsButtonClick();
        softAssert.assertFalse(allNewsPage.getAdvancedSearchHeaderComponentNode().isDisplayed());
        header = allNewsPage.getHeader();

        AboutUsPage aboutUSPAge = header.clickAboutUsButton();
        softAssert.assertFalse(aboutUSPAge.getAdvancedSearchHeaderComponentNode().isDisplayed());
        header = aboutUSPAge.getHeader();

        ServicePage servicePage = header.clickServiceButton();
        softAssert.assertFalse(servicePage.getAdvancedSearchHeaderComponentNode().isDisplayed());
        header = servicePage.getHeader();

        HeaderChallengesDropdown challengesDropdown = header.clickChallengeButton();
        for(int i = 0; i < challengesDropdown.getChallengeDropdownItems().size(); i++){
            BaseChallengePage challengePage = challengesDropdown.clickChallengeDropdownItemByIndex(i);
            softAssert.assertFalse(challengePage.getAdvancedSearchHeaderComponentNode().isDisplayed());
            header = challengePage.getHeader();
            challengesDropdown = header.clickChallengeButton();
        }

        softAssert.assertAll();
    }

    @Test(description = "TUA-23")
    @Issue("TUA-23")
    public void testVerifyKyivIsShownByDefaultCity() {
        softAssert.assertTrue(header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");
        softAssert.assertAll();
    }

    @Test(description = "TUA-311")
    public void testVerifyLocationItem() {
        softAssert.assertTrue(header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");
        header.openCityMenu();
        header.waitUntilCityMenuNodeDisplayed(20);
        softAssert.assertTrue(header.getCityMenuNode().isDisplayed(), "List of city doesn't display");
        softAssert.assertAll();
    }
}
