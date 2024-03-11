package com.academy.ui.homePage;

import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.DirectionTagComponent;
import com.academy.ui.runners.HomePageTestRunner;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;

import io.qameta.allure.Description;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SearchFieldInAdvancedHeader extends HomePageTestRunner {

    @Test(description = "TUA-44")
    @Issue("TUA-44")
    public void checkThatUserCanDoBasicSearch(){
        checkThatUserCanDoBasicSearchByString("American Gymnastics Club");
        checkThatUserCanDoBasicSearchByString("Сфера");
        //+Check the search result with DB //todo
        softAssert.assertAll();
    }
    @Test(description = "TUA-43")
    @Description("Verify that user can delete search query")
    public void checkThatUserCanDeleteSearchQuery(){
        home.advancedSearchHeaderComponent.setTextSelectionSearchInputField("Ансамбль");
        home.advancedSearchHeaderComponent.clickSelectionSearchCloseButton();

        home.advancedSearchHeaderComponent.setTextSelectionSearchInputField("Гурток");
        home.advancedSearchHeaderComponent.clickSelectionSearchCloseButton();

        home.sleep(1000);
        softAssert.assertTrue(home.getAdvancedSearchHeaderComponent().getTextSelectionSearchInputField().isEmpty(), "Search input field is not empty");
    }
    @Step("Check that user can do basic search by string: {string}")
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
        //+Check the search result with DB //todo
        driver.get(configProperties.getBaseUrl());
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("clubs")));
    }
}
