package com.academy.ui.clubPage.advencedSearch;


import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestBasicSearchByNameOfACategory extends LoginWithAdminTestRunner {

    @Test(description = "TUA-227- Verify that user can perform basic search by name of a category")
    public void checkBasicSearchByNameOfCategory() {
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField("хореографія");
        homePage.advancedSearchHeaderComponent.clickSelectionSearchInputField();

        ClubsPage clubs = new ClubsPage(driver);
        List<ClubCardComponent> clubsCard = clubs.getClubCards();

        boolean containsExpectedText = clubsCard.stream()
                .flatMap(component -> component.getDirectionTags().stream())
                .anyMatch(component -> component.getText().contains("Танці, хореографія"));

        Assert.assertTrue(containsExpectedText);
    }

    @Test(description = "TUA-883-Verify that the system shows online clubs when the user chooses 'інше' in the categories")
    public void checkBasicSearchByNameOfCategoryOther() {
        homePage.advancedSearchHeaderComponent.setTextSelectionSearchInputField("Інше");
        homePage.advancedSearchHeaderComponent.clickSelectionSearchInputField();

        ClubsPage clubs = new ClubsPage(driver);
        List<ClubCardComponent> clubsCard = clubs.getClubCards();

        boolean containsExpectedText = clubsCard.stream().flatMap(component -> component.getDirectionTags().stream())
                .anyMatch(component -> component.getText().contains("Інше"));

        Assert.assertTrue(containsExpectedText);
    }

}
