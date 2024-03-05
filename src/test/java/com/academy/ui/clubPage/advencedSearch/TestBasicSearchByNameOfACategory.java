package com.academy.ui.clubPage.advencedSearch;


import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
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
        List<ClubCardComponent> clubs2 = clubs.getClubCards();

        boolean containsExpectedText = clubs2.stream()
                .flatMap(component -> component.getDirectionTags().stream())
                .anyMatch(component -> component.getText().contains("Танці, хореографія"));

        Assert.assertTrue(containsExpectedText);
    }

}
