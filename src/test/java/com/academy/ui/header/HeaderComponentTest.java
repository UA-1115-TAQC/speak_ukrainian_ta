package com.academy.ui.header;

import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.*;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderComponentTest extends BaseTestRunner {
    private HeaderComponent header;

    @BeforeMethod
    private void headerPreconditions(){
        header = homePage.getHeader();
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


        BaseChallengePage p = header.challengesClick();
//       TODO  chalanges

        softAssert.assertAll();
    }

}
