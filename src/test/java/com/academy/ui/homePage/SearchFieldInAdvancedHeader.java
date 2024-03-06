package com.academy.ui.homePage;

import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.DirectionTagComponent;
import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SearchFieldInAdvancedHeader extends HomePageTestRunner {

    @Test(description = "TUA-44")
    public void checkThatUserCanDoBasicSearch(){
        checkThatUserCanDoBasicSearchByString("American Gymnastics Club");
        checkThatUserCanDoBasicSearchByString("Сфера");
        //+Check the search result with DB
        softAssert.assertAll();
    }
    @Test(description = "TUA-43")
    public void checkThatUserCanDeleteSearchQuery(){
        home.advancedSearchHeaderComponent.setTextSelectionSearchInputField("Ансамбль");
        home.advancedSearchHeaderComponent.clickSelectionSearchCloseButton();

        home.advancedSearchHeaderComponent.setTextSelectionSearchInputField("Гурток");
        home.advancedSearchHeaderComponent.clickSelectionSearchCloseButton();

        softAssert.assertTrue(home.getAdvancedSearchHeaderComponent().getTextSelectionSearchInputField().isEmpty(), "Search input field is not empty");
    }
    private void checkThatUserCanDoBasicSearchByString(String string){
        string = string.trim().toLowerCase();
        home.advancedSearchHeaderComponent.setTextSelectionSearchInputField(string);
        ClubsPage clubsPage = new ClubsPage(driver).waitUntilClubsPageIsLoaded(30);
        for(ClubCardComponent card: clubsPage.getClubCards()){
            for (DirectionTagComponent tag : card.getDirections()){
                softAssert.assertTrue(card.getTitle().getText().trim().toLowerCase().contains(string) ||
                                card.getDescription().getText().trim().toLowerCase().contains(string) ||
                            tag.getName().getText().trim().toLowerCase().contains(string)
                        ,
                        "The title of the shown card " + card.getTitle().getText() +
                                " or the description " + card.getDescription().getText() +
                                " or the tags" + card.getDirections().toString()+
                                " doesn't contain the search query " + string);
            }
        }
        home.sleep(1000);
        //+Check the search result with DB
        driver.get(configProperties.getBaseUrl());
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("clubs")));
    }
}
