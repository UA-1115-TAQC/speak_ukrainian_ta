package com.academy.ui.clubPage.advencedSearch;


import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestBasicSearchByNameOfACategory extends LoginWithAdminTestRunner {

    @Test
    @Description("Verify that user can perform basic search by name of a category")
    @Issue("TUA-227")
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

    @Test
    @Description("Verify that the system shows online clubs when the user chooses 'інше' in the categories")
    @Issue("TUA-883")
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
