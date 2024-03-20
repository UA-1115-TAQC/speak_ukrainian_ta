package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.ClubInfoPopUp;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestSearchField extends LoginWithAdminTestRunner {

    @Test
    @Description("Verify that the search results change with each new entered character")
    @Issue("TUA-825")
    public void verifySearchResultChangeWithEnteredCharacter() {
        String[] strings = new String[] {"С", "п", "о", "р", "т"};
        assertTrue(enterCharacters(strings));
    }

    private boolean enterCharacters(String[] strings) {
        ClubsPage clubsPage = homePage.getHeader().clickClubsPageButton();
        for(String str : strings){
            clubsPage = clubsPage.setTextHeaderSearch(str);
//            while(true) {

            if(!containsInput(clubsPage)){
                return false;
            }

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
        return true;
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
