package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EditProfileWithManagerTest extends LoginWithManagerTestRunner {
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private static final String HIDDEN_PASSWORD = "eye-invisible";
    private static final String VISIBLE_PASSWORD = "eye";
    private SoftAssert softAssert;
    private ProfilePage profilePage;

    @BeforeMethod
    public void SetUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();

    }

//    @Test(description = "TUA-867")
//    public void testUploadPhotoLinksAndTooltipIsDisplayed() {
//        homePage.header.openUserMenu().clickProfile();
//        EditProfilePopUp editProfile = new ProfilePage(driver).openEditUserProfile();
//        softAssert.assertTrue(editProfile.getPhotoLink().isDisplayed());
//
//        String actualResult = editProfile.getTooltipText();
//        softAssert.assertEquals(actualResult, "Приймас зображення формату JPG / PNG із мінімальною роздільною здатністю 200x200 пікселів та максимальним розміром файлу 5МВ", "Error messages are different");
//        softAssert.assertTrue(editProfile.getPhotoToolTipForm().isDisplayed());
//
//        softAssert.assertTrue(editProfile.getUploadPhotoLink().isDisplayed());
//        softAssert.assertAll();
//    }
//
//    @Test(description = "TUA-840")
//    public void checkErrorMessagesShowAndButtonDisablesWithInvalidData() {
//        final String TELEPHONE_LESS_THAN_13 = "06895";
//        final String TELEPHONE_MORE_THAN_13 = "6593859632586";
//        final String TELEPHONE_WITH_LETTER = "jngeoлщшогнеп";
//        final String TELEPHONE_WITH_SYMBOLS = "!@#$%^&*(_+.:";
//        final String TELEPHONE_ERROR = "Телефон не відповідає формату +38(___) ___ __ __";
//
//        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
//        editProfilePopUp.waitPopUpOpen(5);
//
//        EditProfileInputElement telephoneElement = editProfilePopUp.getPhoneElement();
//        telephoneElement.setValue(TELEPHONE_LESS_THAN_13);
//        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
//                "For Telephone less 13 symbols error message list should contain message " + TELEPHONE_ERROR);
//        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
//                "For Telephone less 13 symbols Submit button should be disabled");
//
//        telephoneElement.clearInput();
//        telephoneElement.setValue(TELEPHONE_MORE_THAN_13);
//        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
//                "For Telephone more 13 symbols error message list should contain message " + TELEPHONE_ERROR);
//        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
//                "For Telephone more 13 symbols Submit button should be disabled");
//
//        telephoneElement.clearInput();
//        telephoneElement.setValue(TELEPHONE_WITH_LETTER);
//        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
//                "For Telephone with letters error message list should contain message " + TELEPHONE_ERROR);
//        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
//                "For Telephone with letters Submit button should be disabled");
//
//        telephoneElement.clearInput();
//        telephoneElement.setValue(TELEPHONE_WITH_SYMBOLS);
//        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
//                "For Telephone with symbols error message list should contain message " + TELEPHONE_ERROR);
//        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
//                "For Telephone with symbols Submit button should be disabled");
//
//        telephoneElement.clearInput();
//        softAssert.assertTrue(telephoneElement.getErrorMessagesTextList().contains(TELEPHONE_ERROR),
//                "For empty Telephone error message list should contain message " + TELEPHONE_ERROR);
//        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
//                "For empty Telephone Submit button should be disabled");
//
//        softAssert.assertAll();
//    }
//
//    @Test(description = "TUA-904")
//    public void checkCloseButtonOnEditProfileDoesntStoreEnteredData() {
//        final String NAME = "Петро";
//        final String TELEPHONE = "0956874567";
//
//        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
//        editProfilePopUp.waitPopUpOpen(5);
//
//        editProfilePopUp.getFirstNameElement().clearInput().setValue(NAME);
//        softAssert.assertEquals(editProfilePopUp
//                        .getFirstNameElement()
//                        .getInput()
//                        .getAttribute("value"),
//                NAME);
//        editProfilePopUp.getPhoneElement().clearInput().setValue(TELEPHONE);
//
//        softAssert.assertEquals(editProfilePopUp
//                        .getPhoneElement()
//                        .getInput()
//                        .getAttribute("value"),
//                TELEPHONE);
//
//        editProfilePopUp.getCloseButton().click();
//
//        softAssert.assertNotEquals(profilePage.getUserName().getText(), NAME);
//        softAssert.assertNotEquals(profilePage.getPhoneUser().getText(), TELEPHONE);
//
//        softAssert.assertAll();
//    }
//
//    @Test(description = "TUA-906")
//    public void checkPasswordIsHiding() {
//        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
//        editProfilePopUp.waitPopUpOpen(5);
//        editProfilePopUp.clickCheckBox();
//
//        editProfilePopUp.getCurrentPasswordElement().setValue("Test$1er");
//        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement()
//                .getValidationCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON),
//                "Field accepts the data");
//        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement()
//                .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD));
//
//        editProfilePopUp.getCurrentPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement()
//                .getPasswordVisibilityIcon().getAttribute("aria-label").contains(VISIBLE_PASSWORD),
//                "The password is visible and can be hidden again after clicking");
//
//        editProfilePopUp.getCurrentPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement()
//                .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD),
//                "The password is invisible");
//
//        editProfilePopUp.getNewPasswordElement().setValue("Test$1erQwerty");
//        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement()
//                        .getValidationCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON),
//                "Field accepts the data");
//        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement()
//                .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD));
//
//        editProfilePopUp.getNewPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement()
//                        .getPasswordVisibilityIcon().getAttribute("aria-label").contains(VISIBLE_PASSWORD),
//                "The password is visible and can be hidden again after clicking");
//
//        editProfilePopUp.getNewPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement()
//                        .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD),
//                "The password is invisible");
//
//        editProfilePopUp.getConfirmPasswordElement().setValue("Test$1erQwerty");
//        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement()
//                        .getValidationCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON),
//                "Field accepts the data");
//        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement()
//                .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD));
//
//        editProfilePopUp.getConfirmPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement()
//                        .getPasswordVisibilityIcon().getAttribute("aria-label").contains(VISIBLE_PASSWORD),
//                "The password is visible and can be hidden again after clicking");
//
//        editProfilePopUp.getConfirmPasswordElement().clickPasswordVisibilityIcon();
//        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement()
//                        .getPasswordVisibilityIcon().getAttribute("aria-label").contains(HIDDEN_PASSWORD),
//                "The password is invisible");
//
//        softAssert.assertAll();
//    }

    @Test(description = "TUA-836", dataProvider = "invalidLastNameInput",
            dataProviderClass = EditProfileWithManagerDataProvider.class)
    public void checkErrorInvalidLastName(String lastName, String errorMsg){
        softAssert = new SoftAssert();

        EditProfilePopUp editProfile = profilePage.openEditUserProfile();
        editProfile.waitPopUpOpen(10);
        editProfile.clickManagerButton();

        editProfile.getLastNameElement().clearInput().setValue(lastName);
        List<String> errors = editProfile.getLastNameElement().getErrorMessagesTextList();
        softAssert.assertEquals(errors.get(0), errorMsg);
        softAssert.assertFalse(editProfile.getSubmitButton().isEnabled());

        softAssert.assertAll();
    }

}
