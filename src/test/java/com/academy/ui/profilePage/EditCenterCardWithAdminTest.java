package com.academy.ui.profilePage;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepThree;
import com.academy.ui.components.CenterCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditCenterCardWithAdminTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    private ProfilePage profilePage;
    private final String VALID_CENTER_NAME = "Курси програмування IT-stat";
    private final String VALID_TELEPHONE = "0977777777";
    private final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openProfilePage();
    }

    @Test(description = "Admin can edit description in the center pop-up")
    @Description("Verify that user can edit ‘Опис’ textfield with valid data and save changes on the ‘Опис’ tab of the ‘Редагувати Центр’ pop-up window")
    @Issue("TUA-394")
    public void checkAdminCanEditDescriptionInCenter() {

        profilePage.clickMyClubsAndCentersOnDropdown();
        profilePage.sleep(1000);
        profilePage.clickMyCentersOnDropdown();
        CenterCardWithEditComponent centerCard = profilePage.getCenterCardByName(VALID_CENTER_NAME);
        centerCard.getMoreButton().click();

        AddCenterPopUpComponent editCenterPopUp = centerCard.clickEditCenter();
        editCenterPopUp.waitPopUpOpen(5);
        editCenterPopUp.getStepOneContainer().clickNextStepButton();
        editCenterPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .clearInput()
                .setValue(VALID_TELEPHONE);
        editCenterPopUp.getStepTwoContainer().clickNextStepButton();

        AddCenterPopUpStepThree stepThree = editCenterPopUp.getStepThreeContainer();
        String prevDescription = stepThree.getCenterDescriptionTextarea().getAttribute("value");
        stepThree.clearDescriptionTextarea().setCenterDescriptionTextarea(TEXT_50_SYMBOLS);
        softAssert.assertEquals(stepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                TEXT_50_SYMBOLS,
                "Description textarea should contains text " + TEXT_50_SYMBOLS);
        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyCentersOnDropdown();
        CenterCardWithEditComponent newCenterCard = profilePage.getCenterCardByName(VALID_CENTER_NAME);
        newCenterCard.getMoreButton().click();


        AddCenterPopUpComponent newEditCenterPopUp = newCenterCard.clickEditCenter();
        newEditCenterPopUp.waitPopUpOpen(5);
        newEditCenterPopUp.getStepOneContainer().clickNextStepButton();
        newEditCenterPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .clearInput()
                .setValue(VALID_TELEPHONE);
        newEditCenterPopUp.getStepTwoContainer().clickNextStepButton();

        AddCenterPopUpStepThree newStepThree = newEditCenterPopUp.getStepThreeContainer();
        softAssert.assertEquals(newStepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                TEXT_50_SYMBOLS,
                "Description textarea should contains text " + TEXT_50_SYMBOLS);
        newStepThree.clearDescriptionTextarea().setCenterDescriptionTextarea(prevDescription);
        softAssert.assertEquals(newStepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                prevDescription,
                "Description textarea should contains text " + prevDescription);
        newStepThree.clickCompleteButton();

        softAssert.assertAll();

    }

}
