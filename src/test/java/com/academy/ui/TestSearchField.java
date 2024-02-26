package com.academy.ui;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSearchField extends BaseTestRunner {

    @Test
    public void verifyPlaceholderDisappearWhileTyping() {
        final String PLACEHOLDER = "Який гурток шукаєте?";
        final String TEXT = "A";
        SoftAssert softAssert = new SoftAssert();
        AdvancedSearchHeaderComponent searchHeaderComponent = homePage.advancedSearchHeaderComponent;
        softAssert.assertEquals(searchHeaderComponent
                        .getSelectionSearchInputFieldPlaceholder()
                        .getText(),
                PLACEHOLDER
        );
        searchHeaderComponent.setTextSelectionSearchInputField(TEXT);
        try {
            searchHeaderComponent.getSelectionSearchInputFieldPlaceholder().isDisplayed();
        } catch (NoSuchElementException e) {
            softAssert.assertEquals(searchHeaderComponent
                            .getSelectionSearchInputField()
                            .getAttribute("value"),
                    TEXT
            );
        }
        searchHeaderComponent.getSelectionSearchInputField().sendKeys(Keys.BACK_SPACE);
        softAssert.assertEquals(searchHeaderComponent
                        .getSelectionSearchInputFieldPlaceholder()
                        .getText(),
                PLACEHOLDER
        );
        softAssert.assertAll();
    }
}
