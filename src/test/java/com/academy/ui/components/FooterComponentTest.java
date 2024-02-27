package com.academy.ui.components;

import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class FooterComponentTest extends BaseTestRunner {
    private SoftAssert softAssert;

    @BeforeTest
    public void createAssert() {
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void deleteAssert() {
        softAssert = null;
    }

    private void checkFooterElements(FooterComponent footer, String pageNme) {

        final int SOCIAL_LINKS_NUMBER = 3;
        final int SPONSOR_LINKS_NUMBER = 7;

        softAssert.assertTrue(footer.getLogo().isDisplayed(),
                "Logo should be displayed on the Footer on the " + pageNme);
        softAssert.assertTrue(footer.getMottoUnderLogo().isDisplayed(),
                "Logo description should be displayed on the Footer on the " + pageNme);
        softAssert.assertTrue(footer.getMottoUnderLogo().isDisplayed(),
                "Logo description should be displayed on the Footer on the " + pageNme);
        softAssert.assertEquals(footer.getFooterSocialLinks().size(), SOCIAL_LINKS_NUMBER);
        softAssert.assertTrue(footer.getCopyrightNotice().isDisplayed(),
                "Copyright should be displayed on the Footer on the " + pageNme);
        softAssert.assertTrue(footer.getSponsorsTitle().isDisplayed(),
                "Sponsors Title should be displayed on the Footer on the " + pageNme);
        softAssert.assertEquals(footer.getFooterSponsorsLinks().size(), SPONSOR_LINKS_NUMBER);
        softAssert.assertTrue(footer.getDonateTitle().isDisplayed(),
                "Donate Title should be displayed on the Footer on the " + pageNme);
        softAssert.assertTrue(footer.getDonateExplanation().isDisplayed(),
                "Donate Explanation should be displayed on the Footer on the " + pageNme);
        softAssert.assertTrue(footer.getDonateButton().isDisplayed(),
                "Donate Button should be displayed on the Footer on the " + pageNme);
    }

    @Test(description = "TUA-943")
    public void verifyFooterRemainsSameAcrossAllPages() {

        final String MESSAGE = "Footer should be displayed on the ";
        final String HOME_PAGE_NAME = "Home Page";
        final String CHALLENGE_PAGE_NAME = "Challenge Page";
        final String CLUBS_PAGE_NAME = "Clubs Page";
        final String ALL_NEWS_PAGE_NAME = "All News Page";

        FooterComponent homePageFooter = homePage.getFooter();
        softAssert.assertTrue(homePageFooter.getWebElement().isDisplayed(),
                MESSAGE + HOME_PAGE_NAME);
        checkFooterElements(homePageFooter, HOME_PAGE_NAME);

        FooterComponent clubsPageFooter = homePage.header.clickClubsPageButton().getFooter();
        softAssert.assertTrue(clubsPageFooter.getWebElement().isDisplayed(),
                MESSAGE + CLUBS_PAGE_NAME);
        checkFooterElements(clubsPageFooter, CLUBS_PAGE_NAME);

        homePage.header.clickChallengeButton().clickChallengeDropdownItemByIndex(0);
        BaseChallengePage challengePage = new BaseChallengePage(driver);
        FooterComponent challengePageFooter = challengePage.getFooter();
        softAssert.assertTrue(challengePageFooter.getWebElement().isDisplayed(),
                MESSAGE + CHALLENGE_PAGE_NAME);
        checkFooterElements(challengePageFooter, CHALLENGE_PAGE_NAME);

        FooterComponent allNewsPageFooter = homePage.header.newsButtonClick().getFooter();
        softAssert.assertTrue(allNewsPageFooter.getWebElement().isDisplayed(),
                MESSAGE + ALL_NEWS_PAGE_NAME);
        checkFooterElements(allNewsPageFooter, ALL_NEWS_PAGE_NAME);

        softAssert.assertAll();
    }
}
