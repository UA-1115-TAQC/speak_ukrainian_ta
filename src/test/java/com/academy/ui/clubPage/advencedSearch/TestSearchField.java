package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;

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

    @Test
    public void verifySearchResultChangeWithEnteredCharacter() {
        String[] strings = new String[] {"С", "п", "о", "р", "т"};
        ClubsPage clubsPage = homePage.getHeader().clickClubsPageButton();
        for(String str : strings){
            clubsPage = clubsPage.setTextHeaderSearch(str);
//            while(true) {

            assertTrue(containsInput(clubsPage));

//        pagination functionality is not implemented on the page

//                ClubsPaginationComponent pagination = clubsPage.getSwitchPagination();
//                if(pagination == null){
//                    break;
//                }
//                if(pagination.isNextDisabled()){
//                    clubsPage = pagination.clickPagItemByNum("1");
//                    break;
//                }
//                clubsPage = pagination.clickNext();
//            }
        }
    }

    private boolean containsInput(ClubsPage clubsPage){
        String input = clubsPage.getAdvancedSearchClubHeader()
                    .getTextSelectionSearchInputField();
        List<ClubCardComponent> clubs = clubsPage.getClubCards();
        for(ClubCardComponent club : clubs){
           if(!club.clubNameContains(input) && !club.directionsContains(input) && !club.descriptionContains(input)){
               ClubInfoPopUp popup = club.clickTitle();
               if(!popup.directionsContains(input)  && !popup.descriptionContains(input)){
                   return false;
               }
               popup.close();
           }
        }
        return true;
    }

}
