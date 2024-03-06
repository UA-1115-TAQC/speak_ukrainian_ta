package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddClubPopUpWithManagerProfilePage extends LoginWithManagerTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-116")
    public void checkDifferentWaysToAddClub() {
        AddClubPopUpComponent addClubPopUpComponent= homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(10);
        softAssert.assertTrue(addClubPopUpComponent.isOpen(),
                "PopUp not opened clicking the button 'Add club' in HomePage");

        addClubPopUpComponent.getCloseButton().click();

        ProfilePage profilePage = homePage.header.openUserMenu().clickProfile();
        AddClubPopUpComponent addClubPopUp = profilePage.openAddClubPopUp();
        softAssert.assertTrue(addClubPopUp.getWebElement().isDisplayed(),
                "PopUp not opened clicking the button 'Add club' in ProfilePage");

        softAssert.assertAll();
    }
}
