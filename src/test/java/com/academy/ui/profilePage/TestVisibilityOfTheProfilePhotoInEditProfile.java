package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestVisibilityOfTheProfilePhotoInEditProfile extends LoginWithManagerTestRunner {

    private static final String PHOTO_PROFILE = "image.png";
    private ProfilePage profilePage;

    @BeforeMethod
    public void editProfilePageWithUserTest_setUp() {
        profilePage = homePage.header.openUserMenu().clickProfile();
    }

    @Test(description = "Verify the visibility of the profile photo in the 'Редагувати профіль' modal window when a user ( as 'Керівник') is logged in via Google")
    @Issue("TUA-916")
    public void checkVisibilityOfTheProfilePhotoOnEditProfileWindow(){
        profilePage.editButtonClick();
        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.getUploadPhoto().sendKeys(configProperties.getImagePath(PHOTO_PROFILE));
        Assert.assertEquals(editProfilePopUp.getUploadPictureTitle().getText(), PHOTO_PROFILE);

    }
}
