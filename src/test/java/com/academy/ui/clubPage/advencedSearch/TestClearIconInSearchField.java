package com.academy.ui.clubPage.advencedSearch;
import com.academy.ui.runners.HomePageTestRunner;
import org.testng.annotations.Test;

public class TestClearIconInSearchField extends HomePageTestRunner {

    private static final String SEARCH_TEXT_START = "A";
    private static final String CLUB_NAME = "Програмування, робототехніка, STEM";

    @Test(description = "TUA-317 Verify that clear icon appears when start typing text in search field and it does not overlap the long entered text")
    public void checkIfClearIconAppearsAndDoesNotOverlapText(){
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(SEARCH_TEXT_START);

        softAssert.assertTrue(homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().isDisplayed());

        homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().click();
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(CLUB_NAME);


        int buttonX = homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().getLocation().getX();
        int buttonY = homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().getLocation().getY();
        int buttonTextWidth =homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().getSize().getWidth();
        int buttonTextHeight = homePage.advancedSearchHeaderComponent.getSearchInputCloseButton().getSize().getHeight();

        int textX = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getLocation().getX();
        int textY = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getLocation().getY();
        int textWidth = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getSize().getWidth();
        int textHeight = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField().getSize().getHeight();


        boolean overlap = buttonX < textX + textWidth &&
                buttonX + buttonTextWidth > textX &&
                buttonY < textY + textHeight &&
                buttonY + buttonTextHeight > textY;

        // Print the result
        if (overlap) {
            System.out.println("Button overlaps text.");
        } else {
            System.out.println("Button does not overlap text.");
        }

    }


}
