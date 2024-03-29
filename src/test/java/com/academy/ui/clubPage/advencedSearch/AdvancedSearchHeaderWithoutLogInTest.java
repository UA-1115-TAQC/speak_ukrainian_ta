package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.components.advancedSearchHeader.AdvancedSearchTooltip;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class AdvancedSearchHeaderWithoutLogInTest extends BaseTestRunner {

    private AdvancedSearchHeaderComponent advancedSearchHeaderComponent;
    private AdvancedSearchTooltip advancedSearchTooltip;
    private SoftAssert softAssert;

    @BeforeMethod(description = "Preconditions: Get advancedSearchHeaderComponent, make softAssert object")
    public void advancedSearchHeaderComponentTest_setUp() {
        advancedSearchHeaderComponent = homePage.getAdvancedSearchHeaderComponent();
        softAssert = new SoftAssert();
    }

    @Test(description = "Test fails because search field isn't display list of Categories and Clubs")
    @Description("Verify that 'Категорії' and 'Гуртки' sections with list of "
            + "categories and clubs are displayed when to click on search field")
    @Issue("TUA-316")
    public void checkSearchFieldDisplayCategoriesAndClubs() {
        //Test will pass if you change base URL on https://speak-ukrainian.org.ua/
        advancedSearchTooltip = advancedSearchHeaderComponent.clickSelectionSearchInputField();
        HashMap<String, WebElement> categories = advancedSearchTooltip.getCategories();
        HashMap<String, WebElement> clubs = advancedSearchTooltip.getClubs();
        softAssert.assertFalse(categories.isEmpty());
        softAssert.assertFalse(clubs.isEmpty());

        softAssert.assertAll();
    }

    @Test(description = "Search field placeholder disappears while typing")
    @Description("Verify that there is a placeholder 'Який гурток шукаєте?' and it disappears when to start typing.")
    @Issue("TUA-314")
    public void verifyPlaceholderDisappearWhileTyping() {
        final String PLACEHOLDER = "Який гурток шукаєте?";
        final String TEXT = "A";
        WebElement searchPlaceholder = homePage.advancedSearchHeaderComponent.getSelectionSearchInputFieldPlaceholder();
        WebElement searchInput = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField();
        softAssert.assertEquals(searchPlaceholder.getText(), PLACEHOLDER);
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(TEXT);
        if (homePage.isElementPresent(searchPlaceholder)) {
            softAssert.assertNotEquals(searchPlaceholder.getText(), PLACEHOLDER);
        } else {
            softAssert.assertEquals(searchInput.getAttribute("value"), TEXT);
        }
        searchInput.sendKeys(Keys.BACK_SPACE);
        softAssert.assertEquals(searchPlaceholder.getText(), PLACEHOLDER);
        softAssert.assertAll();
    }

    @Test
    @Issue("TUA-315")
    public void checkRightLocationOfAdvancedSearch(){
        int advancedSearchX = advancedSearchHeaderComponent.getAdvancedSearchIcon().getLocation().getX();
        int searchFieldX = advancedSearchHeaderComponent.getSelectionSearchInputField().getLocation().getX();

        int distance = Math.abs(searchFieldX - advancedSearchX);
        int threshold = 280; // Threshold indicating that elements are next to each other
        softAssert.assertTrue(distance <= threshold, "Advanced search item is not located near the search field.");

        // Check if the "Advanced search" element is located to the right of the search element
        softAssert.assertTrue(advancedSearchX > searchFieldX, "Advanced search item is not located to the right of the search field.");
        softAssert.assertAll();
    }
}
