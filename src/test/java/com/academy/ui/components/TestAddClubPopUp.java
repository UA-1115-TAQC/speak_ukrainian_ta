package com.academy.ui.components;

import com.academy.ui.components.AddClubPopUpComponent.*;

import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAddClubPopUp extends LoginWithAdminTestRunner {

    private AddClubPopUpStepThree preconditionsWithValidDataOnFirstAndSecondSteps() {
        final String CLUB_NAME = "Add club name";
        final String CATEGORY= "Спортивні секції";
        final String MIN_AGE= "5";
        final String MAX_AGE= "8";
        final String TELEPHONE_NUMBER= "0987656453";
        AddClubPopUpComponent addClubPopUp = homePage.header.addClubButtonClick();
        addClubPopUp.waitPopUpOpen(10);
        AddClubPopUpStepOne stepOne = addClubPopUp.getStepOneContainer();
        stepOne.getClubNameInputElement().setValue(CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(MIN_AGE)
                .setMaxAgeInput(MAX_AGE)
                .clickNextStepButton();
        addClubPopUp.getStepTwoContainer().getTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        addClubPopUp.getStepTwoContainer().clickNextStepButton();

        return addClubPopUp.getStepThreeContainer();
    }


    @Test(description = "TUA-177")
    public void checkDescriptionFieldAllows_1500_MoreAndLessSymbols(){

        final String TEXT_1500_SYMBOLS = "Abcd ".repeat(300);
        final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
        final String TEXT_1501_SYMBOLS = TEXT_1500_SYMBOLS + "!";
        final String TEXT_1550_SYMBOLS =  "Abcd ".repeat(310);
        final String ERROR_MESSAGE = "Опис гуртка може містити від 40 до 1500 символів.";

        AddClubPopUpStepThree stepThree = preconditionsWithValidDataOnFirstAndSecondSteps();
        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1500_SYMBOLS);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 1500 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 50 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1501_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE), "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1550_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE), "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        softAssert.assertAll();
    }
}
