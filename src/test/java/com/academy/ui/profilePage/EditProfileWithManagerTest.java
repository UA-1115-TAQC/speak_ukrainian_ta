package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.editProfileElement.EditProfileInputElement;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithUserTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileWithManagerTest extends LoginWithUserTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;

    @BeforeMethod
    public void SetUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();

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

    @Test(description = "TUA-840")
    public void checkErrorMessagesShowAndButtonDisablesWithInvalidData(){
        final String TELEPHONE_LESS_THAN_13 = "06895";
        final String TELEPHONE_MORE_THAN_13 = "6593859632586";
        final String TELEPHONE_WITH_LETTER = "jngeoлщшогнеп";
        final String TELEPHONE_WITH_SYMBOLS = "!@#$%^&*(_+.:";
        final String TELEPHONE_ERROR= "Телефон не відповідає формату +38(___) ___ __ __";

        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(5);

        EditProfileInputElement telephoneElement = editProfilePopUp.getPhoneElement();
        telephoneElement.setValue(TELEPHONE_LESS_THAN_13);
        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
                "For Telephone less 13 symbols error message list should contain message " + TELEPHONE_ERROR);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "For Telephone less 13 symbols Submit button should be disabled");

        telephoneElement.clearInput();
        telephoneElement.setValue(TELEPHONE_MORE_THAN_13);
        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
                "For Telephone more 13 symbols error message list should contain message " + TELEPHONE_ERROR);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "For Telephone more 13 symbols Submit button should be disabled");

        telephoneElement.clearInput();
        telephoneElement.setValue(TELEPHONE_WITH_LETTER);
        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
                "For Telephone with letters error message list should contain message " + TELEPHONE_ERROR);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "For Telephone with letters Submit button should be disabled");

        telephoneElement.clearInput();
        telephoneElement.setValue(TELEPHONE_WITH_SYMBOLS);
        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
                "For Telephone with symbols error message list should contain message " + TELEPHONE_ERROR);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "For Telephone with symbols Submit button should be disabled");

        telephoneElement.clearInput();
        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
                "For empty Telephone error message list should contain message " + TELEPHONE_ERROR);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "For empty Telephone Submit button should be disabled");

        softAssert.assertAll();
    }

}
