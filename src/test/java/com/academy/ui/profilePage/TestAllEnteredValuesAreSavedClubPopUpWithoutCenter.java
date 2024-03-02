package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithUserTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

//dont forget to change to login with manager test runner
public class TestAllEnteredValuesAreSavedClubPopUpWithoutCenter extends LoginWithUserTestRunner {
    SoftAssert softAssert;
    ProfilePage profilePage;
    AddClubPopUpComponent addClubPopUpComponent;
    AddClubPopUpStepOne addClubPopUpStepOne;
    AddClubPopUpStepTwo addClubPopUpStepTwo;
    AddClubPopUpStepThree addClubPopUpStepThree;
    AddClubPopUpSider addClubPopUpSider;
    WebDriverWait wait;
    @BeforeMethod
    public void precondition(){
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        profilePage = homePage.header.openUserMenu().clickProfile();
        profilePage.addButtonClick().get(0).click();
        //fix the locator when the element is implemented
        addClubPopUpComponent = new AddClubPopUpComponent(driver);
        addClubPopUpSider = new AddClubPopUpSider(driver, addClubPopUpComponent.getSiderElement());
        addClubPopUpStepOne = addClubPopUpComponent.getStepOneContainer();
    }
    @Test(description = "TUA-127")
    public void verifyAllEnteredValuesAreSavedClubPopUpWithoutCenter(){
        verifyAllParametersAreEmptyByDefaultStepOne();
        fillStepOneComponentWithValidData();
        addClubPopUpStepOne.clickNextStepButton();
        addClubPopUpStepTwo = addClubPopUpComponent.getStepTwoContainer();
        verifyAllParametersAreEmptyByDefaultStepTwo();
        softAssert.assertAll();
    }
    private void verifyAllParametersAreEmptyByDefaultStepOne(){
        softAssert.assertTrue(addClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value").isEmpty(),
                "The name of the club isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getCheckedCategoriesList().isEmpty(),
                "Some of the checkboxes are checked by default");
        softAssert.assertTrue(addClubPopUpStepOne.getMaxAgeInput().getAttribute("value").isEmpty(),
                "The maximum age input isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getCenterSelect().getText().isEmpty(), "The club name input isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getMinAgeInput().getAttribute("value").isEmpty(),
                "The minimum age input isn't empty by default");

    }
    private void fillStepOneComponentWithValidData(){
        String validClubName="IT club";
        String validMinAge="2";
        String validMaxAge="18";
        addClubPopUpStepOne.getClubNameInputElement().getInput().sendKeys(validClubName);
        softAssert.assertEquals(addClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value")
                ,validClubName);
        addClubPopUpStepOne.getCategoriesCheckboxList().get(1).click();

        softAssert.assertEquals(addClubPopUpStepOne.getCategoriesCheckboxList().get(0).getText(),
                addClubPopUpStepOne.getCheckedCategoriesList().get(0).getText());
        addClubPopUpStepOne.setMaxAgeInput(validMaxAge);
        addClubPopUpStepOne.setMinAgeInput(validMinAge);
        softAssert.assertEquals(addClubPopUpStepOne.getMinAgeInput().getAttribute("value")
                ,validMinAge);
        softAssert.assertEquals(addClubPopUpStepOne.getMaxAgeInput().getAttribute("value"),validMaxAge);
        addClubPopUpStepOne.getCenterSelect().click();
        addClubPopUpStepOne.getCentersList().get(0).click();
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepOne.getCenterSelectedTitle()));
       softAssert.assertEquals(addClubPopUpStepOne.getCentersList().get(0).getText(),
               addClubPopUpStepOne.getCenterSelectedTitle().getText());
    }
    private void verifyAllParametersAreEmptyByDefaultStepTwo(){
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepTwo.getClubLocationsTitle()));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(addClubPopUpSider.getSecondStepIconBackground(),"border-color", "rgba(255, 255, 255, 1)")));
        softAssert.assertTrue(addClubPopUpStepTwo.getCheckedWorkDaysList().isEmpty(),
                "Some of the work days are checked by default");
        softAssert.assertEquals(addClubPopUpStepTwo.getSwitchButton().getAttribute("aria-checked"),"false",
                "The switch button is toggled by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getTelephoneInputElement().getInput().getAttribute("value").isEmpty(),
                "The telephone input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getFacebookInputElement().getInput().getAttribute("value").isEmpty(),
                "The facebook input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getWhatsappInputElement().getInput().getAttribute("value").isEmpty(),
                "The whatsapp input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getEmailInputElement().getInput().getAttribute("value").isEmpty(),
                "The email input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getSkypeInputElement().getInput().getAttribute("value").isEmpty(),
                "The skype input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getSiteInputElement().getInput().getAttribute("value").isEmpty(),
                "The site input element isn't empty by default");
    }
}
