package com.academy.ui.addClub;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAddClubWithoutOptionalParameters extends LoginWithManagerTestRunner {

    private static final String CLUB_NAME = "Український гурток";
    private static final String CATEGORY_NAME = "Танці, хореографія";
    private static final String  MIN_AGE = "6";
    private static final String  MAX_AGE = "14";
    private static final String  TELEPHONE_NUMBER = "0978585364";

    private static final String  DESCRIPTION_CLUB = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.";
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(5);
        stepOne = addClubPopUpComponent.getStepOneContainer();
        softAssert = new SoftAssert();
    }

    @Test(description = "Verify that a club without a center is created without filling optional parameters")
    @Issue("TUA-122")
    public void checkThatClubCreatedWithoutFillingOptionalParameters(){
        softAssert.assertTrue(stepOne.getClubNameInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepOne.getCheckedCategoriesList().isEmpty());
        softAssert.assertTrue(stepOne.getMinAgeInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepOne.getMaxAgeInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepOne.getNextStepButton().isEnabled());

        stepOne.getClubNameInputElement().setValue(CLUB_NAME);
        AddClubPopUpStepOne addClubCategory = stepOne.selectCategory(CATEGORY_NAME);
        stepOne.getMinAgeInput().sendKeys(MIN_AGE);
        stepOne.getMaxAgeInput().sendKeys(MAX_AGE);
        softAssert.assertTrue(stepOne.getNextStepButton().isEnabled());

        stepOne.clickNextStepButton();

        stepTwo = addClubPopUpComponent.getStepTwoContainer();

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().isEmpty());


        stepTwo.getTelephoneInputElement().setValue(TELEPHONE_NUMBER);
        softAssert.assertTrue(stepTwo.getNextStepButton().isEnabled());
        stepTwo.clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        softAssert.assertTrue(stepThree.getClubDescriptionTextarea().getAttribute("value").isEmpty());
        stepThree.clearDescriptionTextarea().setDescriptionValue(DESCRIPTION_CLUB);
        softAssert.assertTrue(stepThree.getNextStepButton().isEnabled());

        stepThree.clickNextStepButton();

        ProfilePage profilePage = new ProfilePage(driver);
        String club = profilePage.getClubCardByName(CLUB_NAME).getClubName();
        softAssert.assertTrue(club.equals(CLUB_NAME));

        softAssert.assertAll();

    }
}
