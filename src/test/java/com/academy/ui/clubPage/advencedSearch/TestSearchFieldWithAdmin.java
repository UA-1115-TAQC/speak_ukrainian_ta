package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSearchFieldWithAdmin extends LoginWithAdminTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp_init() {
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Checks that the basic search UI matches to the mock-up")
    @Issue("TUA-142")
    public void checkBasicSearchFieldUi() {
        var searchComponent = homePage.getAdvancedSearchHeaderComponent();
        final var expectedInputWidth = "252px";
        final var expectedInputHeight = "24px";
        final var expectedIconWidth = "24px";
        final var expectedIconHeight = "24px";
        final var expectedSearchInputTitle = "Який гурток шукаєте?";

        softAssert.assertTrue(searchComponent.getSelectionSearchInputFieldPlaceholder().isDisplayed(),
                "Search input field should be displayed");
        softAssert.assertEquals(searchComponent.getSelectionSearchInputFieldPlaceholder()
                .getText(), expectedSearchInputTitle, "Search input placeholder expected to be: %s"
                .formatted(expectedSearchInputTitle));
        softAssert.assertEquals(searchComponent.getSelectionSearchInputFieldPlaceholder()
                .getCssValue("width"), expectedInputWidth,
                "Search input field width expected to be: %s.".formatted(expectedInputWidth));
        softAssert.assertEquals(searchComponent.getSelectionSearchInputFieldPlaceholder()
                .getCssValue("height"), expectedInputHeight,
                "Search input field height expected to be: %s.".formatted(expectedInputHeight));

        softAssert.assertTrue(searchComponent.getSearchIcon().isDisplayed(),
                "Search icon should be displayed");
        softAssert.assertEquals(searchComponent.getSearchIcon()
                .getCssValue("width"), expectedIconWidth,
                "Search icon width expected to be: %s.".formatted(expectedIconWidth));
        softAssert.assertEquals(searchComponent.getSearchIcon()
                .getCssValue("height"), expectedIconHeight,
                "Search icon height expected to be: %s.".formatted(expectedIconHeight));

        softAssert.assertTrue(searchComponent.getAdvancedSearchIcon().isDisplayed(),
                "Advanced search icon should be displayed");
        softAssert.assertEquals(searchComponent.getAdvancedSearchIcon()
                .getCssValue("width"), expectedIconWidth,
                "Advanced search icon width expected to be: %s.".formatted(expectedIconWidth));
        softAssert.assertEquals(searchComponent.getAdvancedSearchIcon()
                .getCssValue("height"), expectedIconHeight,
                "Advanced search icon height expected to be: %s.".formatted(expectedIconHeight));

        softAssert.assertAll();
    }
}
