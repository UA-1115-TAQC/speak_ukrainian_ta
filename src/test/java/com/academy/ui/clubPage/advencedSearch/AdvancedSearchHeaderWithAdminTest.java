package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.CenterCardComponent;
import com.academy.ui.pages.AllNewsPage;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AdvancedSearchHeaderWithAdminTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;
    @BeforeMethod(description = "Preconditions: Make new softAssert object")
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
    @Test(description = "TUA-246")
    public void advancedSearchCheckChangingClubsWithChangingTown(){
        ClubsPage clubsPage = new ClubsPage(driver);
        AllNewsPage allNewsPage = homePage.header.newsButtonClick();

        allNewsPage.advancedSearchHeaderComponent.clickAdvancedSearchIcon();
        softAssert.assertEquals(clubsPage.getSearchSider().getLabel().getText(), "Розширений пошук");

        clubsPage = clubsPage.getSearchSider().chooseRadioButton("Центр");
        softAssert.assertEquals(clubsPage.getSearchSider().getSearchCityElement().getInputContent().getText(), "Київ");

        List<CenterCardComponent> centerCards = clubsPage.getCenterCards();
        softAssert.assertTrue(centerCards.get(1).getAddress().getText().contains("Київ"));
        softAssert.assertTrue(centerCards.get(4).getAddress().getText().contains("Київ"));
        softAssert.assertTrue(centerCards.get(5).getAddress().getText().contains("Київ"));

        clubsPage.getSearchSider().clickCityDropDown();
        softAssert.assertTrue(clubsPage.getSearchSider().getSearchCityElement().getDropDownElement()
                                                                                .getWebElement()
                                                                                .isDisplayed());

        clubsPage.getSearchSider().getSearchCityElement().selectItem("Харків");
        softAssert.assertEquals(clubsPage.getSearchSider().getSearchCityElement().getInputContent()
                                                                                    .getText(), "Харків");

        clubsPage = new ClubsPage(driver);
        centerCards = clubsPage.getCenterCards();

        for (CenterCardComponent centerCard : centerCards) {
            softAssert.assertTrue(centerCard.getAddress().getText().contains("Харків"),
                                    "False location. Found locations : " + centerCard.getAddress().getText());
        }

        softAssert.assertAll();
    }
}