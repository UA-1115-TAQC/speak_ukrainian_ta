package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.runners.BaseTestRunner;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class AdvancedSearchHeaderComponentTest extends BaseTestRunner {

    private AdvancedSearchHeaderComponent advancedSearchHeaderComponent;
    private AdvancedSearchTooltip advancedSearchTooltip;

    @BeforeMethod
    public void advancedSearchHeaderComponentTest_setUp() {
        advancedSearchHeaderComponent = homePage.getAdvancedSearchHeaderComponent();
    }

    @Test(description = "TUA-316")
    @Description(value = "Test will pass if you change base URL on https://speak-ukrainian.org.ua/")
    public void advancedSearchHeader_display_categories_and_clubs_ok() {
        advancedSearchTooltip = advancedSearchHeaderComponent.clickSelectionSearchInputField();
        HashMap<String, WebElement> categories = advancedSearchTooltip.getCategories();
        HashMap<String, WebElement> clubs = advancedSearchTooltip.getClubs();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(categories.isEmpty());
        softAssert.assertFalse(clubs.isEmpty());
        softAssert.assertAll();
    }
}
