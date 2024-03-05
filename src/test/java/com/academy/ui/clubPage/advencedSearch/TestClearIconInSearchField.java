package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.runners.HomePageTestRunner;
import org.testng.annotations.Test;


public class TestClearIconInSearchField extends HomePageTestRunner {


    private static final String SEARCH_TEXT_START = "A";
    private static final String CLUB_NAME = "Програмування, робототехніка, STEM";

    @Test(description = "TUA-317 Verify that clear icon appears when start typing text in search field and it does not overlap the long entered text")
    public void checkIfClearIconAppearsAndDoesNotOverlapText() {
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(SEARCH_TEXT_START);
        actions.moveToElement(homePage.advancedSearchHeaderComponent.getSearchInputCloseButton()).perform();

        softAssert.assertTrue(homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().isDisplayed());

        homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().click();
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(CLUB_NAME);

        int closeButtonLocation = homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().getLocation().getX();
        int inputFieldLocation = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getLocation().getX();
        int InputFieldWidth = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getSize().getWidth();

        boolean ifButtonOverlapText = closeButtonLocation <= inputFieldLocation + InputFieldWidth;

        softAssert.assertFalse(ifButtonOverlapText, "Button overlaps text");
        softAssert.assertAll();


    }


}
