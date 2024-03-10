package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.components.elements.LocationSearchSiderElement;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchSiderWithoutLogInTest extends BaseTestRunner {

    private AdvancedSearchSiderComponent advancedSearchSider;
    private ClubsPage clubsPage;
    private SoftAssert softAssert;

    private record ClubAddress(String name, String address) {
    }

    private record ClubCategory(String name, List<String> categoryList) {
    }

    private record ClubAge(String name, List<Integer> ageList) {
    }

    @BeforeMethod(description = "Preconditions: Get clubsPage and advancedSearchSider, make softAssert object")
    public void advancedSearchSiderTestSetUp() {
        clubsPage = homePage.getAdvancedSearchHeaderComponent().clickAdvancedSearchIcon();
        advancedSearchSider = clubsPage.getSearchSider();
        softAssert = new SoftAssert();
    }

    @Test(description = "User can find a club in a certain location by city parameter")
    @Description("[Розширений пошук] Verify that the user can find a club in a certain location using the 'Місто' parameter")
    @Issue("TUA-245")
    public void verifyUserClubSearchCertainLocationByCityParameter() {

        final String DEFAULT_CITY = "Київ";
        final String SELECTED_CITY = "Харків";

        softAssert.assertTrue(advancedSearchSider.getWebElement().isDisplayed(),
                "AdvancedSearchSider should be displayed");
        LocationSearchSiderElement dropdownCity = advancedSearchSider.getSearchCityElement();
        String cityName = dropdownCity.getInputContent().getText();
        softAssert.assertEquals(cityName, DEFAULT_CITY);

        List<ClubAddress> allCardsAddresses = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsAddresses.addAll(getCardsFromCurrentPageByCity(currentPage));
        }
        allCardsAddresses.forEach(club -> softAssert.assertTrue(club.address.contains(DEFAULT_CITY),
                "Address of club " + club.name + " should contain selected city " + DEFAULT_CITY));

        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        softAssert.assertFalse(dropdownCity.clickDropDown().getDropDownElement().getItemsList().isEmpty(),
                "City dropdown shouldn't be empty");
        dropdownCity.selectItem(SELECTED_CITY);
        String newCityName = dropdownCity.getInputContent().getText();
        softAssert.assertEquals(newCityName, SELECTED_CITY);

        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            softAssert.assertTrue(clubCard.getAddress().getText().contains(SELECTED_CITY),
                    "Address in the Card should contain selected city " + SELECTED_CITY);
        }
        softAssert.assertAll();
    }

    @Step("Get cards from current page (#{page}) by city")
    private List<ClubAddress> getCardsFromCurrentPageByCity(int page) {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubAddress> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            clubsPage.getClubCards().forEach(card -> list.add(new ClubAddress(card.getTitle().getText(), card.getAddress().getText())));
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        }
        return list;
    }

    @Step("Get cards from current page")
    private List<String> getCardsFromCurrentPage() {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<String> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            clubsPage.getClubCards().forEach(card -> list.add(card.getTitle().getText()));
        }
        return list;
    }

    @Step("Get cards from current page (#{page}) by online parameter")
    private List<String> getCardsFromCurrentPageByOnline(int page) {
        final String ONLINE_TEXT = "Гурток онлайн";
        List<String> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            clubsPage.getClubCards().stream().filter(card -> card
                            .getWebElement()
                            .getAttribute("innerText")
                            .contains(ONLINE_TEXT))
                    .forEach(card -> list.add(card.getTitle().getText()));
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        }
        return list;
    }

    @Step("Get cards from current page (#{page}) by category")
    private List<ClubCategory> getCardsFromCurrentPageByCategory(int page) {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubCategory> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            for (ClubCardComponent card : clubsPage.getClubCards()) {
                ClubInfoPopUp clubInfoPopUp = card.clickTitle();
                clubInfoPopUp.waitPopUpOpen(5);
                List<String> directionsList = clubInfoPopUp
                        .getDirections()
                        .stream()
                        .map(direction -> direction.getName().getText())
                        .toList();
                list.add(new ClubCategory(card.getTitle().getText(), directionsList));
                clubInfoPopUp.getCloseButton().click();
            }
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        }
        return list;
    }

    @Step("Get cards from current page (#{page}) by age")
    private List<ClubAge> getCardsFromCurrentPageByAge(int page) {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubAge> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            for (ClubCardComponent card : clubsPage.getClubCards()) {
                ClubInfoPopUp clubInfoPopUp = card.clickTitle();
                clubInfoPopUp.waitPopUpOpen(5);
                list.add(new ClubAge(card.getTitle().getText(), clubInfoPopUp.getClubsAgeList()));
                clubInfoPopUp.getCloseButton().click();
            }
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        }
        return list;
    }

    @Step("Get pages number")
    private int getPagesNumber() {
        if (!clubsPage.isElementPresent(clubsPage.getSwitchPagination().getWebElement())) return 1;
        int pagesShowed = clubsPage.getSwitchPagination().getPaginationItems().size();
        String pagesNumber = clubsPage.getSwitchPagination()
                .getPaginationItems()
                .get(pagesShowed - 1)
                .getAttribute("title");
        return Integer.parseInt(pagesNumber);
    }

    @Test(description = "User can make search with 1 parameter")
    @Description("Verify that User can make search with 1 parameter.")
    @Issue("TUA-858")
    public void checkUserCanMakeSearchWith_1_Parameter() {
        final String SELECTED_CITY = "Харків";
        final String DEFAULT_CITY = "Київ";
        final String CATEGORY = "Спортивні секції";
        final String AGE = "7";
        softAssert.assertTrue(advancedSearchSider.getWebElement().isDisplayed(),
                "AdvancedSearchSider should be displayed");
        LocationSearchSiderElement dropdownCity = advancedSearchSider.getSearchCityElement();
        softAssert.assertFalse(dropdownCity.clickDropDown().getDropDownElement().getItemsList().isEmpty(),
                "City dropdown shouldn't be empty");

        dropdownCity.selectItem(SELECTED_CITY);
        String cityName = dropdownCity.getInputContent().getText();
        softAssert.assertEquals(cityName, SELECTED_CITY, "Dropdown value should be " + SELECTED_CITY);

        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubAddress> allCardsAddresses = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsAddresses.addAll(getCardsFromCurrentPageByCity(currentPage));
        }
        allCardsAddresses.forEach(club -> softAssert.assertTrue(club.address.contains(SELECTED_CITY),
                "Address of club " + club.name + " should contain selected city " + SELECTED_CITY));

        dropdownCity.clickDropDown().selectItem(DEFAULT_CITY);

        clubsPage = clubsPage.getSearchSider().checkOnlineCheckBox().waitUntilClubsPageIsLoaded(5);

        List<String> allCards = new ArrayList<>();
        List<String> allCardsOnline = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCards.addAll(getCardsFromCurrentPage());
            allCardsOnline.addAll(getCardsFromCurrentPageByOnline(currentPage));
        }
        softAssert.assertEquals(allCards.size(), allCardsOnline.size(),
                "All club's should contain label \"Гурток онлайн\"");

        clubsPage = clubsPage.getSearchSider().checkOnlineCheckBox().waitUntilClubsPageIsLoaded(5);

        List<String> allCardsAfterRestore = new ArrayList<>();
        List<String> allCardsOnlineAfterRestore = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsAfterRestore.addAll(getCardsFromCurrentPage());
            allCardsOnlineAfterRestore.addAll(getCardsFromCurrentPageByOnline(currentPage));
        }
        softAssert.assertNotEquals(allCardsAfterRestore.size(), allCardsOnlineAfterRestore.size(),
                "Cards list should restore all cards after filter with OnlineClubs is off");

        clubsPage = clubsPage.getSearchSider().checkDirectionCheckBox(CATEGORY).waitUntilClubsPageIsLoaded(5);

        List<ClubCategory> allCardsCategories = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsCategories.addAll(getCardsFromCurrentPageByCategory(currentPage));
        }
        allCardsCategories.forEach(card -> softAssert.assertTrue(card.categoryList.contains(CATEGORY),
                "Club " + card.name + " should contain category " + CATEGORY));

        clubsPage = clubsPage.getSearchSider().checkDirectionCheckBox(CATEGORY).waitUntilClubsPageIsLoaded(5);

        List<ClubCategory> allCardsCategoriesAfterRestore = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsCategoriesAfterRestore.addAll(getCardsFromCurrentPageByCategory(currentPage));
        }
        softAssert.assertNotEquals(allCardsCategoriesAfterRestore.size(), allCardsCategories.size(),
                "Cards list should restore all cards after filter with Category is off");

        clubsPage = clubsPage.getSearchSider().enterAge(AGE).waitUntilClubsPageIsLoaded(5);
        List<ClubAge> allCardsByAge = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsByAge.addAll(getCardsFromCurrentPageByAge(currentPage));
        }
        int currentAge = Integer.parseInt(AGE);
        allCardsByAge.forEach(club -> softAssert
                .assertTrue(currentAge >= club.ageList().get(0) && currentAge <= club.ageList().get(1),
                        "Age " + AGE + " in club " + club.name + " should be in a range from "
                                + club.ageList().get(0) + " to " + club.ageList().get(1)));

        clubsPage.getSearchSider().clearAgeInput();

        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);

        List<String> allCardsByAgeAfterRestore = new ArrayList<>();
        for (int currentPage = 0; currentPage < getPagesNumber(); currentPage++) {
            allCardsByAgeAfterRestore.addAll(getCardsFromCurrentPage());
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(currentPage + 1));
        }
        softAssert.assertNotEquals(allCardsByAgeAfterRestore.size(), allCardsByAge.size(),
                "Cards list should restore all cards after filter with Age is empty");

        softAssert.assertAll();
    }

}
