package com.academy.ui.footer;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.pages.BasePageWithoutHeaderAndFooter;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class FooterComponentTest extends BaseTestRunner {
    private SoftAssert softAssert;
    private static final int FACEBOOK_URL = 0;
    private static final int YOUTUBE_URL = 1;
    private static final int INSTAGRAM_URL = 2;
    private BasePageWithoutHeaderAndFooter basePageWithoutHeaderAndFooter;
    private FooterComponent footerComponent;
  
    @BeforeMethod(description = "Preconditions: Get footer and make new basePageWithoutHeaderAndFooter and softAssert objects")
    public void footerPrecondition() {
        footerComponent = homePage.getFooter();
        basePageWithoutHeaderAndFooter = new BasePageWithoutHeaderAndFooter(driver);
        softAssert = new SoftAssert();
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

    @Test(description = "Footer remains same across all pages")
    @Description("[Footer] Verify that the footer remains the same across all pages.")
    @Issue("TUA-943")
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
  
    @Test
    @Description("Verify that clicking the YouTube icon opens the corresponding page")
    @Issue("TUA-945")
    public void clickYouTubeIcon() {
        String expected = footerComponent.getFooterSocialLinks().get(YOUTUBE_URL);
        footerComponent.getYouTubeLink().click();
        basePageWithoutHeaderAndFooter.getTabHandles();
        basePageWithoutHeaderAndFooter.switchToANewTabByItsIndex(YOUTUBE_URL);
        String actual = driver.getCurrentUrl();
        softAssert.assertEquals(expected, actual);

        softAssert.assertAll();
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

    @Test
    @Issue("TUA-944")
    public void checkFacebookIconOpenCorrespondingFacebookPage(){
        footerComponent.clickOnFacebookLink();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains("Facebook")) {
                break;
            }
        }
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/teach.in.ukrainian");
        softAssert.assertAll();
    }

    @Test(description = "TUA-949")
    public void testDonateButtonLightsUp() {
        homePage.scrollToFooter();

        FooterComponent footer = homePage.getFooter();
        softAssert.assertTrue(footer.isDonateBlockIsDisplayed());

        WebElement footerButton = footerComponent.getDonateButton();
        String expectedColor = "rgba(255, 169, 22, 1)";

        footer.moveTooltipToDonateButton();

        String buttonColor = footerButton.getCssValue("background-color");
        Assert.assertNotEquals(buttonColor, expectedColor, "Button did not light up as expected");
        softAssert.assertAll();
    }

    @Test(description = "TUA-982", dataProvider = "window extension")
    public void checkUIAscrossDifferentMobile(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
        checkFooterElements(footerComponent, "Home Page");
        checkTextSizeAndColorAllElements();
        softAssert.assertAll();
    }

    @DataProvider(name = "window extension")
    private Object[][] windowExtension() {
        return new Object[][] {
                {320, 667},
                {414, 896},
                {425, 915}
        };
    }

    private void checkTextSizeAndColorAllElements() {
        softAssert.assertEquals(footerComponent.getSponsorsTitle().getText(),
                "Наші партнери");
        softAssert.assertEquals(footerComponent.getSponsorsTitle().getCssValue("color"),
                "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(footerComponent.getSponsorsTitle().getCssValue("font-size"),
                "20px");

        softAssert.assertEquals(footerComponent.getDonateTitle().getText(),
                "Як допомогти проєкту?");
        softAssert.assertEquals(footerComponent.getDonateTitle().getCssValue("color"),
                "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(footerComponent.getDonateTitle().getCssValue("font-size"),
                "20px");

        softAssert.assertEquals(footerComponent.getDonateExplanation().getText(),
                "Ініціатива потребує постійної фінансової підтримки, аби покривати щоденні витрати на роботу.");
        softAssert.assertEquals(footerComponent.getDonateExplanation().getCssValue("color"),
                "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(footerComponent.getDonateExplanation().getCssValue("font-size"),
                "14px");

        softAssert.assertEquals(footerComponent.getDonateButton().getText(),
                "Допомогти проєкту");
        softAssert.assertEquals(footerComponent.getDonateButton().getCssValue("color"),
                "rgba(250, 140, 22, 1)");

        softAssert.assertEquals(footerComponent.getMottoUnderLogo().getText(),
                "Нам небайдуже майбутнє дітей та української мови");
        softAssert.assertEquals(footerComponent.getMottoUnderLogo().getCssValue("color"),
                "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(footerComponent.getMottoUnderLogo().getCssValue("font-size"),
                "12px");

        softAssert.assertEquals(footerComponent.getCopyrightNotice().getText(),
                "©2021-2022 Design by Qubstudio & Development by SoftServe");
        softAssert.assertEquals(footerComponent.getCopyrightNotice().getCssValue("color"),
                "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(footerComponent.getCopyrightNotice().getCssValue("font-size"),
                "12px");
    }
}
