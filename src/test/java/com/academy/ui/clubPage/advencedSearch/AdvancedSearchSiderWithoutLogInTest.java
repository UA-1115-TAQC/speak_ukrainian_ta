package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.AddLocationPopUpComponent.DropdownElement;
import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.elements.LocationSearchSiderElement;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AdvancedSearchSiderWithoutLogInTest extends BaseTestRunner {

    private AdvancedSearchSiderComponent advancedSearchSider;
    private ClubsPage clubsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void advancedSearchSiderTestSetUp() {
        clubsPage = homePage.getAdvancedSearchHeaderComponent().clickAdvancedSearchIcon();
        advancedSearchSider = clubsPage.getSearchSider();
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-245")
    public void verifyUserClubSearchCertainLocationByCityParameter() {
        final String DEFAULT_CITY = "Київ";
        final String SELECTED_CITY = "Харків";
        softAssert.assertTrue(advancedSearchSider.getWebElement().isDisplayed(),
                "AdvancedSearchSider should be displayed");
        LocationSearchSiderElement dropdownCity = advancedSearchSider.getSearchCityElement();
        String cityName = dropdownCity.getInputContent().getText();
        softAssert.assertEquals(cityName, DEFAULT_CITY);
        clubsPage.waitUntilClubsPageIsLoaded(5);
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            softAssert.assertTrue(clubCard.getAddress().getText().contains(DEFAULT_CITY),
                    "Address in the Card should contain selected city " + cityName);
        }
        softAssert.assertFalse(dropdownCity.clickDropDown().getDropDownElement().getItemsList().isEmpty(),
                "City dropdown shouldn't be empty");
        dropdownCity.selectItem(SELECTED_CITY);
        String newCityName = dropdownCity.getInputContent().getText();
        softAssert.assertEquals(newCityName, SELECTED_CITY);
        clubsPage = clubsPage.waitUntilClubsPageIsLoaded(5);
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            softAssert.assertTrue(clubCard.getAddress().getText().contains(SELECTED_CITY),
                    "Address in the Card should contain selected city " + newCityName);
        }
        softAssert.assertAll();
    }

    @Test(description = "TUA-259-Verify that user can find club using all parameters of advanced search")
    public void checkIfUserCanFindClubUsingAdvancedSearch() throws InterruptedException {
        advancedSearchSider.getSearchDistrictElement().clickDropDown().selectItem("Деснянський");
        advancedSearchSider.getSearchMetroElement().clickDropDown().selectItem("Троєщина");
        advancedSearchSider.getOnlineCheckBox().click();
        clubsPage = new ClubsPage(driver);
        advancedSearchSider.checkDirectionCheckBox("Спортивні секції");
        clubsPage = new ClubsPage(driver);
        advancedSearchSider.enterAge("8");



        //List<ClubCardComponent> clubCards = new ClubsPage(driver).getClubCards();

        softAssert.assertTrue(clubsPage.getClubCards().stream()
                .flatMap(component -> component.getDirectionTags().stream())
                .anyMatch(component -> component.getText().contains("Спортивні секції")));
        softAssert.assertAll();

    }


}