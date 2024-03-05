package com.academy.ui.registration;

import com.academy.ui.components.RegistrationPopup.RegistrationPopupComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.List;

public class RegistrationPopupComponentTest extends BaseTestRunner {
    private static final String ERROR_MESSAGE_MORE_THAN_25_CHARACTERS = "Прізвище не може містити більше, ніж 25 символів";
    private static final String ERROR_MESSAGE_LASTNAME_WITH_DIGITS = "Прізвище не може містити цифри";
    private static final String ERROR_MESSAGE_LASTNAME_WITH_SPECIAL_SYMBOLS = "Прізвище не може містити спеціальні символи";
    public static final String ERROR_MESSAGE_LASTNAME_STARTS = "Прізвище повинно починатися і закінчуватися літерою";

    private RegistrationPopupComponent registrationPopupComponent;
    private GuestMenuComponent guestMenuComponent;
    private SoftAssert softAssert;

    @BeforeMethod
    public void registrationSetUp(Method method) {
        if(method.getAnnotation(Test.class).description().equals("TUA-876")){
            return;
        }
        guestMenuComponent = homePage.header.openGuestMenu();
        registrationPopupComponent = guestMenuComponent.openRegistrationForm();
        softAssert = new SoftAssert();
    }

    @Test
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

    @Test(description = "TUA-7 USER")
    public void checkNewUserCanRegisterWithValidDataForEachRoleUserAndManager() {

        final String VALID_FIRST_NAME_USER = "Петро";
        final String VALID_LAST_NAME_USER = "Поліщук";
        final String VALID_TELEPHONE_USER = "0987654321";
        final String VALID_EMAIL_USER = "petxdr@mailna.co";
        final String VALID_PASSWORD = "Qwerty8!";
        final String VALID_CONFIRM_PASSWORD = "Qwerty8!";
        final String SUCCESSFUL_REGISTRATION_MESSAGE = "Ви успішно зареєструвалися! Вам на пошту відправлено лист з лінком для підтвердження реєстрації";

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

    @Test(description = "TUA-7 MANAGER")
    public void checkNewUserCanRegisterWithValidDataForEachRoleManager() {

        final String VALID_FIRST_NAME_MANAGER = "Микола";
        final String VALID_LAST_NAME_MANAGER = "Ткачук";
        final String VALID_TELEPHONE_MANAGER = "0987654322";
        final String VALID_EMAIL_MANAGER = "petrzdz2@mailna.co";
        final String VALID_PASSWORD = "Qwerty8!";
        final String VALID_CONFIRM_PASSWORD = "Qwerty8!";
        final String SUCCESSFUL_REGISTRATION_MESSAGE = "Ви успішно зареєструвалися! Вам на пошту відправлено лист з лінком для підтвердження реєстрації";

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

    @Test(description = "TUA-876")
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
        softAssert = new SoftAssert();
        softAssert.assertEquals(currentUrl, url);
    }

}
