package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.components.ClubsPaginationComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestSearchField extends BaseTestRunner {

//    @Test(description = "TUA-314")
//    public void verifyPlaceholderDisappearWhileTyping() {
//        final String PLACEHOLDER = "Який гурток шукаєте?";
//        final String TEXT = "A";
//        SoftAssert softAssert = new SoftAssert();
//        WebElement searchPlaceholder = homePage.advancedSearchHeaderComponent.getSelectionSearchInputFieldPlaceholder();
//        WebElement searchInput = homePage.advancedSearchHeaderComponent.getSelectionSearchInputField();
//        softAssert.assertEquals(searchPlaceholder.getText(), PLACEHOLDER);
//        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField(TEXT);
//        if (homePage.isElementPresent(searchPlaceholder)) {
//            softAssert.assertNotEquals(searchPlaceholder.getText(), PLACEHOLDER);
//        } else {
//            softAssert.assertEquals(searchInput.getAttribute("value"), TEXT);
//        }
//        searchInput.sendKeys(Keys.BACK_SPACE);
//        softAssert.assertEquals(searchPlaceholder.getText(), PLACEHOLDER);
//        softAssert.assertAll();
//    }

    @Test(description = "TUA-825")
    public void verifySearchResultChangeWithEnteredCharacter() {
        String[] strs = new String[] {"С", "п", "о", "р", "т"};
        ClubsPage clubsPage = homePage.getHeader().clickClubsPageButton();
        for(String str : strs){
            clubsPage = clubsPage.setTextHeaderSearchField(str);
            while(true) {
                List<ClubCardComponent> clubs = clubsPage.getClubCards();
                assertTrue(containsInput(clubsPage));

//            TODO pagination
                ClubsPaginationComponent pagination = clubsPage.getSwitchPagination();
                if(pagination.isNextDisabled()){
                    break;
                }
                clubsPage = pagination.clickNext();

            }
        }
    }

    private boolean containsInput(ClubsPage clubsPage){
        String input = clubsPage.getAdvancedSearchClubHeader()
                    .getTextSelectionSearchInputField().toLowerCase();
        List<ClubCardComponent> clubs = clubsPage.getClubCards();
        for(ClubCardComponent club : clubs){
           String clubName = club.getClubName();
           if(!club.clubNameContains(input) && !club.directionsContains(input)){

               if(clubName.equals("Палац культури \"Дарниця\"")){
                   continue;
               }
               System.out.println(input + ": " + clubName);
//               ClubInfoPopUp popup = club.clickTitle();
//               if(!popup.directionsContains(input)){
                   return false;
//               }
//               popup.close();
           }
        }
        return true;
    }
}
