package com.academy.ui.header;

import com.academy.ui.components.header.HeaderChallengesDropdown;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.*;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HeaderComponentTest extends BaseTestRunner {
    private HeaderComponent header;

    @BeforeMethod(description = "get header component")
    private void headerPreconditions(){
        header = homePage.getHeader();
    }

    @Test
    @Description("Verify that on 'Про нас', 'Новини' and “Послуги українською” pages lower part of header is not displayed")
    @Issue("TUA-972")
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

}
