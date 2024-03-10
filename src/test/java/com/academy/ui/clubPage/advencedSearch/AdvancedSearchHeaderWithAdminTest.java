package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.pages.AllNewsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchHeaderWithAdminTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;
    @BeforeMethod(description = "Precondition: Make new softAssert object")
    public void advancedSearchHeaderTestSetUp() {
        softAssert = new SoftAssert();
    }

    @Test(description = "Search redirect user to clubs page from other pages")
    @Description("[Basic Search] Check if the user is redirected to the clubs page when searching from other pages.")
    @Issue("TUA-850")
    public void checkSearchRedirectUserToClubsPageFromOtherPages(){

        final String NEWS_PAGE = "news";
        final String CLUBS_PAGE = "clubs";
        final String CLUB_NAME = "Танці";

        AllNewsPage allNewsPage = homePage.header.newsButtonClick();
        softAssert.assertTrue(driver.getCurrentUrl().contains(NEWS_PAGE),
                "News page should be loaded");
        allNewsPage.advancedSearchHeaderComponent
                .setTextSelectionSearchInputField(CLUB_NAME)
                .clickAdvancedSearchIcon();
        softAssert.assertTrue(driver.getCurrentUrl().contains(CLUBS_PAGE),
                "Clubs page should be loaded");

        softAssert.assertAll();;
    }

}
