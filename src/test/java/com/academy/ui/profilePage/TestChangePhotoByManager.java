package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestChangePhotoByManager extends LoginWithManagerTestRunner {
    private static final String PHOTO_PROFILE_200x200 = "butterfly png 200x200.png";
    private ProfilePage profilePage;


    @BeforeMethod
    public void editProfilePageWithUserTest_setUp() {
        profilePage = homePage.header.openUserMenu().clickProfile();
    }

    @Test(description = "Verify that the user as 'Керівник' is able to choose the photo according to the validity rules")
    @Issue("TUA-844")
    public void checkThatManagerAbleToChangePhoto() {
        profilePage.editButtonClick();
        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.getUploadPhoto().sendKeys(configProperties.getImagePath(PHOTO_PROFILE_200x200));
        editProfilePopUp.clickSubmitButton();

        Assert.assertTrue(profilePage.getTopNoticeMessage().isDisplayed());

    }
}

