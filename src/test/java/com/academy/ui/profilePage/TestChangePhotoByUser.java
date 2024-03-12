package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestChangePhotoByUser extends LogInWithUserTestRunner {
    private static final String PHOTO_PROFILE_200x200 = "butterfly png 200x200.png";

    private static final String PHOTO_PROFILE_7360x4912 = "image jpg 4,6 mb.jpg";
    private ProfilePage profilePage;
    private SoftAssert softAssert;


    @BeforeMethod
    public void editProfilePageWithUserTest_setUp() {
        profilePage = homePage.header.openUserMenu().clickProfile();
        softAssert = new SoftAssert();
    }

    @Test(description = "Verify that the user as 'Відвідувач' is able to choose the photo according to the validity rules")
    @Issue("TUA-361")
    public void checkThatUserAbleToChangePhoto() {
        profilePage.editButtonClick();
        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.getUploadPhoto().sendKeys(configProperties.getImagePath(PHOTO_PROFILE_200x200));
        editProfilePopUp.clickSubmitButton();

        softAssert.assertTrue(profilePage.getTopNoticeMessage().isDisplayed());

        profilePage.editButtonClick();
        EditProfilePopUp editProfilePopUp2 = profilePage.openEditUserProfile();
        editProfilePopUp.getUploadPhoto().sendKeys(configProperties.getImagePath(PHOTO_PROFILE_7360x4912));
        editProfilePopUp.clickSubmitButton();

        softAssert.assertTrue(profilePage.getTopNoticeMessage().isDisplayed());

        softAssert.assertAll();
    }
}
