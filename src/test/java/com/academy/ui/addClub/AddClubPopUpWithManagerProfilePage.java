package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddClubPopUpWithManagerProfilePage extends LoginWithManagerTestRunner {
    private static final String VALID_CLUB_NAME = "Add club name";
    private static final String CATEGORY = "Спортивні секції";
    private static final String VALID_MIN_AGE = "5";
    private static final String VALID_MAX_AGE = "8";
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-116_1")
    @Description("Verify that 'Додати гурток' pop-up can be opened by clicking the button 'Add club' in Home Page")
    @Issue("TUA-116")
    public void checkAddClubPopUpHomePage() {
        AddClubPopUpComponent addClubPopUpComponent= homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(10);
        softAssert.assertTrue(addClubPopUpComponent.isOpen(),
                "PopUp not opened clicking the button 'Add club' in HomePage");

        addClubPopUpComponent.getCloseButton().click();
        softAssert.assertAll();
    }

    @Test(description = "TUA-116_2")
    @Description("Verify that 'Додати гурток' pop-up can be opened by clicking the button 'Add club' in Profile Page")
    @Issue("TUA-116")
    public void checkAddClubPopUpProfilePage() {
        ProfilePage profilePage = homePage.header.openUserMenu().clickProfile();
        AddClubPopUpComponent addClubPopUp = profilePage.openAddClubPopUp();
        softAssert.assertTrue(addClubPopUp.getWebElement().isDisplayed(),
                "PopUp not opened clicking the button 'Add club' in ProfilePage");

        softAssert.assertAll();
    }

    @Test(description = "The error messages appeared for the name field when creating a club with incorrect data")
    @Description("Verify error message for ‘Назва’ field of ‘Додати локацію’ pop-up when creating a club")
    @Issue("TUA-249")
    public void CheckErrorMessagesForNameLocationField() {
        ProfilePage profilePage = homePage.header.openUserMenu().clickProfile();
        AddClubPopUpComponent addClubPopUp = profilePage.openAddClubPopUp();
        stepOne = addClubPopUp.getStepOneContainer();
        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(VALID_MIN_AGE)
                .setMaxAgeInput(VALID_MAX_AGE)
                .clickNextStepButton();

        stepTwo = addClubPopUp.getStepTwoContainer();

        AddLocationPopUpComponent addLocation = stepTwo.clickAddLocationButton();
        addLocation.getLocationNameInputElement().setValue("Ы, э, Ѩ, Ѭ,");
        softAssert.assertTrue(addLocation.getLocationNameInputElement()
                .getErrorMessagesTextList()
                .contains("Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи"));

        addLocation.getLocationNameInputElement().clearInput().setValue("dfdg");
        softAssert.assertTrue(addLocation.getLocationNameInputElement()
                .getErrorMessagesTextList()
                .contains("Назва локації закоротка"));

        addLocation.getLocationNameInputElement().clearInput().setValue("vbyui1258/vbyui1258/vbyui1258/vbyui1258" +
                "/vbyui1258/vbyui1258/vbyui1258/vbyui1258/vbyui1258/nyrfvdoiu2587");
        softAssert.assertTrue(addLocation.getLocationNameInputElement()
                .getErrorMessagesTextList()
                .contains("Назва локації задовга"));

        softAssert.assertAll();
    }
}
