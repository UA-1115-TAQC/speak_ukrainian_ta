package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.pages.AllNewsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchHeaderWithAdmin extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void advancedSearchHeaderTestSetUp() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-850")
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
