package com.academy.ui.registration;

import com.academy.ui.components.RegistrationPopup.RegistrationPopupComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    public void registrationSetUp() {
        guestMenuComponent = homePage.header.openGuestMenu();
        registrationPopupComponent = guestMenuComponent.openRegistrationForm();
        softAssert = new SoftAssert();
    }

    @Test
    public void registration_invalidInputOfLastNameErrorMessageShown_ok() {
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
}
