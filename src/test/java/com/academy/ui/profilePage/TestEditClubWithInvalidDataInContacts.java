package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestEditClubWithInvalidDataInContacts extends LoginWithManagerTestRunner {

    private static final String TELEPHONE_LESS_THEN_TEN_NUMBERS = "050987654";
    private static final String TELEPHONE_MORE_THEN_TEN_NUMBERS = "05094567892";
    private static final String TELEPHONE_WITH_SPECIAL_CHARACTERS_AND_LETTERS = " fu!@£$% ";
    private static final String INVALID_EMAIL = "prince@.@com";
    private static final String EXPECTED_ERROR_MASSAGE_IN_TELEPHONE_FIELD = "Телефон не відповідає вказаному формату";
    private static final String EXPECTED_ERROR_MASSAGE_IN_TELEPHONE_FIELD_WITH_SYMBOLS = "Телефон не може містити спеціальні символи, літери та пробіли " +
            "Телефон не відповідає вказаному формату";

    private static final String EXPECTED_ERROR_MASSAGE_IN_EMAIL_FIELD = "Некоректний формат email";
    private SoftAssert softAssert;
    private ProfilePage profilePage;

    @BeforeMethod
    public void SetUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();

    }

    @Test(description = "Verify that user cannot save invalid data in the ‘Контакти’ fields (for a club that is not in the center) on the ‘Контакти’ tab of the ‘Редагувати гурток’ pop-up window")
    @Issue("TUA-76")
    public void checkThatUserCannotSavedInvalidDataForAClub() {
        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        AddClubPopUpComponent editClub = clubCard.clickMoreButton().clickEditClub();
        editClub.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo stepTwo = editClub.getStepTwoContainer();

        stepTwo.getTelephoneInputElement().clearInput().setValue(TELEPHONE_LESS_THEN_TEN_NUMBERS);
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getErrorMessagesTextList().getFirst(), EXPECTED_ERROR_MASSAGE_IN_TELEPHONE_FIELD);
        softAssert.assertTrue(stepTwo.getNextStepButton().isEnabled());

        stepTwo.getTelephoneInputElement().clearInput().setValue(TELEPHONE_MORE_THEN_TEN_NUMBERS);
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getErrorMessagesTextList().getFirst(), EXPECTED_ERROR_MASSAGE_IN_TELEPHONE_FIELD);
        softAssert.assertTrue(stepTwo.getNextStepButton().isEnabled());

        stepTwo.getTelephoneInputElement().clearInput().setValue(TELEPHONE_WITH_SPECIAL_CHARACTERS_AND_LETTERS);
        String actualMessage = stepTwo.getErrorMessageTelephoneField().getText().replaceAll("\\s+", " ").trim();
        softAssert.assertEquals(actualMessage, EXPECTED_ERROR_MASSAGE_IN_TELEPHONE_FIELD_WITH_SYMBOLS);
        softAssert.assertTrue(stepTwo.getNextStepButton().isEnabled());

        stepTwo.getEmailInputElement().clearInput().setValue(INVALID_EMAIL);
        softAssert.assertEquals(stepTwo.getEmailInputElement().getErrorMessagesTextList().getFirst(), EXPECTED_ERROR_MASSAGE_IN_EMAIL_FIELD);
        softAssert.assertTrue(stepTwo.getNextStepButton().isEnabled());

        softAssert.assertAll();

    }

}
