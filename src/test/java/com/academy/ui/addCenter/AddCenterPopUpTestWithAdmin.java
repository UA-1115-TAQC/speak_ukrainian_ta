package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationInputElement;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddCenterPopUpTestWithAdmin extends LoginWithAdminTestRunner {

    private AddCenterPopUpComponent addCenterPopUp;
    private AddCenterPopUpStepOne stepOne;
    private SoftAssert softAssert;
    private final String VALID_CENTER_NAME = "Center Name";

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addCenterPopUp = homePage.header.openAdminMenu().openAddCentreForm();
        stepOne = addCenterPopUp.getStepOneContainer();
        addCenterPopUp.waitPopUpOpen(10);
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-262")
    public void checkErrorMessageForNameFieldInAddLocationForCenter(){
        final String INVALID_CENTER_NAME = "Ы, э, Ѩ, Ѭ,";
        final String ERROR_SYMBOLS_MESSAGE = "Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи";
        final String CENTER_NAME_LESS_5_SYMBOLS = "dfdg";
        final String ERROR_TO_SHORT_MESSAGE = "Назва локації закоротка";
        final String CENTER_NAME_MORE_100_SYMBOLS = "vbyui1258/".repeat(10) + "!";
        final String ERROR_TO_LONG_MESSAGE = "Назва локації задовга";

        stepOne.setCenterName(VALID_CENTER_NAME);
        AddLocationInputElement nameInput = stepOne.clickAddLocationButton().getLocatioNameInputElement();
        nameInput.setValue(INVALID_CENTER_NAME);
        softAssert.assertTrue(nameInput
                        .getErrorMessagesTextList()
                        .contains(ERROR_SYMBOLS_MESSAGE),
                "Error message " + ERROR_SYMBOLS_MESSAGE + " should be displayed");

        nameInput.clearInput();
        nameInput.setValue(CENTER_NAME_LESS_5_SYMBOLS);
        softAssert.assertTrue(nameInput
                        .getErrorMessagesTextList()
                        .contains(ERROR_TO_SHORT_MESSAGE),
                "Error message " + ERROR_TO_SHORT_MESSAGE + " should be displayed");

        nameInput.clearInput();
        nameInput.setValue(CENTER_NAME_MORE_100_SYMBOLS);
        softAssert.assertTrue(nameInput
                        .getErrorMessagesTextList()
                        .contains(ERROR_TO_LONG_MESSAGE),
                "Error message " + ERROR_TO_LONG_MESSAGE + " should be displayed");

        softAssert.assertAll();
    }
}
