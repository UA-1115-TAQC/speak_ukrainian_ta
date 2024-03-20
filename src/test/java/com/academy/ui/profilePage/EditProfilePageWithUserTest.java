package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.editProfileElement.EditProfileInputElement;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

public class EditProfilePageWithUserTest extends LogInWithUserTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;
    private EditProfilePopUp editProfilePopUp;

    @BeforeMethod(description = "Preconditions: Get profilePage, make new softAssert object")
    public void editProfilePageWithUserTest_setUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();
    }

    @Test(description = "Edit profile button is present and direct to edit Profile page")
    @Description("Verify that the ‘Редагувати профіль’ link is present and direct to the ‘Редагувати профіль’ page")
    @Issue("TUA-358")
    public void checkEditProfileLinkIsPresentAndDirectToEditProfilePage() {
        softAssert.assertTrue(profilePage.getEditProfileButton().isDisplayed(),
                "EditProfile should be link present");
        EditProfilePopUp editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(5);
        softAssert.assertTrue(editProfilePopUp.isOpen(),
                "EditProfile popup should be open");
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidFirstName")
    @Parameters({"IncorrectFirstName", "ExpectedErrorMessage"})
    @Description("Verify name field on Edit Profile PopUp doesn't accept incorrect data and error messages are shown")
    @Issue("TUA-328")
    public void checkEditNameFieldWithInvalidData(String incorrectFirstNameData, String expectedErrorMsg) {
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

        firstNameElement.setValue(incorrectFirstNameData);
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
        return new Object[][]{
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
        return new Object[][]{
                {"a", new String[]{"Телефон не може містити літери", "Телефон не відповідає вказаному формату"}},
                {"-a", new String[]{"Телефон не може містити літери", "Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
                {";", new String[]{"Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
                {"99999999999999999", new String[]{"Телефон не відповідає вказаному формату"}},
                {" m", new String[]{"Телефон не може містити літери", "Телефон не може містити пробіли", "Телефон не відповідає вказаному формату", "Телефон не може містити спеціальні символи"}},
        };
    }

    @Test
    @Description("""
            Verify User as 'Відвідувач' can see 'Завантажити фото' text link
            under the 'Фото' link and tooltip message appears""")
    @Issue("TUA-866")
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

    @Test
    @Description("Check 'Редагувати профіль' page UI. The user as 'Відвідувач'")
    @Issue("TUA-360")
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

    @Test(description = "The user as 'Відвідувач'", dataProvider = "invalidLastName")
    @Parameters({"IncorrectLastName", "ExpectedErrorMessage"})
    @Description("Verify that error messages are shown and 'Зберегти зміни' button" +
            " becomes disabled while entering invalid data into the 'Прізвище' field")
    @Issue("TUA-343")
    public void checkEditLastNameFieldWithInvalidData(String lastName, String expectedErrorMsg) {
        final String emptyFieldErrorMsg = "Будь ласка введіть Ваше прізвище";

        EditProfilePopUp editProfile = profilePage.openEditUserProfile();

        editProfile.getLastNameElement().setValue(lastName);
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
        return new Object[][]{
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

    @Issue("TUA-905")
    @Test()
    public void checkIsPasswordHide() {
        editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(10);

        editProfilePopUp.getCheckboxChangePassword().click();
        editProfilePopUp.getCurrentPasswordInput().setValue("1qaz@Xsw");
        softAssert.assertTrue(editProfilePopUp.getCurrentPasswordInput().getValidationCircleIcon().isDisplayed());
        editProfilePopUp.getCurrentPasswordInput().clickPasswordVisibilityIcon();
        softAssert.assertEquals(editProfilePopUp.getCurrentPasswordInput().getWebElement().getAttribute("type"), "text");

        editProfilePopUp.getNewPasswordInput().setValue("1qaz@Xsw2");
        softAssert.assertTrue(editProfilePopUp.getNewPasswordInput().getValidationCircleIcon().isDisplayed());
        editProfilePopUp.getNewPasswordInput().clickPasswordVisibilityIcon();
        softAssert.assertEquals(editProfilePopUp.getNewPasswordInput().getWebElement().getAttribute("type"), "text");

        editProfilePopUp.getConfirmPasswordInput().setValue("1qaz@Xsw2");
        softAssert.assertTrue(editProfilePopUp.getConfirmPasswordInput().getValidationCircleIcon().isDisplayed());
        editProfilePopUp.getConfirmPasswordInput().clickPasswordVisibilityIcon();
        softAssert.assertEquals(editProfilePopUp.getConfirmPasswordInput().getWebElement().getAttribute("type"), "text");
        softAssert.assertAll();
    }

    @Test(description = "TUA-113")
    public void editUserWithValidData() {
        final String firstName = "John";
        final String lastName = "Doe";
        final String phone = "0987654321";
        final String password = "Password1;";

        EditProfilePopUp editProfile = new ProfilePage(driver).openEditUserProfile();
        editUserWithData(editProfile, firstName, lastName, phone, password, false);

        editProfile = new ProfilePage(driver).openEditUserProfile();
        editUserWithData(editProfile, firstName, lastName, phone, password, true);

        softAssert.assertAll();
    }

    private void editUserWithData(EditProfilePopUp editProfile, String firstName, String lastName, String phone, String password, boolean withPassword) {
        final String updateSuccessMessage = withPassword ? "Профіль змінено успішно" : "Ви успішно залогувалися!";

        editProfile.waitPopUpOpen(5);
        editProfile.clickUserButton();
        editProfile.getPhoneElement().clearInput().setValue(phone);
        editProfile.getFirstNameElement().clearInput().setValue(firstName);
        editProfile.getLastNameElement().clearInput().setValue(lastName);

        if (withPassword) {
            editProfile.clickCheckBox();
            editProfile.getCurrentPasswordElement().clearInput().setValue(configProperties.getUserPassword());
            editProfile.getNewPasswordInput().clearInput().setValue(password);
            editProfile.getConfirmPasswordInput().clearInput().setValue(password);
        }

        editProfile.clickSubmitButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), updateSuccessMessage,
                "Successful registration message should appear");
    }

    @Test(description = """
            Check user can change old password with valid new one""",
            dataProvider = "userValidPassword")
    @Description("""
            Check user as 'Відвідувач' can change the password
            by inputting valid data in ‘New password field’""")
    @Parameters({"oldUserPassword", "newUserPassword",
            "validationCircleIconExpectedColor", "expectedSuccessMessage"})
    @Issue("TUA-171")
    public void checkUserCanChangeOldPassword(String userPassword, String password,
                                              String expectedColor, String expectedSuccessMessage) {
        var editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(5);
        editProfilePopUp = editProfilePopUp.clickCheckBox();

        var currentPasswordInput = editProfilePopUp.getCurrentPasswordInput();
        currentPasswordInput.clearInput();
        currentPasswordInput.setValue(userPassword);
        softAssert.assertEquals(currentPasswordInput.getValidationCircleIcon()
                .getCssValue("color"), expectedColor);

        var newPasswordInput = editProfilePopUp.getNewPasswordInput();
        newPasswordInput.clearInput();
        newPasswordInput.setValue(password);
        softAssert.assertEquals(newPasswordInput.getValidationCircleIcon()
                .getCssValue("color"), expectedColor);

        var repeatPassword = editProfilePopUp.getConfirmPasswordInput();
        repeatPassword.clearInput();
        repeatPassword.setValue(password);
        softAssert.assertEquals(repeatPassword.getValidationCircleIcon()
                .getCssValue("color"), expectedColor);

        profilePage = editProfilePopUp.clickSubmitButton();
        softAssert.assertTrue(profilePage.getSuccessEditMessage().contains(expectedSuccessMessage),
                "Message '%s' should be visible".formatted(expectedSuccessMessage));

        tearDownUser(password, userPassword);
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] userValidPassword() {
        return new Object[][]{
                {configProperties.getUserPassword(), "Dictionary13?",
                        "rgba(82, 196, 26, 1)", "Профіль змінено успішно"},
                {configProperties.getUserPassword(), "Qwerty1?",
                        "rgba(82, 196, 26, 1)", "Профіль змінено успішно"},
                {configProperties.getUserPassword(), "QwertyQwertyQwerty1?",
                        "rgba(82, 196, 26, 1)", "Профіль змінено успішно"}
        };
    }

    private void tearDownUser(String oldPassword, String password) {
        var editProfilePopUp = profilePage.openEditUserProfile();
        editProfilePopUp.waitPopUpOpen(5);

        var currentPasswordInput = editProfilePopUp.getCurrentPasswordInput();
        currentPasswordInput.clearInput();
        currentPasswordInput.setValue(oldPassword);

        var newPassword = editProfilePopUp.getNewPasswordInput();
        newPassword.clearInput();
        newPassword.setValue(password);

        var repeatPassword = editProfilePopUp.getConfirmPasswordInput();
        repeatPassword.clearInput();
        repeatPassword.setValue(password);
        profilePage = editProfilePopUp.clickSubmitButton();
    }

    //    input in email field is disabled
    @Ignore
    @Test(dataProvider = "invalidEmail", dataProviderClass = EditProfilePageWithUserDataProvider.class)
    @Description("Verify that error message is shown and 'Зберегти зміни' button becomes disabled " +
            "while entering invalid data for the ' Email' field. The user as 'Відвідувач'")
    @Issue("TUA-355")
    public void checkErrorMsgInvalidEmail(String input) {
        softAssert = new SoftAssert();
        EditProfilePopUp editProfile = profilePage.openEditUserProfile();
        editProfile.getEmailElement().setValue(input);

        softAssert.assertEquals(
                editProfile.getEmailElement().getErrorMessagesTextList().get(0),
                "Некоректний формат email"
        );
        softAssert.assertFalse(editProfile.getSubmitButton().isEnabled());
        softAssert.assertAll();
    }


    @Test
    @Issue("TUA-359")
    @Description("Verify that error messages are shown while leaving empty any field in the 'Змінити пароль' pop-up")
    public void checkErrorWhenPasswordEmpty() {
        EditProfilePopUp editProfile = profilePage.openEditUserProfile();
        editProfile.waitPopUpOpen(100);
        editProfile.clickCheckBox();
        editProfile.getCurrentPasswordInput().setValue("Blabla");
        editProfile.getNewPasswordInput().setValue("BlablA@123");
        editProfile.getSubmitButton().click();

        editProfile.waitUntilElementIsVisible(editProfile.getConfirmPasswordElement().getErrorMessages().getFirst());
        String borderColor = editProfile.getConfirmPasswordInputNode().getCssValue("border-color");
        softAssert.assertTrue(Objects.equals(borderColor, "rgb(255, 77, 79)"), "Border isn't red");
        softAssert.assertTrue(editProfile.getConfirmPasswordElement().getErrorMessages()
                                        .getFirst().getText().equals("Будь ласка, підтвердіть пароль"), "No error message");


        editProfile.getConfirmPasswordInput().setValue("BlablA@123");
        editProfile.getNewPasswordInput().clearInput();
        editProfile.waitUntilElementIsVisible(editProfile.getNewPasswordElement().getErrorMessages().getFirst());
        borderColor = editProfile.getNewPasswordInputNode().getCssValue("border-color");
        softAssert.assertTrue(Objects.equals(borderColor, "rgb(255, 77, 79)"), "Border isn't red");
        softAssert.assertTrue(editProfile.getNewPasswordElement().getErrorMessages()
                                        .getFirst().getText().equals("Будь ласка, введіть новий пароль"), "No error message");

        editProfile.getNewPasswordInput().setValue("BlablA@123");
        editProfile.getCurrentPasswordInput().clearInput();
        editProfile.waitUntilElementIsVisible(editProfile.getCurrentPasswordElement().getErrorMessages().getFirst());
        borderColor = editProfile.getCurrentPasswordInputNode().getCssValue("border-color");
        softAssert.assertTrue(Objects.equals(borderColor, "rgb(255, 77, 79)"), "Border isn't red");
        softAssert.assertTrue(editProfile.getCurrentPasswordElement().getErrorMessages()
                                        .getFirst().getText().equals("Введіть старий пароль"), "No error message");

        softAssert.assertAll();
    }
}
