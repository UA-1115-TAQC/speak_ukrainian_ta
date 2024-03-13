package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpSider;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import com.academy.ui.runners.utils.ConfigProperties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AddClubPopUpWithManagerTest extends LoginWithManagerTestRunner {

    private static final String VALID_CLUB_NAME = "Add club name";
    private static final String CATEGORY = "Спортивні секції";
    private static final String VALID_MIN_AGE = "5";
    private static final String VALID_MAX_AGE = "8";
    private static final String VALID_TELEPHONE_NUMBER = "0987656453";
    private static final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private static final String VALID_DESCRIPTION = "Lorem ipsum dolor sit amet orci aliquam.";
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;
    private AddClubPopUpSider sider;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(5);
        stepOne = addClubPopUpComponent.getStepOneContainer();
        softAssert = new SoftAssert();
    }

    private void fillStepOneWithValidDataPreconditions() {
        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(VALID_MIN_AGE)
                .setMaxAgeInput(VALID_MAX_AGE)
                .clickNextStepButton();
    }

    private void fillStepTwoWithValidDataPreconditions() {
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
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
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty(), "Should be no errors with 50 symbols");

        stepThree.clickCompleteButton();
        softAssert.assertAll();
    }

    @Test(description = "TUA-179")
    public void checkWhenAddClubTextareaFieldIsBlank() {
        fillStepOneWithValidDataPreconditions();
        stepOne.clickNextStepButton();

        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        fillStepTwoWithValidDataPreconditions();
        stepTwo.clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea();
        stepThree.clickCompleteButton();

        softAssert.assertTrue(stepThree.getErrorMessagesTextList().get(0).equals("Некоректний опис гуртка"));
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("aria-label").contains(INVALID_CIRCLE_ICON));
    }

    @Test(description = "TUA-123")
    public void checkClubCantBeCreatedWithEmptyMandatoryParameters() {
        final String EMPTY_STRING = "";
        final String DISABLED_BUTTON_MESSAGE = "Next button should be disabled";

        softAssert.assertEquals(stepOne
                        .getClubNameInputElement()
                        .getInput()
                        .getAttribute("value"),
                EMPTY_STRING,
                "Name should be empty"
        );
        softAssert.assertFalse(stepOne.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE + " when name field is empty");

        softAssert.assertTrue(stepOne.getCheckedCategoriesList().isEmpty(),
                "Checkboxes should be empty"
        );
        softAssert.assertFalse(stepOne.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE + " when category isn't selected");
        softAssert.assertEquals(stepOne
                        .getMinAgeInput()
                        .getAttribute("value"),
                EMPTY_STRING,
                "Min age should be empty"
        );
        softAssert.assertFalse(stepOne.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE + " when min age field is empty");
        softAssert.assertEquals(stepOne
                        .getMinAgeInput()
                        .getAttribute("value"),
                EMPTY_STRING,
                "Max age should be empty"
        );
        softAssert.assertFalse(stepOne.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE + " when max age field is empty");

        fillStepOneWithValidDataPreconditions();

        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();

        softAssert.assertEquals(stepTwo
                        .getTelephoneInputElement()
                        .getInput()
                        .getAttribute("value"),
                EMPTY_STRING,
                "Telephone should be empty"
        );

        softAssert.assertFalse(stepTwo.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE + " when telephone field is empty");

        fillStepTwoWithValidDataPreconditions();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        softAssert.assertEquals(stepThree
                        .getClubDescriptionTextarea()
                        .getAttribute("value"),
                EMPTY_STRING,
                "Description should be empty"
        );
        softAssert.assertFalse(stepTwo.getNextStepButton().isEnabled(),
                DISABLED_BUTTON_MESSAGE  + " when description textarea is field is empty");

        softAssert.assertAll();
    }

    @Test(description = "TUA-177")
    public void checkDescriptionFieldAllows_1500_MoreAndLessSymbols() {

        final String TEXT_1500_SYMBOLS = "Abcd ".repeat(300);
        final String TEXT_1501_SYMBOLS = TEXT_1500_SYMBOLS + "!";
        final String TEXT_1550_SYMBOLS = "Abcd ".repeat(310);
        final String ERROR_MESSAGE = "Опис гуртка може містити від 40 до 1500 символів.";

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();

        stepThree = addClubPopUpComponent.getStepThreeContainer();

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1500_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty(),
                "Should be no errors with 1500 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty(),
                "Should be no errors with 50 symbols");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1501_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE),
                "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1550_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextList().contains(ERROR_MESSAGE),
                "Should appear error message 'Опис гуртка може містити від 40 до 1500 символів.'");

        softAssert.assertAll();
    }

    @Test(description = "TUA-119")
    public void checkStepTreeDescriptionUI() {
        int WINDOW_WIDTH = 400;
        int WINDOW_HEIGHT = 600;

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();

        sider = addClubPopUpComponent.getSider();
        stepThree = addClubPopUpComponent.getStepThreeContainer();

        checkStepThreeDescriptionElementsPresent();

        softAssert.assertEquals(sider.getFirstStepIcon().getText(), "");
        softAssert.assertEquals(sider.getFirstStepTitle().getText(), "Основна інформація");
        softAssert.assertEquals(sider.getSecondStepIcon().getText(), "");
        softAssert.assertEquals(sider.getSecondStepTitle().getText(), "Контакти");
        softAssert.assertEquals(sider.getThirdStepIcon().getText(), "3");
        softAssert.assertEquals(sider.getThirdStepTitle().getText(), "Опис");

        softAssert.assertEquals(stepThree.getClubTitle().getText(), "Додати гурток");
        softAssert.assertEquals(stepThree.getClubTitle().getCssValue(
                "color"), "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(stepThree.getClubTitle().getCssValue(
                "font-size"), "24px");

        softAssert.assertEquals(stepThree.getClubLogoTitle().getText(), "Логотип");
        softAssert.assertEquals(stepThree.getClubLogoTitle().getCssValue(
                "color"), "rgba(128, 128, 128, 1)");
        softAssert.assertEquals(stepThree.getClubLogoTitle().getCssValue(
                "font-size"), "19px");
        softAssert.assertEquals(stepThree.getClubLogoDownloadButton().getText(), "Завантажити лого");

        softAssert.assertEquals(stepThree.getClubCoverTitle().getText(), "Обкладинка");
        softAssert.assertEquals(stepThree.getClubCoverTitle().getCssValue(
                "color"), "rgba(128, 128, 128, 1)");
        softAssert.assertEquals(stepThree.getClubCoverTitle().getCssValue(
                "font-size"), "19px");
        softAssert.assertEquals(stepThree.getClubCoverDownloadButton().getText(), "Завантажити обкладинку");

        softAssert.assertEquals(stepThree.getClubGalleryTitle().getText(), "Галерея");
        softAssert.assertEquals(stepThree.getClubGalleryTitle().getCssValue(
                "color"), "rgba(128, 128, 128, 1)");
        softAssert.assertEquals(stepThree.getClubGalleryTitle().getCssValue(
                "font-size"), "19px");
        softAssert.assertEquals(stepThree.getClubGalleryDownloadButton().getText(), "Додати");

        softAssert.assertEquals(stepThree.getClubDescriptionTitle().getText(), "Опис");
        softAssert.assertEquals(stepThree.getClubDescriptionTitle().getCssValue(
                "color"), "rgba(128, 128, 128, 1)");
        softAssert.assertEquals(stepThree.getClubDescriptionTitle().getCssValue(
                "font-size"), "19px");

        softAssert.assertEquals(stepThree.getPrevStepButton().getText(), "Назад");
        softAssert.assertEquals(stepThree.getNextStepButton().getText(), "Завершити");
        softAssert.assertFalse(stepThree.getNextStepButton().isEnabled(), "Button should be not active");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getClubLogoDownloadButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Logo Download Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getClubCoverDownloadButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Cover Download Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getClubGalleryDownloadButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Gallery Download Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getClubDescriptionTextarea().equals(driver.switchTo().activeElement()),
                "Focus should be on Description Textarea");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getPrevStepButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Previous Step Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepThree.getNextStepButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Submit Button");

        Dimension dimension = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        driver.manage().window().setSize(dimension);

        checkStepThreeDescriptionElementsPresent();

        softAssert.assertAll();
    }

    private void checkStepThreeDescriptionElementsPresent() {
        softAssert.assertTrue(sider.getFirstStepIcon().isDisplayed(),
                "Step One icon should be displayed");
        softAssert.assertTrue(sider.getFirstStepTitle().isDisplayed(),
                "Step One title should be displayed");

        softAssert.assertTrue(sider.getSecondStepIcon().isDisplayed(),
                "Step Two icon should be displayed");
        softAssert.assertTrue(sider.getSecondStepTitle().isDisplayed(),
                "Step Two title should be displayed");

        softAssert.assertTrue(sider.getThirdStepIcon().isDisplayed(),
                "Step Three icon should be displayed");
        softAssert.assertTrue(sider.getThirdStepTitle().isDisplayed(),
                "Step Three title should be displayed");

        softAssert.assertTrue(stepThree.getClubTitle().isDisplayed(),
                "Step Club title should be displayed");

        softAssert.assertTrue(stepThree.getClubLogoTitle().isDisplayed(),
                "Step Logo title should be displayed");
        softAssert.assertTrue(stepThree.getClubLogoDownloadButton().isDisplayed(),
                "Step Logo Download Button should be displayed");

        softAssert.assertTrue(stepThree.getClubCoverTitle().isDisplayed(),
                "Step Cover title should be displayed");
        softAssert.assertTrue(stepThree.getClubCoverDownloadButton().isDisplayed(),
                "Step Cover Download Button should be displayed");

        softAssert.assertTrue(stepThree.getClubGalleryTitle().isDisplayed(),
                "Step Gallery title should be displayed");
        softAssert.assertTrue(stepThree.getClubGalleryDownloadButton().isDisplayed(),
                "Step Gallery Download Button should be displayed");

        softAssert.assertTrue(stepThree.getClubDescriptionTitle().isDisplayed(),
                "Step Description title should be displayed");

        softAssert.assertTrue(stepThree.getPrevStepButton().isDisplayed(),
                "Step Previous Step Button should be displayed");
        softAssert.assertTrue(stepThree.getNextStepButton().isDisplayed(),
                "Step Submit Button should be displayed");
    }

    @Test(description = "TUA-173", dataProvider = "validDescription",dataProviderClass = AddClubWithManagerDataProvider.class)
    public void checkDescriptionValidData(String input){
        softAssert = new SoftAssert();
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();

        stepThree.setDescriptionValue(input);
        softAssert.assertEquals(
                stepThree.getClubDescriptionValidationMark().getCssValue("color"),
                "rgba(82, 196, 26, 1)");
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty());
        softAssert.assertAll();
    }

    @Test(description = "TUA-925")
    public void verify5PhotoCanBeAddedByManager() {
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree = stepThree.uploadImgToGallery(ConfigProperties.getImagePath("test.png"));

        softAssert.assertTrue(stepThree.getClubGalleryUploadedImgs().size() == 1);

        stepThree = stepThree.uploadImgToGallery(ConfigProperties.getImagePath("test.png"))
                .uploadImgToGallery(ConfigProperties.getImagePath("test.png"))
                .uploadImgToGallery(ConfigProperties.getImagePath("test.png"))
                .uploadImgToGallery(ConfigProperties.getImagePath("test.png"));

        softAssert.assertTrue(stepThree.getClubGalleryUploadedImgs().size() == 5);
        softAssert.assertAll();
    }

    @Test()
    @Description("Verify that the icon of the main category is set by default for 'Лого' if it is not chosen")
    @Issue("TUA-923")
    public void checkIfDefaultIconIsSet(){
        softAssert = new SoftAssert();

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.setDescriptionValue(VALID_DESCRIPTION);
        ProfilePage profilePage = stepThree.clickCompleteButton();

        List<ClubCardWithEditComponent> list = profilePage.getClubCardComponentsList();
        ClubCardWithEditComponent newClub = null;
        for(ClubCardWithEditComponent club : list){
            if(club.getClubName().equals(VALID_CLUB_NAME)){
                newClub = club;
            }
        }

        if(newClub == null){
            softAssert.fail("Club was not added");
            softAssert.assertAll();
            return;
        }

        softAssert.assertNotEquals(newClub.getLogoSrc(), "");
        softAssert.assertAll();
    }

}
