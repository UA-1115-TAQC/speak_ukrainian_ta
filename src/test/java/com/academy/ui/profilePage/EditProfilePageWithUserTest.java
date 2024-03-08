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

    @Test(description = "TUA-421", dataProvider = "invalidPhone")
    public void checkEditPhoneFieldWithInvalidData(String phone, String[] expectedErrorMsg) throws InterruptedException {

        var editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(10);
        softAssert.assertTrue(editProfilePopUp.getSubmitButton().isEnabled(),
                "SubmitButton should be enabled");

        var phoneElement = editProfilePopUp.getPhoneElement();
        phoneElement.clearInput();

        phoneElement.setValue(phone);
        for (int i = 0; i < expectedErrorMsg.length; i++) {
            softAssert.assertEquals(phoneElement.getErrorMessagesTextList().get(i), expectedErrorMsg[i]);
            softAssert.assertNotEquals(profilePage.getPhoneUser().getText(), phone);
        }

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

    @DataProvider(name = "invalidPhone")
    private Object[][] invalidPhoneDataProvider() {
        return new Object[][] {
                {"a", new String[] {"Телефон не може містити літери", "Телефон не відповідає вказаному формату"}},
                {"-a", new String[] {"Телефон не може містити літери", "Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
                {";", new String[] {"Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
                {"99999999999999999", new String[] {"Телефон не відповідає вказаному формату"}},
                {" m", new String[] {"Телефон не може містити літери", "Телефон не може містити пробіли", "Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
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
}
