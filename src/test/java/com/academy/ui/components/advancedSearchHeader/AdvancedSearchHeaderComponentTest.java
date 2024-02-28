package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class AdvancedSearchHeaderComponentTest extends BaseTestRunner {

    private AdvancedSearchHeaderComponent advancedSearchHeaderComponent;
    private AdvancedSearchTooltip advancedSearchTooltip;
    private SoftAssert softAssert;

    @BeforeMethod
    public void advancedSearchHeaderComponentTest_setUp() {
        advancedSearchHeaderComponent = homePage.getAdvancedSearchHeaderComponent();
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-316")
    public void advancedSearchHeader_display_categories_and_clubs_ok() {
        //Test will pass if you change base URL on https://speak-ukrainian.org.ua/
        advancedSearchTooltip = advancedSearchHeaderComponent.clickSelectionSearchInputField();
        HashMap<String, WebElement> categories = advancedSearchTooltip.getCategories();
        HashMap<String, WebElement> clubs = advancedSearchTooltip.getClubs();
        softAssert.assertFalse(categories.isEmpty());
        softAssert.assertFalse(clubs.isEmpty());
        softAssert.assertAll();
    }
}
