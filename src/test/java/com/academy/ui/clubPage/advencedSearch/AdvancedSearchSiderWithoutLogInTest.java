package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.components.elements.LocationSearchSiderElement;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    private List<ClubAddress> getCardsFromCurrentPageByCity(int page) {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<ClubAddress> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            clubsPage.getClubCards().forEach(card -> list.add(new ClubAddress(card.getTitle().getText(), card.getAddress().getText())));
            clubsPage.getSwitchPagination().clickPagItemByNum(String.valueOf(page + 1));
        }
        return list;
    }

    private List<String> getCardsFromCurrentPage() {
        clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(5);
        List<String> list = new ArrayList<>();
        if (!clubsPage.isClubsPageEmpty()) {
            clubsPage.getClubCards().forEach(card -> list.add(card.getTitle().getText()));
        }
        return list;
    }

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

    private int getPagesNumber() {
        if (!clubsPage.isElementPresent(clubsPage.getSwitchPagination().getWebElement())) return 1;
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

    @Test
    @Issue("TUA-238")
    public void checkAdvancedSearchUI() {
        softAssert.assertTrue(advancedSearchSider.getWebElement().isDisplayed(),
                "AdvancedSearchSider should be displayed");
        softAssert.assertTrue(advancedSearchSider.getLabel().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getClubOrCenterTitle().isDisplayed());
        softAssert.assertFalse(advancedSearchSider.getCenterOrClubRadioButton().isEmpty());
        softAssert.assertTrue(advancedSearchSider.getCheckedRadioButton().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getCityTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDistrictTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getMetroStationTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getOnlineTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getOnlineCheckBox().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(0).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(1).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(2).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(3).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(4).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(5).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(6).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(7).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(8).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(9).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(10).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(10).isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getAgeTitle().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getAgeInput().isDisplayed());
        softAssert.assertTrue(advancedSearchSider.getYears().isDisplayed());

        int expectedX = advancedSearchSider.getSiderBox().getLocation().getX();
        int labelX = advancedSearchSider.getLabel().getLocation().getX();
        int clubOrCenterTitleX = advancedSearchSider.getClubOrCenterTitle().getLocation().getX();
        int clubRadioButtonX = advancedSearchSider.getCenterOrClubRadioButton().get(0).getLocation().getX();
        int centerRadioButtonX = advancedSearchSider.getCenterOrClubRadioButton().get(1).getLocation().getX();
        int cityX = advancedSearchSider.getCityTitle().getLocation().getX();
        int districtX = advancedSearchSider.getDistrictTitle().getLocation().getX();
        int metroX = advancedSearchSider.getMetroStationTitle().getLocation().getX();
        int onlineX = advancedSearchSider.getOnlineTitle().getLocation().getX();
        int onlineCheckX = advancedSearchSider.getOnlineCheckBox().getLocation().getX();
        int directionTitleX = advancedSearchSider.getDirectionsTitle().getLocation().getX();
        int d0X = advancedSearchSider.getDirectionsCheckBox().get(0).getLocation().getX();
        int d1X = advancedSearchSider.getDirectionsCheckBox().get(1).getLocation().getX();
        int d2X = advancedSearchSider.getDirectionsCheckBox().get(2).getLocation().getX();
        int d3X = advancedSearchSider.getDirectionsCheckBox().get(3).getLocation().getX();
        int d4X = advancedSearchSider.getDirectionsCheckBox().get(4).getLocation().getX();
        int d5X = advancedSearchSider.getDirectionsCheckBox().get(5).getLocation().getX();
        int d6X = advancedSearchSider.getDirectionsCheckBox().get(6).getLocation().getX();
        int d7X = advancedSearchSider.getDirectionsCheckBox().get(7).getLocation().getX();
        int d8X = advancedSearchSider.getDirectionsCheckBox().get(8).getLocation().getX();
        int d9X = advancedSearchSider.getDirectionsCheckBox().get(9).getLocation().getX();
        int d10X = advancedSearchSider.getDirectionsCheckBox().get(10).getLocation().getX();
        int ageTitleX = advancedSearchSider.getAgeTitle().getLocation().getX();
        int ageInputX = advancedSearchSider.getYears().getLocation().getX();
        softAssert.assertEquals(labelX, expectedX, "1");
        softAssert.assertEquals(clubOrCenterTitleX, expectedX, "2");
        softAssert.assertEquals(clubRadioButtonX, expectedX, "3");
        softAssert.assertEquals(centerRadioButtonX, expectedX, "3");
        softAssert.assertEquals(cityX, expectedX, "4");
        softAssert.assertEquals(districtX, expectedX, "5");
        softAssert.assertEquals(metroX, expectedX, "6");
        softAssert.assertEquals(onlineX, expectedX, "7");
        softAssert.assertEquals(onlineCheckX, expectedX, "8");
        softAssert.assertEquals(directionTitleX, expectedX, "9");
        softAssert.assertEquals(d0X, expectedX, "10");
        softAssert.assertEquals(d1X, expectedX, "11");
        softAssert.assertEquals(d2X, expectedX, "12");
        softAssert.assertEquals(d3X, expectedX, "13");
        softAssert.assertEquals(d4X, expectedX, "14");
        softAssert.assertEquals(d5X, expectedX, "15");
        softAssert.assertEquals(d6X, expectedX, "16");
        softAssert.assertEquals(d7X, expectedX, "17");
        softAssert.assertEquals(d8X, expectedX, "18");
        softAssert.assertEquals(d9X, expectedX, "19");
        softAssert.assertEquals(d10X, expectedX, "20");
        softAssert.assertEquals(ageTitleX, expectedX, "21");
        softAssert.assertEquals(ageInputX, expectedX, "22");

        softAssert.assertEquals(advancedSearchSider.getLabel().getText(),"Розширений пошук");
        softAssert.assertEquals(advancedSearchSider.getClubOrCenterTitle().getText(), "Гурток/Центр");
        softAssert.assertEquals(advancedSearchSider.getCenterOrClubRadioButton().get(0).getText(), "Гурток");
        softAssert.assertEquals(advancedSearchSider.getCenterOrClubRadioButton().get(1).getText(), "Центр");
        softAssert.assertEquals(advancedSearchSider.getCityTitle().getText(), "Місто");
        softAssert.assertEquals(advancedSearchSider.getDistrictTitle().getText(), "Район міста");
        softAssert.assertEquals(advancedSearchSider.getMetroStationTitle().getText(), "Найближча станція метро");
        softAssert.assertEquals(advancedSearchSider.getOnlineTitle().getText(), "Ремоут");
        softAssert.assertEquals(advancedSearchSider.getOnlineCheckBox().getText(), "Доступний онлайн");
        softAssert.assertEquals(advancedSearchSider.getDirectionsTitle().getText(), "Категорії");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(0).getText(), "Спортивні секції");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(1).getText(), "Танці, хореографія");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(2).getText(), "Студії раннього розвитку");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(3).getText(), "Програмування, робототехніка, STEM");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(4).getText(), "Художня студія, мистецтво, дизайн");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(5).getText(), "Вокальна студія, музика, музичні інструменти");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(6).getText(), "Акторська майстерність, театр");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(7).getText(), "Особистісний розвиток");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(8).getText(), "Журналістика, дитяче телебачення, монтаж відео");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(9).getText(), "Центр розвитку");
        softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(10).getText(), "Інше");
        softAssert.assertEquals(advancedSearchSider.getAgeTitle().getText(), "Вік дитини");
        softAssert.assertEquals(advancedSearchSider.getYears().getText(), "років");

        softAssert.assertEquals(advancedSearchSider.getLabel().getCssValue("color"),"rgba(45, 76, 104, 1)");
        softAssert.assertEquals(advancedSearchSider.getLabel().getCssValue("font-size"),"24px");
        softAssert.assertEquals(advancedSearchSider.getClubOrCenterTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getClubOrCenterTitle().getCssValue("font-size"), "19px");
        softAssert.assertEquals(advancedSearchSider.getCenterOrClubRadioButton().get(0).getCssValue("color"), "rgba(89, 89, 89, 1)");
        softAssert.assertEquals(advancedSearchSider.getCenterOrClubRadioButton().get(1).getCssValue("color"), "rgba(89, 89, 89, 1)");
        softAssert.assertEquals(advancedSearchSider.getCityTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getCityTitle().getCssValue("font-size"), "19px");
        softAssert.assertEquals(advancedSearchSider.getDistrictTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getDistrictTitle().getCssValue("font-size"), "19px");
        softAssert.assertEquals(advancedSearchSider.getMetroStationTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getMetroStationTitle().getCssValue("font-size"), "19px");
        softAssert.assertEquals(advancedSearchSider.getOnlineCheckBox().getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(advancedSearchSider.getOnlineCheckBox().getCssValue("font-size"), "14px");
        softAssert.assertEquals(advancedSearchSider.getDirectionsTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getDirectionsTitle().getCssValue("font-size"), "19px");
        for (int i = 0; i < 11; i++) {
            softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(i).getCssValue("color"), "rgba(89, 89, 89, 1)");
            softAssert.assertEquals(advancedSearchSider.getDirectionsCheckBox().get(i).getCssValue("font-size"), "14px");
        }
        softAssert.assertEquals(advancedSearchSider.getAgeTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(advancedSearchSider.getAgeTitle().getCssValue("font-size"), "19px");
        softAssert.assertEquals(advancedSearchSider.getYears().getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(advancedSearchSider.getYears().getCssValue("font-size"), "14px");

        Actions actions = new Actions(driver);
        WebElement startingElement = advancedSearchSider.getCenterOrClubRadioButton().get(0);
        startingElement.click();
//        actions.sendKeys(Keys.TAB).perform();
//        softAssert.assertTrue(advancedSearchSider.getCenterOrClubRadioButton().get(0).equals(driver.switchTo().activeElement()),
//                "Focus should be on radio button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getSearchCityElement().getDropDownElement().getWebElement().equals(driver.switchTo().activeElement()),
                "Focus should be on city dropdown");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getSearchDistrictElement().getDropDownElement().equals(driver.switchTo().activeElement()),
                "Focus should be on district dropdown");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getSearchMetroElement().getDropDownElement().equals(driver.switchTo().activeElement()),
                "Focus should be on search metro dropdown");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getOnlineCheckBox().equals(driver.switchTo().activeElement()),
                "Focus should be on online checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(0).equals(driver.switchTo().activeElement()),
                "Focus should be on 1 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(1).equals(driver.switchTo().activeElement()),
                "Focus should be on 2 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(2).equals(driver.switchTo().activeElement()),
                "Focus should be on 3 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(3).equals(driver.switchTo().activeElement()),
                "Focus should be on 4 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(4).equals(driver.switchTo().activeElement()),
                "Focus should be on 5 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(5).equals(driver.switchTo().activeElement()),
                "Focus should be on 6 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(6).equals(driver.switchTo().activeElement()),
                "Focus should be on 7 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(7).equals(driver.switchTo().activeElement()),
                "Focus should be on 8 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(8).equals(driver.switchTo().activeElement()),
                "Focus should be on 9 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(9).equals(driver.switchTo().activeElement()),
                "Focus should be on 10 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getDirectionsCheckBox().get(10).equals(driver.switchTo().activeElement()),
                "Focus should be on 11 direction");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(advancedSearchSider.getAgeInput().equals(driver.switchTo().activeElement()),
                "Focus should be on age input");

        softAssert.assertAll();
    }

}
