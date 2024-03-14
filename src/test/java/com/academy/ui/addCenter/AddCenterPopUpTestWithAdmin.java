package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationInputElement;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddCenterPopUpTestWithAdmin extends LoginWithAdminTestRunner {

    private AddCenterPopUpComponent addCenterPopUp;
    private AddCenterPopUpStepOne stepOne;
    private SoftAssert softAssert;
    private final String VALID_CENTER_NAME = "Center Name";

    @BeforeMethod(description = "Preconditions: Get addCenterPopUp and stepOne components, make softAssert object")
    public void addClubPopUpTestPrecondition() {
        addCenterPopUp = homePage.header.openAdminMenu().openAddCentreForm();
        addCenterPopUp.waitPopUpOpen(10);
        stepOne = addCenterPopUp.getStepOneContainer();
        softAssert = new SoftAssert();
    }

    @Test(description = "Error message is shown for name field for add location while creating a center")
    @Description("Verify error message for ‘Назва’ field of ‘Додати локацію’ pop-up when creating a center")
    @Issue("TUA-262")
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

    @Test(description = "Manager can add location with valid data to list while creating a center")
    @Description("Verify that a 'Керівник' can add location to the list of locations after filling in all mandatory and all optional fields with valid data")
    @Issue("TUA-158")
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

    @Test()
    @Issue("TUA-160")
    public void checkImpossibilityAddLocationIfAllMandatoryFieldsEmpty(){
        AddLocationPopUpComponent addLocationPopUp = stepOne.clickAddLocationButton();
        softAssert.assertTrue(addLocationPopUp.isOpen());
        addLocationPopUp.clickAddLocationButton();
        int sizeBeforeAdd = stepOne.getLocationsElementsList().size();

        softAssert.assertTrue(addLocationPopUp.getAddLocationButton().isEnabled(), "Button is enabled");
        softAssert.assertFalse(addLocationPopUp.getLocatioNameInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(addLocationPopUp.getLocatioNameInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-close-circle"));

        softAssert.assertFalse(addLocationPopUp.getLocatioCityDropdownElement().getErrorMessage().getText().isEmpty());
        softAssert.assertTrue(addLocationPopUp.getLocatioCityDropdownElement().getValidationCircleIcon().getAttribute("class").contains("anticon-close-circle"));

        softAssert.assertFalse(addLocationPopUp.getLocationAddressInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(addLocationPopUp.getLocationAddressInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-close-circle"));

        softAssert.assertFalse(addLocationPopUp.getLocationCoordinatesInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(addLocationPopUp.getLocationCoordinatesInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-close-circle"));

        softAssert.assertFalse(addLocationPopUp.getLocationTelephoneInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(addLocationPopUp.getLocationTelephoneInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-close-circle"));

        addLocationPopUp.close();
        stepOne = addCenterPopUp.getStepOneContainer();
        softAssert.assertTrue(stepOne.getLocationsElementsList().size() == sizeBeforeAdd);
        softAssert.assertAll();
    }

    @Test(description = "TUA-162")
    public void checkDistrictEmptyIfCityEmpty() {
        AddLocationPopUpComponent addLocation = stepOne.clickAddLocationButton();
        softAssert.assertTrue(addLocation.getLocatioCityDropdownElement().clickDropdown().getTextDropdownOptionsList().isEmpty());
        addLocation.getLocatioCityDropdownElement().clickDropdown(); // click again to remove dropdown
        softAssert.assertTrue(addLocation.getLocationDistrictDropdownElement().clickDropdown().getTextDropdownOptionsList().isEmpty());
    }
}