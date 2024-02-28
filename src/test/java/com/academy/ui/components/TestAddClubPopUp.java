package com.academy.ui.components;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.elements.BaseDropdownElement;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAddClubPopUp extends LoginWithAdminTestRunner {
    private static final String DEFAULT_INPUT = "qwerty";
    private static final String CLUB_NAME = "Add club name";
    private static final String CATEGORY = "Спортивні секції";
    private static final String MIN_AGE = "5";
    private static final String MAX_AGE = "8";
    private static final String TELEPHONE_NUMBER = "0987656453";
    private static final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        stepOne = addClubPopUpComponent.getStepOneContainer();
        addClubPopUpComponent.waitPopUpOpen(10);
        softAssert = new SoftAssert();
    }

    private void fillStepOneWithValidDataPreconditions() {
        stepOne.getClubNameInputElement().setValue(CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(MIN_AGE)
                .setMaxAgeInput(MAX_AGE)
                .clickNextStepButton();
    }

    private void fillStepTwoWithValidDataPreconditions() {
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        stepTwo.clickNextStepButton();
    }

    @Test(description = "TUA-177")
    public void checkDescriptionFieldAllows_1500_MoreAndLessSymbols() {

        final String TEXT_1500_SYMBOLS = "Abcd ".repeat(300);
        final String TEXT_1501_SYMBOLS = TEXT_1500_SYMBOLS + "!";
        final String TEXT_1550_SYMBOLS = "Abcd ".repeat(310);
        final String ERROR_MESSAGE = "Опис гуртка може містити від 40 до 1500 символів.";

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();

        stepThree = addClubPopUpComponent.getStepThreeContainer();

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1500_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 1500 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 50 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1501_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE), "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1550_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE), "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        softAssert.assertAll();
    }

    @Test(description = "TUA-237")
    public void validate_inputs_in_addLocationForm_ok() {
        stepOne.getClubNameInputElement().setValue(CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(MIN_AGE)
                .setMaxAgeInput(MAX_AGE)
                .clickNextStepButton();

        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddLocationPopUpComponent addLocationPopUpComponent = stepTwo.clickAddLocationButton();

        addLocationPopUpComponent.getLocatioNameInputElement().setValue("Україна");
        softAssert.assertTrue(addLocationPopUpComponent.getLocatioNameInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        BaseDropdownElement cityDropdownElement = addLocationPopUpComponent.getLocatioCityDropdownElement().clickDropdown();
        cityDropdownElement.selectValue("Київ");
        softAssert.assertTrue(addLocationPopUpComponent.getLocatioCityDropdownElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        BaseDropdownElement districtDropdownElement = addLocationPopUpComponent.getLocationDistrictDropdownElement().clickDropdown();
        districtDropdownElement.selectValue("Шевченківський");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationDistrictDropdownElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationAddressInputElement().setValue("вул. Шевченка, 233/1");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationAddressInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationCoordinatesInputElement().setValue("49.829104498711104, 24.005058710351314");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationCoordinatesInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertTrue(addLocationPopUpComponent.getLocationTelephoneInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getAddLocationButton().click();

        stepTwo.getTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getFacebookInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getFacebookInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getWhatsappInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getEmailInputElement().setValue("qwerty@gmail.com");
        softAssert.assertTrue(stepTwo.getEmailInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getSkypeInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getSkypeInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getSiteInputElement().setValue("https://qwerty.com.ua");
        softAssert.assertTrue(stepTwo.getSiteInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 50 symbols");

        addClubPopUpComponent.getCloseButton().click();
        softAssert.assertAll();
    }
}
