package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUpComponent.*;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.randomvaluesgenerators.RandomAlphanumericStringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddCenterPopUpTestWithManager extends LoginWithManagerTestRunner {
    private AddCenterPopUpComponent addCenterPopUp;
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpStepOne addClubPopUpStepOne;
    private AddCenterPopUpStepOne stepOne;
    private AddCenterPopUpStepTwo stepTwo;
    private AddCenterPopUpStepThree stepThree;
    private AddCenterPopUpStepFour stepFour;
    private SoftAssert softAssert;
    private String validCenterName;
   private String validLocationName;
   private String validCity ="Київ";
   private String validCoordinates = "50.4504, 30.524";
   private String validPhone = "0".repeat(10);
   private String validAddress;
    private String validDescription;
    private AddLocationPopUpComponent addLocationPopUp;
    @BeforeMethod
    public void moveToClubsSection(){
        addCenterPopUp = homePage.header.openAdminMenu().openAddCentreForm();
        addCenterPopUp.waitPopUpOpen(10);
        stepOne = addCenterPopUp.getStepOneContainer();
        softAssert = new SoftAssert();
        validCenterName = RandomAlphanumericStringGenerator.generateRandomString(8, 12, 2);
        validLocationName= RandomAlphanumericStringGenerator.generateRandomString(8, 12, 2);
        validAddress= RandomAlphanumericStringGenerator.generateRandomString(15, 20, 3);
        validDescription = RandomAlphanumericStringGenerator.generateRandomString(40, 60, 3);
        fillStepOneWithValidData();
        stepOne.clickNextStepButton();
        stepTwo = addCenterPopUp.getStepTwoContainer();
        fillStepTwoWithValidData();
        stepTwo.clickNextStepButton();
        stepThree = addCenterPopUp.getStepThreeContainer();
        fillStepThreeWithValidData();
        stepThree.clickNextStepButton();
        stepFour = addCenterPopUp.getStepFourContainer();
    }
    @Test(description = "TUA-136")
    @Description("Verify that a club is created from 'Додати центр' pop-up without filling optional parameters")
    @Issue("TUA-136")
    public void verifyAClubIsCreatedWithoutFillingInOptionalParameters(){
        stepFour.getSelectAClubText().click();
        softAssert.assertFalse(stepFour.getSelectAClubText().isDisplayed(),
                "It is impossible to add a new club when creating a center. " +
                        "The 'add a club' button isn't implemented in the stepFour of the addCenterPopUp");
        // add a test to check adding a club functionality if this option is implemented //todo
        softAssert.assertAll();
    }
    private void fillStepOneWithValidData(){
        stepOne.setCenterName(validCenterName);
         addLocationPopUp = stepOne.clickAddLocationButton();
         addLocationPopUp.getLocatioNameInputElement().getInput().sendKeys(validLocationName);
         addLocationPopUp.getLocatioCityDropdownElement().clickDropdown().selectValue(validCity);
         addLocationPopUp.getLocationAddressInputElement().setValue(validAddress);
         addLocationPopUp.getLocationCoordinatesInputElement().setValue(validCoordinates);
        addLocationPopUp.getLocationTelephoneInputElement().setValue(validPhone);
        addLocationPopUp.clickAddLocationButton();
        stepOne = addCenterPopUp.getStepOneContainer();
        stepOne.clickLocationCheckboxByName(validLocationName);
    }
    private void fillStepTwoWithValidData(){
        stepTwo.getTelephoneInputElement().setValue(validPhone);
    }

    private void fillStepThreeWithValidData(){
        stepThree.setCenterDescriptionTextarea(validDescription);
    }
}
