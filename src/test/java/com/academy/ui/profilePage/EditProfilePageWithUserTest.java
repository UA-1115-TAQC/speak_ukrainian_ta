package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.pages.ProfilePage;

import com.academy.ui.runners.LogInWithUserTestRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfilePageWithUserTest extends LogInWithUserTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;
    private EditProfilePopUp editProfilePopUp;

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

    @Test(description = "TUA-360")
    public void checkEditProfileUI() {
        editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(10);

        softAssert.assertTrue(editProfilePopUp.getPopUpHeaderTitle().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getUserTypeButton().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getManagerTypeButton().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getUserIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getManagerIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getLastNameElement().getTitle().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getFirstNameElement().getTitle().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getPhoneElement().getTitle().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getEmailElement().getInput().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getDownloadPhotoButton().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getDownloadPhotoButton().isEnabled());
        softAssert.assertTrue(editProfilePopUp.getQuestionCircleForPhoto().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getQuestionCircleForPhoto().isEnabled());
        softAssert.assertTrue(editProfilePopUp.getCheckboxChangePassword().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getCheckboxChangePassword().isEnabled());
        softAssert.assertTrue(editProfilePopUp.getChangePasswordTitle().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getSubmitButton().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getSubmitButton().isEnabled());
        softAssert.assertTrue(editProfilePopUp.getCloseButton().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getCloseButton().isEnabled());

        softAssert.assertEquals(editProfilePopUp.getPopUpHeaderTitle().getText(), "Редагувати профіль");
        softAssert.assertEquals(editProfilePopUp.getPopUpHeaderTitle()
                .getCssValue("color"), "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(editProfilePopUp.getPopUpHeaderTitle()
                .getCssValue("font-size"), "24px");

        softAssert.assertEquals(editProfilePopUp.getUserTypeButton().getText(), "Відвідувач");
        softAssert.assertEquals(editProfilePopUp.getUserTypeButton()
                .getCssValue("color"), "rgba(0, 0, 0, 1)");
        softAssert.assertEquals(editProfilePopUp.getUserTypeButton()
                .getCssValue("font-size"), "18px");

        softAssert.assertEquals(editProfilePopUp.getManagerTypeButton().getText(), "Керівник");
        softAssert.assertEquals(editProfilePopUp.getManagerTypeButton()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getManagerTypeButton()
                .getCssValue("font-size"), "18px");

        softAssert.assertEquals(editProfilePopUp.getLastNameElement().getTitleText(), "Прізвище");
        softAssert.assertEquals(editProfilePopUp.getLastNameElement().getTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getLastNameElement().getTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(editProfilePopUp.getLastNameElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getLastNameElement().getInput()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getTitleText(), "Ім'я");
        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getInput()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getTitleText(), "Телефон");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getInput()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getPhoneCountryCode().getText(), "+38");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getPhoneCountryCode()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getPhoneCountryCode()
                .getCssValue("background-color"), "rgba(0, 0, 0, 0.02)");
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getPhoneCountryCode()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getEmailElement().getTitleText(), "Email");
        softAssert.assertEquals(editProfilePopUp.getEmailElement().getTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getEmailElement().getTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(editProfilePopUp.getEmailElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.25)");
        softAssert.assertEquals(editProfilePopUp.getEmailElement().getInput()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getDownloadPhotoButton().getText(), "Завантажити фото");
        softAssert.assertEquals(editProfilePopUp.getDownloadPhotoButton()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getDownloadPhotoButton()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getChangePasswordTitle().getText(), "Змінити пароль");
        softAssert.assertEquals(editProfilePopUp.getChangePasswordTitle()
                .getCssValue("color"), "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(editProfilePopUp.getChangePasswordTitle()
                .getCssValue("font-size"), "14px");

        editProfilePopUp.clickCheckBox();

        editProfilePopUp.getCurrentPasswordElement().setValue("Qwerty~");
        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement().getValidationCircleIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement().getPasswordVisibilityIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordElement().getValidationCircleIcon().isEnabled());
        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordElement().getInput()
                .getCssValue("font-size"), "14px");

        editProfilePopUp.getNewPasswordInput().setValue("Qwerty`123");
        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement().getValidationCircleIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement().getPasswordVisibilityIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getNewPasswordElement().getValidationCircleIcon().isEnabled());
        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getInput()
                .getCssValue("font-size"), "14px");

        editProfilePopUp.getConfirmPasswordInput().setValue("Qwerty`123");
        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement().getValidationCircleIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement().getPasswordVisibilityIcon().isDisplayed());
        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordElement().getValidationCircleIcon().isEnabled());
        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordInput().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordInput().getInput()
                .getCssValue("font-size"), "14px");

        softAssert.assertEquals(editProfilePopUp.getSubmitButton().getText(), "Зберегти зміни");
        softAssert.assertEquals(editProfilePopUp.getSubmitButton()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(editProfilePopUp.getSubmitButton()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(editProfilePopUp.getSubmitButton()
                .getCssValue("font-size"), "16px");

        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordElement().getInput()
                .getSize(), new Dimension(372, 22));
        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordElement().getValidationCircleIcon()
                .getSize(), new Dimension(14, 14));
        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordElement().getPasswordVisibilityIcon()
                .getSize(), new Dimension(14, 14));

        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getInput()
                .getSize(), new Dimension(372, 22));
        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getValidationCircleIcon()
                .getSize(), new Dimension(14, 14));
        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getPasswordVisibilityIcon()
                .getSize(), new Dimension(14, 14));

        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordElement().getInput()
                .getSize(), new Dimension(372, 22));
        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordElement().getValidationCircleIcon()
                .getSize(), new Dimension(14, 14));
        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordElement().getPasswordVisibilityIcon()
                .getSize(), new Dimension(14, 14));

        softAssert.assertEquals(editProfilePopUp.getCheckboxChangePassword()
                .getSize(), new Dimension(17, 17));
        softAssert.assertEquals(editProfilePopUp.getSubmitButton().getSize()
                , new Dimension(270, 40));
        softAssert.assertEquals(editProfilePopUp.getQuestionCircleForPhoto()
                .getSize(), new Dimension(14, 14));

        softAssert.assertEquals(editProfilePopUp.getEmailElement().getInput()
                .getSize(), new Dimension(436, 32));
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getPhoneCountryCode()
                .getSize(), new Dimension(48, 32));
        softAssert.assertEquals(editProfilePopUp.getPhoneElement().getInput()
                .getSize(), new Dimension(360, 22));
        softAssert.assertEquals(editProfilePopUp.getLastNameElement()
                .getInput().getSize(), new Dimension(408, 22));
        softAssert.assertEquals(editProfilePopUp.getFirstNameElement().getInput()
                .getSize(), new Dimension(408, 22));

        softAssert.assertEquals(editProfilePopUp.getUserIcon()
                .getSize(), new Dimension(32, 32));
        softAssert.assertEquals(editProfilePopUp.getManagerIcon()
                .getSize(), new Dimension(32, 32));

        softAssert.assertEquals(editProfilePopUp.getUserTypeButton()
                .getSize(), new Dimension(364, 48));
        softAssert.assertEquals(editProfilePopUp.getManagerTypeButton()
                .getSize(), new Dimension(364, 48));

        softAssert.assertEquals(editProfilePopUp.getCloseButton().getSize(),
                new Dimension(22, 22));

        Actions actions = new Actions(driver);
        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordInput().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getSubmitButton()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getCloseButton()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        editProfilePopUp.getLastNameElement().clearInput();
        softAssert.assertTrue(editProfilePopUp.getLastNameElement().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getFirstNameElement().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getPhoneElement().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getDownloadPhotoButton()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getCheckboxChangePassword()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordInput().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(editProfilePopUp.getNewPasswordInput().getInput()
                .equals(driver.switchTo().activeElement()));
        actions.sendKeys(Keys.TAB).perform();

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
