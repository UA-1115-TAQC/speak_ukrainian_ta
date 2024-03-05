package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.components.elements.LocationSearchSiderElement;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.DirectionTagComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedSearchSiderWithoutLogInTest extends BaseTestRunner {

    private AdvancedSearchSiderComponent advancedSearchSider;
    private ClubsPage clubsPage;
    private SoftAssert softAssert;

    private record ClubAddress(String name, String address) {
    }

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

        List<ClubAddress> allCardsAddresses = new ArrayList<>();
        int currentPage = 1;
        while (currentPage < getPagesNumber()) {
            allCardsAddresses.addAll(getCardsFromCurrentPageByCity(currentPage, DEFAULT_CITY));
            currentPage++;
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

    private List<ClubAddress> getCardsFromCurrentPageByCity(int page, String city) {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubAddress> list = new ArrayList<>();
        clubsPage.getClubCards().forEach(card -> list.add(new ClubAddress(card.getTitle().getText(), card.getAddress().getText())));
        clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        return list;
    }

    private int getPagesNumber() {
        int pagesShowed = clubsPage.getSwitchPagination().getPaginationItems().size();
        String pagesNumber = clubsPage.getSwitchPagination()
                .getPaginationItems()
                .get(pagesShowed - 1)
                .getAttribute("title");
        return Integer.parseInt(pagesNumber);
    }

    @Test(description = "TUA-858")
    public void checkUserCanMakeSearchWith_1_Parameter() {
        final String SELECTED_CITY = "Харків";
        final String DEFAULT_CITY = "Київ";
        final String CATEGORY = "Спортивні секції";
        final String AGE = "7";
        final String ONLINE_CLUB_TEXT = "Гурток онлайн";
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
        int currentPage = 1;
        while (currentPage < getPagesNumber()) {
            allCardsAddresses.addAll(getCardsFromCurrentPageByCity(currentPage, SELECTED_CITY));
            currentPage++;
        }
        allCardsAddresses.forEach(club -> softAssert.assertTrue(club.address.contains(SELECTED_CITY),
                "Address of club " + club.name + " should contain selected city " + SELECTED_CITY));
        dropdownCity.clickDropDown().selectItem(DEFAULT_CITY);

        clubsPage = clubsPage.getSearchSider().checkOnlineCheckBox().waitUntilClubsPageIsLoaded(5);
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            softAssert.assertTrue(clubCard
                            .getWebElement()
                            .getAttribute("innerText")
                            .contains(ONLINE_CLUB_TEXT),
                    "Club " + clubCard.getTitle().getText() + " should contain label \"Гурток онлайн\"");
        }
        clubsPage = clubsPage.getSearchSider().checkOnlineCheckBox().waitUntilClubsPageIsLoaded(10);

        int countOnlineClubs = 0;
        for (ClubCardComponent card : clubsPage.getClubCards()) {
            if (card.getWebElement().getAttribute("innerText").contains(ONLINE_CLUB_TEXT)) {
                countOnlineClubs++;
            }
        }
        softAssert.assertNotEquals(clubsPage.getClubCards().size(), countOnlineClubs,
                "Cards list should restore all cards after filter with OnlineClubs is off");

        clubsPage = clubsPage.getSearchSider().checkDirectionCheckBox(CATEGORY).waitUntilClubsPageIsLoaded(5);
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            List<String> list = new ArrayList<>();
            for (DirectionTagComponent direction : clubCard.getDirections()) {
                list.add(direction.getName().getText());
            }
            softAssert.assertTrue(list.contains(CATEGORY),
                    "Club " + clubCard.getTitle().getText() + " should contain category " + CATEGORY);
        }
        clubsPage = clubsPage.getSearchSider().checkDirectionCheckBox(CATEGORY).waitUntilClubsPageIsLoaded(5);
        int countCategories = 0;
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            countCategories += clubCard.getListOfDirectionsTitles().contains(CATEGORY) ? 1 : 0;
        }
        softAssert.assertNotEquals(clubsPage.getClubCards().size(), countCategories,
                "Cards list should restore all cards after filter with Category is off");

        clubsPage = clubsPage.getSearchSider().enterAge(AGE).waitUntilClubsPageIsLoaded(5);
        ;
        int currentAge = Integer.parseInt(AGE);
        for (ClubCardComponent clubCard : clubsPage.getClubCards()) {
            ClubInfoPopUp clubInfoPopUp = clubCard.clickTitle();
            List<Integer> age = clubInfoPopUp.getClubsAgeList();
            softAssert.assertTrue(currentAge >= age.get(0) && currentAge <= age.get(1),
                    "Age " + AGE + " should be in a range from " + age.get(0) + " to " + age.get(1));
            clubInfoPopUp.getCloseButton().click();
        }

        softAssert.assertAll();
    }

}
