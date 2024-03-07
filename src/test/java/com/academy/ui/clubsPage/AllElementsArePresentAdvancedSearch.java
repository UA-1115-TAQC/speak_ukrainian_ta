package com.academy.ui.clubsPage;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AllElementsArePresentAdvancedSearch extends LoginWithAdminTestRunner {
    protected ClubsPage clubsPage;
    protected HomePage homePage;
    protected SoftAssert softAssert;
    protected WebDriverWait wait;
    protected AdvancedSearchSiderComponent searchSiderComponent;

    @BeforeMethod
    public void setup() {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> homePage.header.isLoggedIn());
        clubsPage = homePage.header.clickClubsPageButton().waitUntilClubsPageIsLoaded(30);
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-59")
    public void verifyAllElementsArePresentAdvancedSearch() {
        verifyPageIsLoaded();
        verifySearchSiderElementsAreDisplayed();
        verifySearchDropdownsAreOpened();
        verifyAdditionalElementsAreDisplayed();
        softAssert.assertAll();
    }

    private void verifyPageIsLoaded() {
        softAssert.assertTrue(clubsPage.getAdvancedSearchClubHeader().getAdvancedSearchTextHeading().getText().contains("Гуртки в місті"),
                "The clubs page isn't opened");
        clubsPage.getAdvancedSearchClubHeader().clickAdvancedSearchIcon();
        searchSiderComponent = clubsPage.getSearchSider();
        wait.until(ExpectedConditions.visibilityOf(searchSiderComponent.getLabel()));
    }

    private void verifySearchSiderElementsAreDisplayed() {
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getLabel());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getDistrictTitle());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getMetroStationTitle());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getDirectionsTitle());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getOnlineTitle());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getAgeTitle());
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getAgeInput());
    }

    private void verifySearchDropdownsAreOpened() {
        searchSiderComponent.clickCityDropDown();
        softAssert.assertTrue(searchSiderComponent.getSearchCityElement().getDropDownElement().getItemsList().get(0).isDisplayed(),
                "The city dropdown isn't opened");
        searchSiderComponent.getLabel().click();
        searchSiderComponent.clickDistrictDropDown();
        softAssert.assertTrue(searchSiderComponent.getSearchDistrictElement().getDropDownElement().getItemsList().get(0).isDisplayed(),
                "The district dropdown isn't opened");
        searchSiderComponent.getLabel().click();
        searchSiderComponent.clickMetroDropDown();
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getSearchMetroElement().getDropDownElement().getItemsList().get(0));
    }

    private void verifyAdditionalElementsAreDisplayed() {
        verifyElementIsDisplayedAdvancedSearch(searchSiderComponent.getOnlineCheckBox());
        for (WebElement checkbox : searchSiderComponent.getDirectionsCheckBox()) {
            verifyElementIsDisplayedAdvancedSearch(checkbox);
        }
    }

    private void verifyElementIsDisplayedAdvancedSearch(WebElement el) {
        softAssert.assertTrue(el.isDisplayed());
    }
}
