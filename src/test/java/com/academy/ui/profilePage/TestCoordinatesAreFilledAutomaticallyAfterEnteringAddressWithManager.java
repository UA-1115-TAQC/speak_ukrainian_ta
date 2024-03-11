package com.academy.ui.profilePage;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
public class TestCoordinatesAreFilledAutomaticallyAfterEnteringAddressWithManager extends LoginWithManagerTestRunner {
    protected SoftAssert softAssert;
    protected ProfilePage profilePage;
    WebDriverWait wait;
    AddCenterPopUpComponent addCenterPopUpComponent;
    AddCenterPopUpStepOne addClubPopUpStepOne;
    AddLocationPopUpComponent addLocationPopUpComponent;
    String testLocationName ="ТестЛокація";
    String cityDropdownValue = "Київ";
    String address = "вул. Грушевського, 5";
    @BeforeMethod
    @Step("Setting up preconditions for the test")
    public void precondition(){
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        profilePage = homePage.header.openUserMenu().clickProfile();
        profilePage.addButtonClick().get(1).click();
        addCenterPopUpComponent = new AddCenterPopUpComponent(driver);
        addClubPopUpStepOne = addCenterPopUpComponent.getStepOneContainer();
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepOne.getCenterNameInput()));
    }
    @Test(description = "TUA-164")
    @Issue("TUA-164")
    public void testCoordinatesAreFilledAutomaticallyAfterEnteringAddress(){
        addLocationPopUpComponent = addClubPopUpStepOne.clickAddLocationButton();
        fillLocationComponent(testLocationName, cityDropdownValue, address);
        verifyTheFilledDataIsPresentInFieldsLocationComponent(testLocationName, cityDropdownValue, address);
        verifyCoordinatesAreFilledAutomatically();
        softAssert.assertAll();
    }
    @Step("Fill the location component with a center name: {0}, city: {1}, address: {2}")
    private void fillLocationComponent(String centerName, String cityDropdownValue, String address){
        addLocationPopUpComponent.getLocatioNameInputElement().setValue(centerName);
        addLocationPopUpComponent.getLocatioCityDropdownElement().clickDropdown();
        addLocationPopUpComponent.getLocatioCityDropdownElement().selectValue(cityDropdownValue);
        addLocationPopUpComponent.getLocationAddressInputElement().setValue(address);
    }
    @Step("Verify that filled data is present in fields of location component")
    private void verifyTheFilledDataIsPresentInFieldsLocationComponent(String centerName, String cityDropdownValue, String address){
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioNameInputElement().getInput().getAttribute("value"), centerName);
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioCityDropdownElement().getSelectedItem().getText(), cityDropdownValue);
        softAssert.assertEquals(addLocationPopUpComponent.getLocationAddressInputElement().getInput().getAttribute("value"), address);
    }
    @Step("Verify that coordinates are filled automatically")
    private void verifyCoordinatesAreFilledAutomatically(){
        if(!addLocationPopUpComponent.getLocationAddressInputElement().getInput().getAttribute("value").isEmpty()){
            softAssert.assertFalse(addLocationPopUpComponent.getLocationCoordinatesInputElement().getInput().getAttribute("value").isEmpty(),
                    "The coordinates aren't filled automatically after entering the address and selecting the city");
        }
    }
}
