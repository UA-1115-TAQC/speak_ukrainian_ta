package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditClubWithManager extends LoginWithManagerTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;


    @BeforeMethod
    public void editProfilePageWithUserTest_setUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();
    }


    @Test(description = "TUA-82")
    public void checkUserCanChangePhotoWhileEditClub() {
        final String CLUB_NAME = "referfreerqerfr";
        final String IMAGE_PATH = "speak_ukrainian_ta/src/test/resources/images/image.png";
        profilePage.sleep(3000);

        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(CLUB_NAME);

        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();
        //stepThree.getClubCoverDownloadButton().click();
        stepThree.getClubDescriptionTextarea().click();
        stepThree.sleep(3000);


    }
}
