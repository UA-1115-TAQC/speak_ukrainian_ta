package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationInputElement;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
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
        addCenterPopUp.waitPopUpOpen(10);
        stepOne = addCenterPopUp.getStepOneContainer();
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-262")
    public void checkErrorMessageForNameFieldInAddLocationForCenter() {
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

    @Test(description = "TUA-158")
    public void checkAddingLocationWithValidToList() {
        final String VALID_LOCATION_NAME = "ТестЛокація4";
        final String VALID_CITY_NAME = "Київ";
        final String VALID_CITY_DISTRICT = "Деснянський";
        final String VALID_ADDRESS = "вул. Садова, 1а";
        final String VALID_COORDINATES = "50.4485253, 30.4735083";
        final String VALID_TELEPHONE = "0977777777";

        AddLocationPopUpComponent addLocationPopUp = stepOne.clickAddLocationButton();

        addLocationPopUp.getLocatioNameInputElement().setValue(VALID_LOCATION_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocatioNameInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_LOCATION_NAME);

        addLocationPopUp.getLocatioCityDropdownElement().clickDropdown().selectValue(VALID_CITY_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocatioCityDropdownElement()
                        .getSelectedItem()
                        .getText(),
                VALID_CITY_NAME);

        addLocationPopUp.getLocationDistrictDropdownElement().clickDropdown().selectValue(VALID_CITY_DISTRICT);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationDistrictDropdownElement()
                        .getSelectedItem()
                        .getText(),
                VALID_CITY_DISTRICT);

        addLocationPopUp.getLocationAddressInputElement().setValue(VALID_ADDRESS);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationAddressInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_ADDRESS);

        addLocationPopUp.getLocationCoordinatesInputElement().setValue(VALID_COORDINATES);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationCoordinatesInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_COORDINATES);

        addLocationPopUp.getLocationTelephoneInputElement().setValue(VALID_TELEPHONE);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationTelephoneInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE);

        addLocationPopUp.clickAddLocationButton();

        softAssert.assertEquals(stepOne.getLocationsCheckboxesList().size(), 1);
        softAssert.assertTrue(stepOne.getLocationsNameList().contains(VALID_LOCATION_NAME),
                "List of location names should have " + VALID_LOCATION_NAME);

        softAssert.assertAll();
    }
}
