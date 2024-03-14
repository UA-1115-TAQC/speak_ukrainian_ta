package com.academy.ui.profilePage;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
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
    protected WebDriverWait wait;
    protected AddCenterPopUpComponent addCenterPopUpComponent;
    protected AddCenterPopUpStepOne addClubPopUpStepOne;
    AddLocationPopUpComponent addLocationPopUpComponent;
    String testLocationName ="ТестЛокація";
    String cityDropdownValue = "Київ";
    String address = "вул. Грушевського, 5";
    @BeforeMethod

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
    @Description("Verify that the 'Географічні координати' field is filled automatically " +
            "after a 'Керівник' filled in the ‘Адреса’ field")
    @Issue("TUA-164")
    public void testCoordinatesAreFilledAutomaticallyAfterEnteringAddress(){
        addLocationPopUpComponent = addClubPopUpStepOne.clickAddLocationButton();
        fillLocationComponent(testLocationName, cityDropdownValue, address);
        verifyTheFilledDataIsPresentInFieldsLocationComponent(testLocationName, cityDropdownValue, address);
        verifyCoordinatesAreFilledAutomatically();
        softAssert.assertAll();
    }

    private void fillLocationComponent(String centerName, String cityDropdownValue, String address){
        addLocationPopUpComponent.getLocatioNameInputElement().setValue(centerName);
        addLocationPopUpComponent.getLocatioCityDropdownElement().clickDropdown();
        addLocationPopUpComponent.getLocatioCityDropdownElement().selectValue(cityDropdownValue);
        addLocationPopUpComponent.getLocationAddressInputElement().setValue(address);
    }

    private void verifyTheFilledDataIsPresentInFieldsLocationComponent(String centerName, String cityDropdownValue, String address){
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioNameInputElement().getInput().getAttribute("value"), centerName);
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioCityDropdownElement().getSelectedItem().getText(), cityDropdownValue);
        softAssert.assertEquals(addLocationPopUpComponent.getLocationAddressInputElement().getInput().getAttribute("value"), address);
    }

    private void verifyCoordinatesAreFilledAutomatically(){
        if(!addLocationPopUpComponent.getLocationAddressInputElement().getInput().getAttribute("value").isEmpty()){
            softAssert.assertFalse(addLocationPopUpComponent.getLocationCoordinatesInputElement().getInput().getAttribute("value").isEmpty(),
                    "The coordinates aren't filled automatically after entering the address and selecting the city");
        }
    }
}
