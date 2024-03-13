package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUpComponent.*;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.runners.LoginWithManagerTestRunner;
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

        //validCenterName;//generate a str
        //locationName;//generate a str
        //validAddress;
        //validDescription;
        fillStepOneWithValidData();
        stepOne.clickNextStepButton();
        stepTwo = addCenterPopUp.getStepTwoContainer();
        fillStepTwoWithValidData();
        stepTwo.clickNextStepButton();
        stepThree = addCenterPopUp.getStepThreeContainer();
        stepThree.clickNextStepButton();
        fillStepThreeWithValidData();
        stepFour = addCenterPopUp.getStepFourContainer();
    }
    @Test(description = "TUA-136")
    @Issue("TUA-136")
    public void verifyAClubIsCreatedWithoutFillingInOptionalParameters(){
        stepFour.getSelectAClubText().click();
        addClubPopUpStepOne = addClubPopUpComponent.getStepOneContainer();
        softAssert.assertTrue(addClubPopUpComponent.isOpen(),"not op");
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
