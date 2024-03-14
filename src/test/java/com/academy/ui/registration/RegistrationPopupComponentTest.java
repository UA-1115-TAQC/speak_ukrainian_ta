package com.academy.ui.registration;

import com.academy.ui.components.RegistrationPopup.RegistrationPopupComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.support.Color;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class RegistrationPopupComponentTest extends BaseTestRunner {
    private static final String ERROR_MESSAGE_MORE_THAN_25_CHARACTERS = "Прізвище не може містити більше, ніж 25 символів";
    private static final String ERROR_MESSAGE_LASTNAME_WITH_DIGITS = "Прізвище не може містити цифри";
    private static final String ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS = "Прізвище не може містити спеціальні символи";
    public static final String ERROR_MESSAGE_LASTNAME_STARTS = "Прізвище повинно починатися і закінчуватися літерою";
    private static final String EXPECTED_ERROR_MESSAGE_FOR_INVALID_PASSWORD = "Пароль не може бути коротшим, ніж 8 та довшим, ніж 20 символів";
    final String SUCCESSFUL_REGISTRATION_MESSAGE = "Ви успішно зареєструвалися! Вам на пошту відправлено лист з лінком для підтвердження реєстрації";
    private static final String INVALID_PASSWORD_LESS_THAN_EIGHT_CHARACTERS = "Qa#1234";
    private static final String INVALID_PASSWORD_MORE_THAN_TWENTY_CHARACTERS = "TeachUA#QC-572LV/devs";
    final String VALID_FIRST_NAME_USER = "Петро";
    final String VALID_LAST_NAME_USER = "Поліщук";
    final String VALID_TELEPHONE_USER = "0987654321";
    final String VALID_EMAIL_USER = "petxdr@mailna.co";
    final String VALID_PASSWORD = "Qwerty8!";
    final String VALID_CONFIRM_PASSWORD = "Qwerty8!";
    final String VALID_FIRST_NAME_MANAGER = "Микола";
    final String VALID_LAST_NAME_MANAGER = "Ткачук";
    final String VALID_TELEPHONE_MANAGER = "0987654322";
    final String VALID_EMAIL_MANAGER = "petrzdz2@mailna.co";
    public static final String ERROR_MESSAGE_EMAIl_FORMAT = "Некоректний формат email";
    public static final String ERROR_MESSAGE_EMAIl_EMPTY = "Введіть email";

    private RegistrationPopupComponent registrationPopupComponent;
    private GuestMenuComponent guestMenuComponent;
    private SoftAssert softAssert;

    @BeforeMethod(description = "Preconditions: Get guestMenu and registrationPopup components, make new softAssert object")
    public void registrationSetUp(Method method) {
        if(method.getAnnotation(Test.class).description().equals("TUA-876")){
            return;
        }
        guestMenuComponent = homePage.header.openGuestMenu();
        registrationPopupComponent = guestMenuComponent.openRegistrationForm();
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that error messages are shown for entering invalid data for the 'Прізвище' field")
    @Issue("TUA-13")
    public void registration_invalidInputOfLastNameErrorMessageShown_ok() {
        registrationPopupComponent.waitPopUpOpen(5);
        registrationPopupComponent.clickSetUserButton();
        List<String> errorMessagesTextList;

        registrationPopupComponent.getLastNameInput().clearInput().setValue("Михайло Михайлович Коцюбинський");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_MORE_THAN_25_CHARACTERS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("Тарас Григорович Шевченков");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_MORE_THAN_25_CHARACTERS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("#1Іван");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_WITH_DIGITS);
        softAssert.assertEquals(errorMessagesTextList.get(1), ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("#1@Василь");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_WITH_DIGITS);
        softAssert.assertEquals(errorMessagesTextList.get(1), ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue(" Ольга");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("K'");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("C- ");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.clickSetManagerButton();

        registrationPopupComponent.getLastNameInput().clearInput().setValue("Михайло Михайлович Коцюбинський");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_MORE_THAN_25_CHARACTERS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("Тарас Григорович Шевченков");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_MORE_THAN_25_CHARACTERS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("#1Іван");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_WITH_DIGITS);
        softAssert.assertEquals(errorMessagesTextList.get(1), ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("#1@Василь");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_WITH_DIGITS);
        softAssert.assertEquals(errorMessagesTextList.get(1), ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue(" Ольга");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("K'");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getLastNameInput().clearInput().setValue("C- ");
        errorMessagesTextList = registrationPopupComponent.getLastNameInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_LASTNAME_STARTS);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        softAssert.assertAll();
    }

    @Test(description = "TUA-110")
    @Description("Verify that error messages are shown for entering invalid data for the 'Email' field")
    public void registration_invalidInputOfEmailErrorMessageShown_ok() {
        registrationPopupComponent.waitPopUpOpen(5);

        List<String> errorMessagesTextList;
        String[] inputValues = new String[]{"ddd", "\\\\\\;R", "000@"};

        // User mode
        registrationPopupComponent.clickSetUserButton();

        // user touches input, but doesn't enter anything
        registrationPopupComponent.getEmailInput().clearInput().setKey(Keys.SPACE).setKey(Keys.BACK_SPACE);
        errorMessagesTextList = registrationPopupComponent.getEmailInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_EMAIl_EMPTY);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        for (String testValue : inputValues) {
            registrationPopupComponent.getEmailInput().clearInput().setValue(testValue);
            errorMessagesTextList = registrationPopupComponent.getEmailInput().getErrorMessagesTextList();
            softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_EMAIl_FORMAT);
            softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());
        }

        // Manager mode
        registrationPopupComponent.clickSetManagerButton();

        // user touches input, but doesn't enter anything
        registrationPopupComponent.getEmailInput().clearInput().setKey(Keys.SPACE).setKey(Keys.BACK_SPACE);;
        errorMessagesTextList = registrationPopupComponent.getEmailInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_EMAIl_EMPTY);
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        for (String testValue : inputValues) {
            registrationPopupComponent.getEmailInput().clearInput().setValue(testValue);
            errorMessagesTextList = registrationPopupComponent.getEmailInput().getErrorMessagesTextList();
            softAssert.assertEquals(errorMessagesTextList.get(0), ERROR_MESSAGE_EMAIl_FORMAT);
            softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());
        }
        softAssert.assertAll();
    }
  
    @Test(description = "TUA-243")
    @Description("Verify that user can be registered with valid data")
    public void checkNewUserCanBeRegisteredWithValidData() {
        final String firstName = "John";
        final String lastName = "Doe";
        final String phone = "0987654321";
        final String email = "new_email_example@email.com";
        final String password = "Password1;";
        final String confirmation = "Password1;";
        final String registration_success = "Ви успішно зареєструвалися! Вам на пошту відправлено лист з лінком для підтвердження реєстрації";

        registrationPopupComponent.waitPopUpOpen(5);
        registrationPopupComponent.clickSetUserButton();

        registrationPopupComponent.getFirstNameInput().clearInput().setValue(firstName);
        registrationPopupComponent.getLastNameInput().clearInput().setValue(lastName);
        registrationPopupComponent.getPhoneInput().clearInput().setValue(phone);
        registrationPopupComponent.getEmailInput().clearInput().setValue(email);
        registrationPopupComponent.getPasswordInput().clearInput().setValue(password);
        registrationPopupComponent.getPasswordConfirmationInput().clearInput().setValue(confirmation);
        registrationPopupComponent.clickRegisterButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), registration_success,
                "Successful registration message should appear");

        softAssert.assertAll();
    }

    @Test(description = "New user can register with valid data for User role")
    @Description("Verify that new user is registered with valid data for each role (Відвідувач, Керівник) [DB].")
    @Issue("TUA-7")
    public void checkNewUserCanRegisterWithValidDataForEachRoleUser() {
        registrationPopupComponent.waitPopUpOpen(5);
        registrationPopupComponent.clickSetUserButton();

        registrationPopupComponent.getFirstNameInput().clearInput().setValue(VALID_FIRST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getFirstNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_FIRST_NAME_USER);

        registrationPopupComponent.getLastNameInput().clearInput().setValue(VALID_LAST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getLastNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_LAST_NAME_USER);

        registrationPopupComponent.getPhoneInput().clearInput().setValue(VALID_TELEPHONE_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getPhoneInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE_USER);

        registrationPopupComponent.getEmailInput().clearInput().setValue(VALID_EMAIL_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getEmailInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_EMAIL_USER);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(VALID_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_PASSWORD);

        registrationPopupComponent.getPasswordConfirmationInput().clearInput().setValue(VALID_CONFIRM_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordConfirmationInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_CONFIRM_PASSWORD);

        registrationPopupComponent.clickRegisterButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), SUCCESSFUL_REGISTRATION_MESSAGE,
                "Successful registration message should appear");

        softAssert.assertAll();
    }

    @Test(description = "New user can register with valid data for Manager role")
    @Description("Verify that new user is registered with valid data for each role (Відвідувач, Керівник) [DB].")
    @Issue("TUA-7")
    public void checkNewUserCanRegisterWithValidDataForEachRoleManager() {
        registrationPopupComponent.waitPopUpOpen(5);
        registrationPopupComponent.clickSetManagerButton();

        registrationPopupComponent.getFirstNameInput().clearInput().setValue(VALID_FIRST_NAME_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getFirstNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_FIRST_NAME_MANAGER);

        registrationPopupComponent.getLastNameInput().clearInput().setValue(VALID_LAST_NAME_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getLastNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_LAST_NAME_MANAGER);

        registrationPopupComponent.getPhoneInput().clearInput().setValue(VALID_TELEPHONE_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getPhoneInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE_MANAGER);

        registrationPopupComponent.getEmailInput().clearInput().setValue(VALID_EMAIL_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getEmailInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_EMAIL_MANAGER);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(VALID_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_PASSWORD);

        registrationPopupComponent.getPasswordConfirmationInput().clearInput().setValue(VALID_CONFIRM_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordConfirmationInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_CONFIRM_PASSWORD);

        registrationPopupComponent.clickRegisterButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), SUCCESSFUL_REGISTRATION_MESSAGE,
                "Successful registration message should appear");

        softAssert.assertAll();
    }

    @Test(description = "TUA-15")
    public void verifyBanRegistrationIfOneNecessaryFiledEmpty(){
        registrationPopupComponent.clickSetUserButton();
        registrationPopupComponent.getLastNameInput().setValue("");
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getFirstNameInput().setValue("Name");
        registrationPopupComponent.getPhoneInput().setValue("0953775167");
        registrationPopupComponent.getEmailInput().setValue("blabla@gmail.com");
        registrationPopupComponent.getPasswordInput().setValue("Qwerty@123");
        registrationPopupComponent.getPasswordConfirmationInput().setValue("Qwerty@123");

        softAssert.assertEquals(registrationPopupComponent.getFirstNameInput().getInput().getAttribute("value"), "Name");
        softAssert.assertEquals(registrationPopupComponent.getPhoneInput().getInput().getAttribute("value"), "0953775167");
        softAssert.assertEquals(registrationPopupComponent.getEmailInput().getInput().getAttribute("value"), "blabla@gmail.com");

        softAssert.assertTrue(registrationPopupComponent.getPasswordInput().getInput().getAttribute("type").contains("password"));
        softAssert.assertTrue(registrationPopupComponent.getPasswordConfirmationInput().getInput().getAttribute("type").contains("password"));

        registrationPopupComponent.getLastNameInput().setValue(" ");
        registrationPopupComponent.clickRegisterButton();
        String hex = Color.fromString(registrationPopupComponent.getLastNameInput().getWholeInputElement().getCssValue("border-color")).asHex();
        softAssert.assertEquals(hex, "#ff4d4f");

        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getFirstNameInput().clearInput();
        registrationPopupComponent.getPhoneInput().clearInput();
        registrationPopupComponent.getEmailInput().clearInput();
        registrationPopupComponent.getPasswordInput().clearInput();
        registrationPopupComponent.getPasswordConfirmationInput().clearInput();

        // the same, but for manager

        registrationPopupComponent = registrationPopupComponent.clickSetManagerButton();
        registrationPopupComponent.getLastNameInput().setValue("");
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());

        registrationPopupComponent.getFirstNameInput().setValue("Name");
        registrationPopupComponent.getPhoneInput().setValue("0953775167");
        registrationPopupComponent.getEmailInput().setValue("blabla@gmail.com");
        registrationPopupComponent.getPasswordInput().setValue("Qwerty@123");
        registrationPopupComponent.getPasswordConfirmationInput().setValue("Qwerty@123");

        softAssert.assertEquals(registrationPopupComponent.getFirstNameInput().getInput().getAttribute("value"), "Name");
        softAssert.assertEquals(registrationPopupComponent.getPhoneInput().getInput().getAttribute("value"), "0953775167");
        softAssert.assertEquals(registrationPopupComponent.getEmailInput().getInput().getAttribute("value"), "blabla@gmail.com");

        softAssert.assertTrue(registrationPopupComponent.getPasswordInput().getInput().getAttribute("type").contains("password"));
        softAssert.assertTrue(registrationPopupComponent.getPasswordConfirmationInput().getInput().getAttribute("type").contains("password"));

        registrationPopupComponent.clickRegisterButton();
        hex = Color.fromString(registrationPopupComponent.getLastNameInput().getWholeInputElement().getCssValue("border-color")).asHex();
        softAssert.assertEquals(hex, "#ff4d4f");
        softAssert.assertFalse(registrationPopupComponent.getRegistrationButton().isEnabled());
        softAssert.assertAll();
    }

    @Test(description = "TUA-875")
    public void checkTheCorrespondingMessage() {
        final String VALID_FIRST_NAME_USER = "Гаррі";
        final String VALID_LAST_NAME_USER = "Поттер";
        final String VALID_TELEPHONE_USER = "0961257864";
        final String VALID_EMAIL_USER = "a476185dd@emailabox.pro";
        final String VALID_PASSWORD = "Pass!wor4";
        final String VALID_CONFIRM_PASSWORD = "Pass!wor4";
        final String SUCCESSFUL_REGISTRATION_MESSAGE = "Ви успішно зареєструвалися! " +
                "Вам на пошту відправлено лист з лінком для підтвердження реєстрації";

        registrationPopupComponent.waitPopUpOpen(5);
        registrationPopupComponent.clickSetUserButton();

        registrationPopupComponent.getFirstNameInput().clearInput().setValue(VALID_FIRST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getFirstNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_FIRST_NAME_USER);

        registrationPopupComponent.getLastNameInput().clearInput().setValue(VALID_LAST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getLastNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_LAST_NAME_USER);

        registrationPopupComponent.getPhoneInput().clearInput().setValue(VALID_TELEPHONE_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getPhoneInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE_USER);

        registrationPopupComponent.getEmailInput().clearInput().setValue(VALID_EMAIL_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getEmailInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_EMAIL_USER);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(VALID_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_PASSWORD);
        registrationPopupComponent.getPasswordConfirmationInput().clearInput().setValue(VALID_CONFIRM_PASSWORD);
        softAssert.assertEquals(registrationPopupComponent
                        .getPasswordConfirmationInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_CONFIRM_PASSWORD);

        registrationPopupComponent.clickRegisterButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), SUCCESSFUL_REGISTRATION_MESSAGE,
                "Successful registration message should appear");

        softAssert.assertAll();
    }
    @Test(description = " TUA-77 Verify that error messages are shown for entering invalid data for the 'Пароль' field")
    public void checkIfErrorMessagesAreShownForInvalidDataUser() {
        List<String> errorMessagesActual;

        registrationPopupComponent.clickSetUserButton();
        registrationPopupComponent.getFirstNameInput().clearInput().setValue(VALID_FIRST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getFirstNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_FIRST_NAME_USER);

        registrationPopupComponent.getLastNameInput().clearInput().setValue(VALID_LAST_NAME_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getLastNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_LAST_NAME_USER);

        registrationPopupComponent.getPhoneInput().clearInput().setValue(VALID_TELEPHONE_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getPhoneInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE_USER);

        registrationPopupComponent.getEmailInput().clearInput().setValue(VALID_EMAIL_USER);
        softAssert.assertEquals(registrationPopupComponent
                        .getEmailInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_EMAIL_USER);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(INVALID_PASSWORD_LESS_THAN_EIGHT_CHARACTERS);
        errorMessagesActual = registrationPopupComponent.getPasswordInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesActual.get(0), EXPECTED_ERROR_MESSAGE_FOR_INVALID_PASSWORD);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(INVALID_PASSWORD_MORE_THAN_TWENTY_CHARACTERS);
        errorMessagesActual = registrationPopupComponent.getPasswordInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesActual.get(0), EXPECTED_ERROR_MESSAGE_FOR_INVALID_PASSWORD);

        softAssert.assertAll();
    }

    @Test(description = " TUA-77 Verify that error messages are shown for entering invalid data for the 'Пароль' field")
    public void checkIfErrorMessagesAreShownForInvalidDataManager() {
        List<String> errorMessagesActual;

        registrationPopupComponent.clickSetManagerButton();
        registrationPopupComponent.getFirstNameInput().clearInput().setValue(VALID_FIRST_NAME_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getFirstNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_FIRST_NAME_MANAGER);

        registrationPopupComponent.getLastNameInput().clearInput().setValue(VALID_LAST_NAME_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getLastNameInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_LAST_NAME_MANAGER);

        registrationPopupComponent.getPhoneInput().clearInput().setValue(VALID_TELEPHONE_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getPhoneInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE_MANAGER);

        registrationPopupComponent.getEmailInput().clearInput().setValue(VALID_EMAIL_MANAGER);
        softAssert.assertEquals(registrationPopupComponent
                        .getEmailInput()
                        .getInput()
                        .getAttribute("value"),
                VALID_EMAIL_MANAGER);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(INVALID_PASSWORD_LESS_THAN_EIGHT_CHARACTERS);
        errorMessagesActual = registrationPopupComponent.getPasswordInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesActual.get(0), EXPECTED_ERROR_MESSAGE_FOR_INVALID_PASSWORD);

        registrationPopupComponent.getPasswordInput().clearInput().setValue(INVALID_PASSWORD_MORE_THAN_TWENTY_CHARACTERS);
        errorMessagesActual = registrationPopupComponent.getPasswordInput().getErrorMessagesTextList();
        softAssert.assertEquals(errorMessagesActual.get(0), EXPECTED_ERROR_MESSAGE_FOR_INVALID_PASSWORD);

        softAssert.assertAll();
    }
  
    @Test(description = "TUA-876")
    @Description("Verify that the user is not redirected to another page after canceling the registration process")
    @Issue("TUA-876")
    public void checkRedirectionAfterRegistrationCanceled(){
        HeaderComponent header = homePage.getHeader();
        header.newsButtonClick();
        String url = driver.getCurrentUrl();

        registrationPopupComponent = header.openGuestMenu().openRegistrationForm();
        registrationPopupComponent.getLastNameInput().clearInput().setValue("Qwerty");
        registrationPopupComponent.getFirstNameInput().clearInput().setValue("Qwerty");
        registrationPopupComponent.getPhoneInput().clearInput().setValue("0123456789");
        registrationPopupComponent.close();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }

}
