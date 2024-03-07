package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;

import com.academy.ui.runners.LogInWithUserTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfilePageWithUserTest extends LogInWithUserTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;

    @BeforeMethod
    public void editProfilePageWithUserTest_setUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();
    }

    @Test(description = "TUA-358")
    public void checkEditProfileLinkIsPresentAndDirectToEditProfilePage() {
        softAssert.assertTrue(profilePage.getEditProfileButton().isDisplayed(),
                "EditProfile should be link present");
        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(5);
        softAssert.assertTrue(editProfilePopUp.isOpen(),
                "EditProfile popup should be open");
        softAssert.assertAll();
    }

    @Test(description = "TUA-328", dataProvider = "invalidFirstName")
    public void checkEditNameFieldWithInvalidData(String firstName, String expectedErrorMsg) {
        final var emptyFieldErrorMsg = "Введіть Ваше ім'я";

        var editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(10);
        softAssert.assertTrue(editProfilePopUp.getSubmitButton().isEnabled(),
                "SubmitButton should be enabled");

        var firstNameElement = editProfilePopUp.getFirstNameElement();
        firstNameElement.clearInput();
        softAssert.assertEquals(firstNameElement.getErrorMessagesTextList().get(0), emptyFieldErrorMsg);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "Submit button should not be enabled");

        firstNameElement.setValue(firstName);
        softAssert.assertEquals(firstNameElement.getErrorMessagesTextList().get(0), expectedErrorMsg);
        softAssert.assertFalse(editProfilePopUp.getSubmitButton().isEnabled(),
                "Submit button should not be enabled");

        softAssert.assertAll();
    }

    @DataProvider(name = "invalidFirstName")
    private Object[][] invalidFirstNameDataProvider() {
        return new Object[][] {
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Ім'я не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Ім'я не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Ім'я не може містити спеціальні символи"},
                {"1234", "Ім'я не може містити цифри"},
                {"-Name", "Ім'я повинно починатися та закінчуватися літерою"},
                {" Name", "Ім'я не може містити спеціальні символи"},
                {"'Name", "Ім'я повинно починатися та закінчуватися літерою"},
                {"Name-", "Ім'я повинно починатися та закінчуватися літерою"},
                {"Name ", "Ім'я не може містити спеціальні символи"},
                {"Name'", "Ім'я повинно починатися та закінчуватися літерою"}
        };
    }

    @Test(description = "TUA-866")
    public void checkVisibilityOfUploadPhotoLink() {
        var editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(10);

        softAssert.assertTrue(editProfilePopUp.getPhotoLink().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getPhotoLink().getText().equals("Фото"));
        softAssert.assertEquals(editProfilePopUp.getTooltipText(),
                "Приймас зображення формату JPG / PNG із мінімальною роздільною здатністю 200x200 пікселів"
                        + " та максимальним розміром файлу 5МВ");
        softAssert.assertTrue(editProfilePopUp.getUploadPhotoLink().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getUploadPhotoLink().getText().equals("Завантажити фото"));
        softAssert.assertAll();
    }

    @Test(description = "TUA-343", dataProvider = "invalidLastName")
    public void checkEditLastNameFieldWithInvalidData(String lastName, String expectedErrorMsg) {
        final String emptyFieldErrorMsg = "Будь ласка введіть Ваше прізвище";

        EditProfilePopUp editProfile = new ProfilePage(driver).openEditUserProfile();

        editProfile.getLastNameElement().clearInput().setValue(lastName);
        softAssert.assertEquals(editProfile.getLastNameElement().getErrorMessagesTextList().get(0), expectedErrorMsg);
        softAssert.assertFalse(editProfile.getSubmitButton().isEnabled(),
                "Submit button should not be enabled");

        editProfile.getLastNameElement().clearInput();
        softAssert.assertEquals(editProfile.getLastNameElement().getErrorMessagesTextList().get(0), emptyFieldErrorMsg);
        softAssert.assertFalse(editProfile.getSubmitButton().isEnabled(),
                "Submit button should not be enabled");
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidLastName")
    private Object[][] invalidLastNameDataProvider() {
        return new Object[][] {
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Прізвище не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Прізвище не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Прізвище не може містити спеціальні символи"},
                {"1234", "Прізвище не може містити цифри"},
                {"-Lastname", "Прізвище не може містити символи"},
                {"< Lastname>", "Прізвище повинно починатися та закінчуватися літерою"},
                {"'Lastname", "Прізвище повинно починатися та закінчуватися літерою"},
                {"Lastname-", "Прізвище повинно починатися та закінчуватися літерою"},
                {"Lastname'", "Прізвище повинно починатися та закінчуватися літерою"}
        };
    }
}
