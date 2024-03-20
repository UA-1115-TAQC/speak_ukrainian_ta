package com.academy.ui.addCenter;

import com.academy.ui.components.AddCenterPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

public class TestAddLocationToCenterPopUp extends LoginWithManagerTestRunner {

    private static final String CENTER = "Study Buddies";

    private static final String LOCATION = "Київ-Лівобережний";

    private static final String CITY = "Київ";

    private static final String DISTRICT = "Дарницький";

    private static final String ADDRESS = "проспект Петра Григоренка 15";

    private static final String GEOGRAPHICAL_COORDINATES = "50.424361, 30.619556";

    private static final String TELEPHONE_NUMBER = "0976756754";

    private static final String DESCRIPTION = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. It has survived not only five centuries.";
    private static final String CLUB = "Study with us";

    private AddCenterPopUpComponent addCenterPopUp;
    private AddCenterPopUpStepOne stepOne;
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addCenterPopUp = homePage.header.openAdminMenu().openAddCentreForm();
        addCenterPopUp.waitPopUpOpen(10);
        stepOne = addCenterPopUp.getStepOneContainer();
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that a 'Керівник' can add a location of a center")
    @Issue("TUA-247")
    public void addLocationOfACenterTest() {

        stepOne.setCenterName(CENTER);
        AddLocationPopUpComponent addLocationPopUp = stepOne.clickAddLocationButton();

        addLocationPopUp.getLocationNameInputElement().setValue(LOCATION);
        softAssert.assertTrue(addLocationPopUp.isOpen());

        addLocationPopUp.getLocationCityDropdownElement().clickDropdown().selectValue(CITY);
        softAssert.assertEquals(addLocationPopUp.getLocationCityDropdownElement().getSelectedItem().getText(), CITY);

        addLocationPopUp.getLocationDistrictDropdownElement().clickDropdown().selectValue(DISTRICT);
        softAssert.assertEquals(addLocationPopUp.getLocationDistrictDropdownElement().getSelectedItem().getText(), DISTRICT);

        addLocationPopUp.getLocationAddressInputElement().setValue(ADDRESS);
        softAssert.assertEquals(addLocationPopUp.getLocationAddressInputElement().getInput().getAttribute("value"), ADDRESS);

        addLocationPopUp.getLocationCoordinatesInputElement().setValue(GEOGRAPHICAL_COORDINATES);
        softAssert.assertEquals(addLocationPopUp.getLocationCoordinatesInputElement().getInput().getAttribute("value"), GEOGRAPHICAL_COORDINATES);

        addLocationPopUp.getLocationTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertEquals(addLocationPopUp.getLocationTelephoneInputElement().getInput().getAttribute("value"), TELEPHONE_NUMBER);

        addLocationPopUp.clickAddLocationButton();
        softAssert.assertTrue(stepOne.getLocationsNameList().contains(LOCATION));

        stepOne.clickLocationCheckboxByName(LOCATION);
        stepOne.clickNextStepButton();

        AddCenterPopUpStepTwo stepTwo = addCenterPopUp.getStepTwoContainer();

        stepTwo.getTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getInput().getAttribute("value"), TELEPHONE_NUMBER);
        stepTwo.clickNextStepButton();

        AddCenterPopUpStepThree stepThree = addCenterPopUp.getStepThreeContainer();
        stepThree.setCenterDescriptionTextarea(DESCRIPTION);
        softAssert.assertEquals(stepThree.getCenterDescriptionTextarea().getText(), DESCRIPTION);
        stepThree.clickNextStepButton();

        AddCenterPopUpStepFour stepFour = addCenterPopUp.getStepFourContainer();
        stepFour.clickOnClubCheckBoxByName(CLUB);
        stepFour.clickFinishButton();

        softAssert.assertAll();

    }

    @Test()
    @Issue("TUA-292")
    public void checkFunctionalityOfAddCenterButton(){
        stepOne = addCenterPopUp.getStepOneContainer();
        stepOne.setCenterName(CENTER);
        AddLocationPopUpComponent addLocation = stepOne.clickAddLocationButton();
        addLocation.getLocationNameInputElement().setValue(LOCATION);
        addLocation.getLocationCityDropdownElement().clickDropdown().selectValue(CITY);

        addLocation.getLocationDistrictDropdownElement().clickDropdown().selectValue(DISTRICT);
        addLocation.getLocationMetroDropdownElement().clickDropdown().selectValue("METRO");//doesn't work because there is no data in this dropdown on the website
        addLocation.getLocationAddressInputElement().setValue(ADDRESS);
        addLocation.getLocationCoordinatesInputElement().setValue(GEOGRAPHICAL_COORDINATES);
        softAssert.assertFalse(Objects.equals(addLocation.getAddLocationButton().getAttribute("disabled"), "disabled"), "Button is enabled");

        addLocation.getLocationTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertTrue(addLocation.getAddLocationButton().isEnabled(), "Button isn't enabled");

        addLocation.getLocationTelephoneInputElement().clearInput();
        softAssert.assertFalse(Objects.equals(addLocation.getAddLocationButton().getAttribute("disabled"), "disabled"), "Button is enabled");

        addLocation.getLocationTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertTrue(addLocation.getAddLocationButton().isEnabled(), "Button isn't enabled");

        addLocation.clickAddLocationButton();
        softAssert.assertTrue(stepOne.centerTitle.isDisplayed(), "User is not redirected to Основна інформація");

        softAssert.assertAll();
    }
}