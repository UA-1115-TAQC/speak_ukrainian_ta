package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithUserTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileWithManagerTest extends LoginWithUserTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void SetUp() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-867")
    public void testUploadPhotoLinksAndTooltipIsDisplayed() {
        homePage.header.openUserMenu().clickProfile();
        EditProfilePopUp editProfile = new ProfilePage(driver).openEditUserProfile();
        softAssert.assertTrue(editProfile.getPhotoLink().isDisplayed());

        String actualResult = editProfile.getTooltipText();
        softAssert.assertEquals(actualResult, "Приймас зображення формату JPG / PNG із мінімальною роздільною здатністю 200x200 пікселів та максимальним розміром файлу 5МВ", "Error messages are different");
        softAssert.assertTrue(editProfile.getPhotoToolTipForm().isDisplayed());

        softAssert.assertTrue(editProfile.getUploadPhotoLink().isDisplayed());
        softAssert.assertAll();
    }

}
