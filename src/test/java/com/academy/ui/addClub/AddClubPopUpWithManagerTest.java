package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.components.ClubsPaginationComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.utils.ConfigProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddClubPopUpWithManagerTest extends LoginWithManagerTestRunner {

    private static final String VALID_CLUB_NAME = "Add club name 2";
    private static final String CATEGORY = "Спортивні секції";
    private static final String VALID_MIN_AGE = "5";
    private static final String VALID_MAX_AGE = "8";
    private static final String VALID_TELEPHONE_NUMBER = "0987656453";
    private static final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private static final String VALID_DESCRIPTION = "Lorem ipsum dolor sit amet orci aliquam.";
    private static final Map<String, String> VALID_LOCATION = new HashMap<>() {{
        put("name", "main location");
        put("city", "Київ");
        put("district", "Деснянський");
        put("metro", "Академмістечко");
        put("address", "бульвар Академіка Вернадського, 10");
        put("coordinates", "50.459261, 30.378982");
    }};

    private static final String SUCCESSFUL_POPUP = "Гурток успішно створено";

    private static final String VALID_CENTER_NAME = "Курси програмування IT-stat";


    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;
    private final String image1FileName = "image.png";
    private final String image2FileName = "image2.png";
    private WebDriverWait wait;

    private AddClubPopUpSider sider;
    private ConfigProperties properties;

    @BeforeMethod(description = "Preconditions: Get addClubPopUp and stepOne components, make softAssert object")
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(5);
        stepOne = addClubPopUpComponent.getStepOneContainer();
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private void fillStepOneWithValidDataPreconditions() {
        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY).setMinAgeInput(VALID_MIN_AGE).setMaxAgeInput(VALID_MAX_AGE).clickNextStepButton();
    }

    private void fillStepTwoWithValidDataPreconditions() {
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        stepTwo.getWorkDaysCheckboxList().get(0).click();
        stepTwo.getWorkDaysCheckboxList().get(1).click();
        stepTwo.clickNextStepButton();
    }

    @Test
    @Description("Verify that a club without center is created if all parameters are filled with valid values")
    @Issue("TUA-121")
    public void clubWithoutCenterCreated_ok() {
        String testImage = "harrybean.jpg";
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
        softAssert.assertEquals(stepOne.getDropdownPlaceholder().getText(), "Назва центру");

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
        stepThree.getClubGalleryDownloadInput().sendKeys(properties.getImagePath(testImage));

        List<WebElement> clubGalleryUploadedImgs = stepThree.getClubGalleryUploadedImgs();
        softAssert.assertFalse(clubGalleryUploadedImgs.isEmpty());
        stepThree.getUploadedGalleryImgByIndex(0).clickDeleteImage();
        softAssert.assertTrue(clubGalleryUploadedImgs.isEmpty());

        stepThree.getClubLogoDownloadButton().click();
        stepThree.getClubLogoDownloadInput().sendKeys(properties.getImagePath(testImage));
        softAssert.assertTrue(stepThree.getUploadedLogoElement().getImageTitle().getText().contains(testImage));

        stepThree.getClubCoverDownloadButton().click();
        stepThree.getClubCoverDownloadInput().sendKeys(properties.getImagePath(testImage));
        softAssert.assertTrue(stepThree.getUploadedCoverElement().getImageTitle().getText().contains(testImage));

        stepThree.setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty(), "Should be no errors with 50 symbols");

        stepThree.clickCompleteButton();
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that the ‘Опис’ text field is highlighted in red, when a user leaves the field blank")
    @Issue("TUA-179")
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

        softAssert.assertAll();
    }

    @Test(description = "TUA-924")
    @Description("[Додати гурток] Verify that Керівник can only one photo " +
            "added in 'Обкладинка' photo in JPEG/PNG format and desired photo size 1268*840")
    @Issue("TUA-924")
    public void checkManagerCanAddOnePhotoAsCoverInCertainSize() {
        fillStepOneWithValidDataPreconditions();
        stepOne.clickNextStepButton();
        fillStepTwoWithValidDataPreconditions();
        stepTwo.clickNextStepButton();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.getClubCoverDownloadInput().sendKeys(ConfigProperties.getImagePath(image1FileName));
        WebElement firstUploadedElement = wait.until(ExpectedConditions.visibilityOf(stepThree.getAllUploadedElements().get(0)));
        softAssert.assertTrue(firstUploadedElement.getAttribute("title").contains(image1FileName), "The first photo wasn't uploaded");
        stepThree.getClubCoverDownloadInput().sendKeys(ConfigProperties.getImagePath(image2FileName));
        wait.until(ExpectedConditions.stalenessOf(firstUploadedElement));
        WebElement refreshedElement = wait.until(ExpectedConditions.visibilityOf(stepThree.getAllUploadedElements().get(0)));
        softAssert.assertEquals(stepThree.getAllUploadedElements().size(), 1, "More than one photo could be added in the upload cover element");
        softAssert.assertTrue(refreshedElement.getAttribute("title").contains(image2FileName), "The second photo wasn't uploaded");
        softAssert.assertAll();
    }

    @Test(description = "Club can't be created with empty mandatory fields")
    @Description("Verify that a club can't be created if mandatory parameters are empty")
    @Issue("TUA-123")
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

    @Test(description = "Error messages appear when clubs description is more 1500 symbols")
    @Description("Verify that error message ‘Опис гуртка задовгий’ appears when the user enters more than 1500 symbols into the field")
    @Issue("TUA-177")
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

    @Test(description = "UI test for third step 'Description' of Add club pop-up")
    @Description("Check 'Опис' tab on 'Додати гурток' pop-up window (UI)")
    @Issue("TUA-119")
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

        softAssert.assertEquals(stepThree.getPreviousStepButton().getText(), "Назад");
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
        softAssert.assertTrue(stepThree.getPreviousStepButton().equals(driver.switchTo().activeElement()),
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

        softAssert.assertTrue(stepThree.getPreviousStepButton().isDisplayed(),
                "Step Previous Step Button should be displayed");
        softAssert.assertTrue(stepThree.getNextStepButton().isDisplayed(),
                "Step Submit Button should be displayed");
    }

    @Test(dataProvider = "validDescription",dataProviderClass = AddClubWithManagerDataProvider.class)
    @Description("Verify that the ‘Опис’ text field is filled in with valid data")
    @Issue("TUA-173")
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

    @Test(dataProvider = "invalidAddress", dataProviderClass = AddClubWithManagerDataProvider.class)
    @Description("Verify error message for 'Адреса’ field of ‘Додати локацію’ pop-up when creating a club")
    @Issue("TUA-250")
    public void checkErrorForAddressAddLocation(String input) {
        softAssert = new SoftAssert();
        fillStepOneWithValidDataPreconditions();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddLocationPopUpComponent addLocation = stepTwo.clickAddLocationButton();
        addLocation.getLocationNameInputElement().setValue("Lorem");
        addLocation.getLocationCityDropdownElement().clickDropdown().selectValue("Одеса");
        addLocation.getLocationDistrictDropdownElement().clickDropdown().selectValue("Малиновський");
        addLocation.getLocationMetroDropdownElement().clickDropdown().selectValue("Фонтан");

        addLocation.getLocationAddressInputElement().setValue(input);
        softAssert.assertEquals(addLocation.getLocationAddressInputElement().getErrorMessagesTextList().get(0), "Некоректна адреса");

        addLocation.getLocationAddressInputElement().clearInput().setValue("");
        softAssert.assertEquals(addLocation.getLocationAddressInputElement().getErrorMessagesTextList().get(0) + System.lineSeparator() + addLocation.getLocationAddressInputElement().getErrorMessagesTextList().get(1), "Це поле є обов'язковим" + System.lineSeparator() + "Некоректна адреса");

        softAssert.assertAll();
    }

    @Test(description = "Verify that Manager can add and delete photos for existing club")
    @Description("Verify that 'Керівник' can add and delete 'Лого', 'Обкладинка' photo")
    @Issue("TUA-922")
    public void testAddAndDeletePhotoInLogoAndCover() {
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.getClubLogoDownloadInput().sendKeys(ConfigProperties.getImagePath(image1FileName));

        stepThree.getUploadedLogoElement().waitImageLoad(5);
        softAssert.assertEquals(stepThree
                .getUploadedLogoElement()
                .getImageTitle()
                .getText(),
                image1FileName,
                "Photo not added for Logo");
        stepThree.getUploadedLogoElement().clickDeleteImage();

        stepThree.getClubCoverDownloadInput().sendKeys(ConfigProperties.getImagePath(image2FileName));
        stepThree.getUploadedCoverElement().waitImageLoad(5);
        softAssert.assertEquals(stepThree
                .getUploadedCoverElement()
                .getImageTitle()
                .getText(),
                image2FileName,
                "Photo not added for Cover");

        stepThree.getUploadedCoverElement().clickDeleteImage();

        softAssert.assertAll();
    }

    @Test(description = "Verify from 1 to 5 photo can be added in gallery on the 'add club pop up'")
    @Description("Verify 'Керівник' can add from 1 to 5 photos in 'Галерея' step of the add club pop up")
    @Issue("TUA-925")
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
    public void checkIfDefaultIconIsSet() {
        softAssert = new SoftAssert();

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.setDescriptionValue(VALID_DESCRIPTION);
        stepThree.clickCompleteButton();
        ProfilePage profilePage = new ProfilePage(driver);

        SwitchPaginationComponent pagination = profilePage.getSwitchPagination();
        if(pagination != null){
            pagination.getPaginationItems().get(pagination.getPaginationItems().size() - 1).click();
            profilePage = new ProfilePage(driver);
        }
        ClubCardWithEditComponent newClub = profilePage.getClubCardByName(VALID_CLUB_NAME);

        if (newClub == null) {
            softAssert.fail("Club was not added");
            softAssert.assertAll();
            return;
        }

        softAssert.assertNotEquals(newClub.getLogoSrc(), "");
        softAssert.assertAll();
    }

    @Test()
    @Issue("TUA-178")
    public void checkBanRussianLanguageOnDescription() {
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();

        stepThree.setDescriptionValue("Опис, що включаэ російську букву в слові включає");
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().stream().anyMatch(message -> Objects.equals(message.getText(), "Опис гуртка не може містити російські літери")));
        stepThree.clearDescriptionTextarea();

        int initialErrorCount = stepThree.getErrorMessagesTextarea().size();
        stepThree.setDescriptionValue("Опис, що вклЫчає різні російські бüкви в декількох словäх");
        stepThree.waitNewError(initialErrorCount);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().stream().anyMatch(message -> Objects.equals(message.getText(), "Опис гуртка не може містити російські літери")));
        stepThree.clearDescriptionTextarea();

        initialErrorCount = stepThree.getErrorMessagesTextarea().size();
        stepThree.setDescriptionValue("Опыс, щö включає різні російські букви в декільüою словäх");
        stepThree.waitNewError(initialErrorCount);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().stream().anyMatch(message -> Objects.equals(message.getText(), "Опис гуртка не може містити російські літери")));
        stepThree.clearDescriptionTextarea();
        softAssert.assertAll();
    }


    @Test(description = "TUA-978")
    public void verifyAddingLocationWithoutCenter() {
        fillStepOneWithValidDataPreconditions();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddLocationPopUpComponent addLocation = stepTwo.clickAddLocationButton();
        addLocation.getLocationNameInputElement().setValue(VALID_LOCATION.get("name"));
        addLocation.getLocationCityDropdownElement().clickDropdown().selectValue(VALID_LOCATION.get("city"));
        addLocation.getLocationDistrictDropdownElement().clickDropdown().selectValue(VALID_LOCATION.get("district"));
        addLocation.getLocationMetroDropdownElement().clickDropdown().selectValue(VALID_LOCATION.get("metro"));
        addLocation.getLocationAddressInputElement().setValue(VALID_LOCATION.get("address"));
        addLocation.getLocationCoordinatesInputElement().setValue(VALID_LOCATION.get("coordinates"));
        addLocation.getLocationTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        addLocation.clickAddLocationButton();

        LocationListElement newLocation = stepTwo.getListOfLocationElements().getFirst();
        softAssert.assertEquals(newLocation.getLocationItemTitle(), VALID_LOCATION.get("name"));
        softAssert.assertEquals(newLocation.getDescriptionTitle(), VALID_LOCATION.get("address"));

        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        stepTwo.clickNextStepButton();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.getClubDescriptionTextarea().sendKeys(VALID_DESCRIPTION);
        stepThree.clickCompleteButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), SUCCESSFUL_POPUP, "Successful club creation popup message should appear");
    }

    @Test(description = "TUA-135")
    public void verifyAddingLocationWithCenterWithoutOptionalParams() {
        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY).setMinAgeInput(VALID_MIN_AGE).setMaxAgeInput(VALID_MAX_AGE);
        stepOne.clickCenterDropdown().selectCenter(VALID_CENTER_NAME);
        stepOne.clickNextStepButton();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        stepTwo.clickNextStepButton();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.setDescriptionValue(VALID_DESCRIPTION);
        stepThree.clickCompleteButton();

        softAssert.assertEquals(homePage.getTopNoticeMessage().getText(), SUCCESSFUL_POPUP, "Successful club creation popup message should appear");
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Керівник can preview and delete photos in 'Галерея' step and not add any photos")
    @Issue("TUA-926")
    public void testGalleryUploadAndDeletePhoto() {
        stepOne.getClubNameInputElement().setValue("Lazy club");
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(VALID_MIN_AGE)
                .setMaxAgeInput(VALID_MAX_AGE)
                .clickNextStepButton();
        fillStepTwoWithValidDataPreconditions();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.getClubGalleryDownloadInput().sendKeys(ConfigProperties.getImagePath(image1FileName));
        stepThree.getUploadedGalleryImgByIndex(0).waitImageLoad(5);
        softAssert.assertFalse(stepThree.getClubGalleryUploadedImgs().isEmpty());

        stepThree.getUploadedGalleryImgByIndex(0).clickPreviewImage();
        softAssert.assertEquals(stepThree
                        .getUploadedGalleryImgByIndex(0)
                        .getModalPreviewImageTitle()
                        .getText(),
                image1FileName,
                "Uploaded different photo");

        stepThree.getUploadedGalleryImgByIndex(0).clickClosePreviewWindow().clickDeleteImage();
        softAssert.assertTrue(stepThree.getClubGalleryUploadedImgs().isEmpty());

        stepThree.getClubDescriptionTextarea().sendKeys("Спорт - це для кожного (за певних умов).");
        stepThree.clickCompleteButton();
        softAssert.assertTrue(homePage.getTopNoticeMessage().isDisplayed());
        softAssert.assertAll();
    }
}
