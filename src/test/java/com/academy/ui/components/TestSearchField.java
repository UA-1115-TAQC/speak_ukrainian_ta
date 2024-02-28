package com.academy.ui.components;

import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSearchField extends BaseTestRunner {

    @Test(description = "TUA-314")
    public void verifyPlaceholderDisappearWhileTyping() {
        final String PLACEHOLDER = "Який гурток шукаєте?";
        final String TEXT = "A";
        SoftAssert softAssert = new SoftAssert();
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
}
