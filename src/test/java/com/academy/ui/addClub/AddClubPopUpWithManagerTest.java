package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AddClubPopUpWithManagerTest extends LoginWithManagerTestRunner {

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
        stepTwo.getWorkDaysCheckboxList().get(0).click();
        stepTwo.getWorkDaysCheckboxList().get(1).click();
        stepTwo.clickNextStepButton();
    }

    @Test(description = "TUA-121")
    public void clubWithoutCenterCreated_ok() {
        String imgPath = "D:\\landscape.jpg";
        AddClubInputElement clubNameInputElement = stepOne.getClubNameInputElement();
        softAssert.assertTrue(clubNameInputElement.getInput().getAttribute("value").isEmpty());

        List<WebElement> categoriesCheckboxList = stepOne.getCategoriesCheckboxList();
        softAssert.assertFalse(categoriesCheckboxList.get(0).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(1).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(2).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(3).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(4).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(5).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(6).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(7).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(8).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(9).isSelected());
        softAssert.assertFalse(categoriesCheckboxList.get(10).isSelected());

        softAssert.assertTrue(stepOne.getMinAgeInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepOne.getMaxAgeInput().getAttribute("value").isEmpty());
        softAssert.assertEquals(stepOne.getSelectPlaceholder().getText(), "Назва центру");

        fillStepOneWithValidDataPreconditions();

        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        List<WebElement> workDaysCheckboxList = stepTwo.getWorkDaysCheckboxList();
        softAssert.assertFalse(workDaysCheckboxList.get(0).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(1).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(2).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(3).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(4).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(5).isSelected());
        softAssert.assertFalse(workDaysCheckboxList.get(6).isSelected());

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getFacebookInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getEmailInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getSkypeInputElement().getInput().getAttribute("value").isEmpty());
        softAssert.assertTrue(stepTwo.getSiteInputElement().getInput().getAttribute("value").isEmpty());

        fillStepTwoWithValidDataPreconditions();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        softAssert.assertTrue(stepThree.getClubDescriptionTextarea().getText().isEmpty());

        stepThree.getClubGalleryDownloadButton().click();
        stepThree.getClubGalleryDownloadInput().sendKeys(imgPath);
        List<WebElement> clubGalleryUploadedImgs = stepThree.getClubGalleryUploadedImgs();
        softAssert.assertFalse(clubGalleryUploadedImgs.isEmpty());
        stepThree.getUploadedGalleryImg(0).clickRemoveImg();
        softAssert.assertTrue(clubGalleryUploadedImgs.isEmpty());

        stepThree.getClubLogoDownloadButton().click();
        stepThree.getClubLogoDownloadInput().sendKeys(imgPath);
        softAssert.assertTrue(stepThree.getUploadedLogoImg().getImgTitle().getText().contains("landscape.jpg"));

        stepThree.getClubCoverDownloadButton().click();
        stepThree.getClubCoverDownloadInput().sendKeys(imgPath);
        softAssert.assertTrue(stepThree.getUploadedCoverImg().getImgTitle().getText().contains("landscape.jpg"));

        stepThree.setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessages().isEmpty(), "Should be no errors with 50 symbols");

        stepThree.clickCompleteButton();
        softAssert.assertAll();
    }

    @Test(description = "TUA-925")
    public void verify5PhotoCanBeAddedByManager() {
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree = stepThree.uploadImgToGallery(configProperties.getPathToImg());

        softAssert.assertTrue(stepThree.getClubGalleryUploadedImgs().size() == 1);

        stepThree = stepThree.uploadImgToGallery(configProperties.getPathToImg())
                .uploadImgToGallery(configProperties.getPathToImg())
                .uploadImgToGallery(configProperties.getPathToImg())
                .uploadImgToGallery(configProperties.getPathToImg());

        softAssert.assertTrue(stepThree.getClubGalleryUploadedImgs().size() == 5);
        softAssert.assertAll();
    }
}
