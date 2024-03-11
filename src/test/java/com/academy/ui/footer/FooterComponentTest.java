package com.academy.ui.footer;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.pages.BasePageWithoutHeaderAndFooter;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;
import com.academy.ui.pages.challenges.BaseChallengePage;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class FooterComponentTest extends BaseTestRunner {
    private SoftAssert softAssert;
    private static final int FACEBOOK_URL = 0;
    private static final int YOUTUBE_URL = 1;
    private static final int INSTAGRAM_URL = 2;
    private BasePageWithoutHeaderAndFooter basePageWithoutHeaderAndFooter;
    private FooterComponent footerComponent;
  
    @BeforeMethod
    public void footerPrecondition() {
        footerComponent = homePage.getFooter();
        basePageWithoutHeaderAndFooter = new BasePageWithoutHeaderAndFooter(driver);
    }

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
    @Test(description = "TUA-974")
    @Issue("TUA-974")
    public void checkThatLogoClickRefreshesThePageAfterCheckingFooter(){
        checkFooterElements(footerComponent, "HomePage");
        String initialTitle = driver.getTitle();
        homePage.header.clickTeachInUkrainianLogo();
        String newTitle = driver.getTitle();
        softAssert.assertNotEquals(initialTitle, newTitle, "Page did not refresh after clicking the logo");
        softAssert.assertAll();
    }
  
    @Test(description = "TUA-945")
    public void click_on_youTube_icon_ok() {
        String expected = footerComponent.getFooterSocialLinks().get(YOUTUBE_URL);
        footerComponent.getYouTubeLink().click();
        basePageWithoutHeaderAndFooter.getTabHandles();
        basePageWithoutHeaderAndFooter.switchToANewTabByItsIndex(YOUTUBE_URL);
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    @Test(description = "TUA-946")
    public void testInstagramIconClickOpensCorrespondingPage() {
        homePage.scrollToFooter();
        FooterComponent footer = homePage.getFooter();
        softAssert.assertTrue(footer.isPartnerBlockIsDisplayed());
        softAssert.assertTrue(footer.isSocialBlockIsDisplayed());
        softAssert.assertTrue(footer.isDonateBlockIsDisplayed());

        footer.clickOnInstagramLink();
        basePageWithoutHeaderAndFooter.getTabHandles();
        basePageWithoutHeaderAndFooter.switchToANewTabByItsIndex(1);
        String title = driver.getTitle();
        softAssert.assertTrue(title.contains("Єдині"), "Instagram page 'Єдині' didn't open in a new tab");

        softAssert.assertAll();
    }


}
