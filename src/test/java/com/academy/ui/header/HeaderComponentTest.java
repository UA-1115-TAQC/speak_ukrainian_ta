package com.academy.ui.header;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.components.header.HeaderChallengesDropdown;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.*;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @Test
    @Issue("TUA-347")
    public void checkUI() {
        softAssert.assertTrue(header.getTeachInUkrainianLogo().isDisplayed());
        softAssert.assertTrue(header.getClubsButton().isDisplayed());
        softAssert.assertTrue(header.getChallengeButton().isDisplayed());
        softAssert.assertTrue(header.getNewsButton().isDisplayed());
        softAssert.assertTrue(header.getAboutUsButton().isDisplayed());
        softAssert.assertTrue(header.getServiceButton().isDisplayed());
        softAssert.assertTrue(header.getClubsLocationButton().isDisplayed());
        softAssert.assertTrue(header.getAddClubButton().isDisplayed());
        softAssert.assertTrue(header.getProfileMenuButton().isDisplayed());

        BasePageWithAdvancedSearch advancedSearchHeader = new BasePageWithAdvancedSearch(driver);
        softAssert.assertTrue(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchTextHeading()
                .isDisplayed());

        softAssert.assertTrue(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getSelectionSearchInputField()
                .isDisplayed());

        softAssert.assertTrue(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getSearchIcon()
                .isDisplayed());

        softAssert.assertTrue(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchIcon()
                .isDisplayed());

        softAssert.assertEquals(header.getClubsButton().getText(), "Гуртки");
        softAssert.assertEquals(header.getChallengeButton().getText(), "Челендж");
        softAssert.assertEquals(header.getNewsButton().getText(), "Новини");
        softAssert.assertEquals(header.getAboutUsButton().getText(), "Про нас");
        softAssert.assertEquals(header.getServiceButton().getText(), "Послуги українською");

       softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchTextHeading()
                .getText(), "Ініціатива “Навчай українською”");

        softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getSelectionSearchInputFieldPlaceholder()
                        .getText(),
                "Який гурток шукаєте?"
                );

        softAssert.assertEquals(header.getClubsButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "1");
        softAssert.assertEquals(header.getChallengeButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "2");
        softAssert.assertEquals(header.getNewsButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "3");
        softAssert.assertEquals(header.getAboutUsButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "4");
        softAssert.assertEquals(header.getServiceButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "5");
        softAssert.assertEquals(header.getClubsLocationButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "6");
        softAssert.assertEquals(header.getAddClubButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)", "7");

        softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchTextHeading()
                .getCssValue("color"),
                "rgba(255, 255, 255, 1)", "8");

        softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getSelectionSearchInputField()
                .getCssValue("color"),
                "rgba(0, 0, 0, 0.88)", "9");

        softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getSearchIcon()
                .getCssValue("color"),
                "rgba(255, 255, 255, 1)", "9");

        softAssert.assertEquals(advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchIcon()
                .getCssValue("color"),
                "rgba(255, 165, 0, 1)", "10");

        String headerPadding = header.getHeaderBox().getCssValue("padding-right");
        String advancedSearchPadding = advancedSearchHeader
                .getAdvancedSearchHeaderComponent()
                .getAdvancedSearchBox()
                .getCssValue("padding-right");

        softAssert.assertEquals(advancedSearchPadding, headerPadding, "Padding-right values are not equal!");

        int headerBoxWidth = header.getHeaderBox().getSize().getWidth();
        int leftBlockWidth = header.getLeftBlock().getSize().getWidth();
        int rightBlockWidth = header.getRightBlock().getSize().getWidth();
        int expectedCenterBlockPosition = (headerBoxWidth - leftBlockWidth - rightBlockWidth) / 2;
        int centerBlockPosition = header.getCenterBlock().getLocation().getX() - header.getHeaderBox().getLocation().getX();
        softAssert.assertEquals(centerBlockPosition, expectedCenterBlockPosition, "Center block is not aligned to the center!");

        int numberOfElementsInLeftBlock = 0;
        if (header.getClubsLocationButton().isDisplayed()) numberOfElementsInLeftBlock++;
        if (header.getAddClubButton().isDisplayed()) numberOfElementsInLeftBlock++;
        if (header.getProfileMenuButton().isDisplayed()) numberOfElementsInLeftBlock++;
        softAssert.assertEquals(numberOfElementsInLeftBlock, 3, "Number of elements in the left block is not equal to 3!");

        String initialTitle = driver.getTitle();
        homePage.header.clickTeachInUkrainianLogo();
        String newTitle = driver.getTitle();
        softAssert.assertEquals(initialTitle, newTitle, "Page did not refresh after clicking the logo");

        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.TAB).perform();
//        softAssert.assertTrue(header.getLocationIcon().equals(driver.switchTo().activeElement()),
//                "Focus should be on logo");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getCenterBlock().equals(driver.switchTo().activeElement()),
                "Focus should be on center block");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getClubsButton().equals(driver.switchTo().activeElement()),
                "Focus should be on clubs tab");
        actions.sendKeys(Keys.TAB).perform();
//        softAssert.assertTrue(header.getChallengeButton().equals(driver.switchTo().activeElement()),
//                "Focus should be on challenge tab");
//        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getNewsButton().equals(driver.switchTo().activeElement()),
                "Focus should be on news tab");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getAboutUsButton().equals(driver.switchTo().activeElement()),
                "Focus should be on about us tab");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getServiceButton().equals(driver.switchTo().activeElement()),
                "Focus should be on service tab");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(header.getAddClubButton().equals(driver.switchTo().activeElement()),
                "Focus should be on add club button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchHeader
                        .getAdvancedSearchHeaderComponent()
                        .getSelectionSearchInputField()
                        .equals(driver.switchTo().activeElement()),
                "Focus should be on search filed");

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
