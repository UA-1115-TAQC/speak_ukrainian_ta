package com.academy.ui.clubPage.advencedSearch;


import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestBasicSearchByNameOfACategory extends LoginWithAdminTestRunner {

    @Test(description = "TUA-227")

    public void checkThatUserCanPerformBasicSearchByNameOfCategory(){
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField("хореографія");
        homePage.advancedSearchHeaderComponent.clickSelectionSearchInputField();
        ClubsPage clubs = new ClubsPage(driver);
        List<ClubCardComponent> clubs2  = clubs.getClubCards();
        String expectedTitle = "танці";
        for (ClubCardComponent club : clubs2){
            String actualCategory = club.getCategoryClub();
            System.out.println(actualCategory);

        }


    }
}
