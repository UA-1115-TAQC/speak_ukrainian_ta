package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpSider;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepTwo;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.elements.BaseDropdownElement;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertTrue;


import java.util.List;

public class AddClubPopUpWithAdminTest extends LoginWithAdminTestRunner {
    private static final String DEFAULT_INPUT = "qwerty";
    private static final String VALID_CLUB_NAME = "Add club name";
    private static final String CATEGORY = "Спортивні секції";
    private static final String VALID_MIN_AGE = "2";
    private static final String VALID_MAX_AGE = "18";
    private static final String VALID_TELEPHONE_NUMBER = "0987656453";
    private static final String TEXT_40_SYMBOLS = "Abc ".repeat(10);
    private static final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
    private static final String TEXT_1000_SYMBOLS = "Abcd ".repeat(200);
    private static final String TEXT_1500_SYMBOLS = "Abcde ".repeat(250);
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpSider sider;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        stepOne = addClubPopUpComponent.getStepOneContainer();
        addClubPopUpComponent.waitPopUpOpen(10);
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
        stepTwo.clickNextStepButton();
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

    @Test(description = "TUA-237")
    public void validate_inputs_in_addLocationForm_ok() {

        fillStepOneWithValidDataPreconditions();

        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddLocationPopUpComponent addLocationPopUpComponent = stepTwo.clickAddLocationButton();

        addLocationPopUpComponent.getLocatioNameInputElement().setValue("Україна");
        softAssert.assertTrue(addLocationPopUpComponent.getLocatioNameInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        BaseDropdownElement cityDropdownElement = addLocationPopUpComponent.getLocatioCityDropdownElement().clickDropdown();
        cityDropdownElement.selectValue("Київ");
        softAssert.assertTrue(addLocationPopUpComponent.getLocatioCityDropdownElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        BaseDropdownElement districtDropdownElement = addLocationPopUpComponent.getLocationDistrictDropdownElement().clickDropdown();
        districtDropdownElement.selectValue("Шевченківський");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationDistrictDropdownElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationAddressInputElement().setValue("вул. Шевченка, 233/1");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationAddressInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationCoordinatesInputElement().setValue("49.829104498711104, 24.005058710351314");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationCoordinatesInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getLocationTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        softAssert.assertTrue(addLocationPopUpComponent.getLocationTelephoneInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        addLocationPopUpComponent.getAddLocationButton().click();

        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getFacebookInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getFacebookInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getWhatsappInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getEmailInputElement().setValue("qwerty@gmail.com");
        softAssert.assertTrue(stepTwo.getEmailInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getSkypeInputElement().setValue(DEFAULT_INPUT);
        softAssert.assertTrue(stepTwo.getSkypeInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.getSiteInputElement().setValue("https://qwerty.com.ua");
        softAssert.assertTrue(stepTwo.getSiteInputElement().getValidationCircleIcon()
                .getAttribute("class").contains(VALID_CIRCLE_ICON));

        stepTwo.clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree.getErrorMessagesTextarea().isEmpty(), "Should be no errors with 50 symbols");

        addClubPopUpComponent.getCloseButton().click();
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

    @Test(description = "TUA-928'")
    public void checkNewClubAddedWithCorrectedData() {

        final String INVALID_CLUB_NAME = "ÄыЁЪùייראפ";
        final String VALID_CLUB_NAME = "ПОРівд(*^*&%jhTY";
        final String ERROR_SYMBOL_MESSAGE = "Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи";
        final String TELEPHONE_ERROR_SYMBOL_MESSAGE = "Телефон не може містити спеціальні символи, літери та пробіли";
        final String TELEPHONE_ERROR_FORMAT_MESSAGE = "Телефон не відповідає вказаному формату";
        final String INVALID_MIN_AGE = "0";
        final String INVALID_MAX_AGE = "35";
        final String INVALID_TELEPHONE_NUMBER = "&*^роYT8";
        final String INVALID_DESCRIPTION = "%;№?*(?:фЙїqfG123456789 ÄыЁЪ ¥¼µ€";

        stepOne.getClubNameInputElement().setValue(INVALID_CLUB_NAME);
        softAssert.assertTrue(stepOne
                        .getClubNameInputElement()
                        .getErrorMessagesTextList()
                        .contains(ERROR_SYMBOL_MESSAGE),
                "Error message should have text " + ERROR_SYMBOL_MESSAGE);

        Actions actions = new Actions(driver);

        stepOne.setMinAgeInput(INVALID_MIN_AGE);
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertEquals(stepOne
                        .getMinAgeInput()
                        .getAttribute("value"),
                VALID_MIN_AGE);

        stepOne.setMaxAgeInput(INVALID_MAX_AGE);
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertEquals(stepOne
                        .getMaxAgeInput()
                        .getAttribute("value"),
                VALID_MAX_AGE);

        stepOne.clickNextStepButton();
        softAssert.assertEquals(stepOne
                        .getClubNameInputElement()
                        .getInput()
                        .getAttribute("value"),
                INVALID_CLUB_NAME);
        softAssert.assertEquals(stepOne
                        .getMinAgeInput()
                        .getAttribute("value"),
                VALID_MIN_AGE);
        softAssert.assertEquals(stepOne
                        .getMaxAgeInput()
                        .getAttribute("value"),
                VALID_MAX_AGE);

        AddClubInputElement nameInput = stepOne.getClubNameInputElement();
        nameInput.clearInput();
        nameInput.setValue(VALID_CLUB_NAME);
        softAssert.assertTrue(nameInput
                        .getValidationCircleIcon()
                        .getAttribute("class")
                        .contains(VALID_CIRCLE_ICON),
                "Green circle check icon should appear");

        stepOne.selectCategory(CATEGORY);

        stepOne.clickNextStepButton();

        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddClubInputElement telephoneInput = stepTwo.getTelephoneInputElement();
        telephoneInput.setValue(INVALID_TELEPHONE_NUMBER);
        softAssert.assertTrue(telephoneInput
                        .getValidationCircleIcon()
                        .getAttribute("class")
                        .contains(INVALID_CIRCLE_ICON),
                "Red circle close icon should appear");
        softAssert.assertTrue(telephoneInput
                        .getErrorMessagesTextList().contains(TELEPHONE_ERROR_SYMBOL_MESSAGE),
                "Error message should have text " + TELEPHONE_ERROR_SYMBOL_MESSAGE);

        softAssert.assertTrue(telephoneInput
                        .getErrorMessagesTextList().contains(TELEPHONE_ERROR_FORMAT_MESSAGE),
                "Error message should have text " + TELEPHONE_ERROR_FORMAT_MESSAGE);

        stepTwo.clickNextStepButton();
        softAssert.assertEquals(telephoneInput
                        .getInput()
                        .getAttribute("value"),
                INVALID_TELEPHONE_NUMBER);
        telephoneInput.clearInput();
        telephoneInput.setValue(VALID_TELEPHONE_NUMBER);
        softAssert.assertTrue(telephoneInput
                        .getValidationCircleIcon()
                        .getAttribute("class")
                        .contains(VALID_CIRCLE_ICON),
                "Green circle check icon should appear");

        stepTwo.clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.setDescriptionValue(INVALID_DESCRIPTION);
        softAssert.assertTrue(stepThree
                        .getValidationTextareaCircleIcon()
                        .getAttribute("class")
                        .contains(INVALID_CIRCLE_ICON),
                "Red circle close icon should appear");
        softAssert.assertTrue(stepThree
                        .getErrorMessagesTextList()
                        .contains(ERROR_SYMBOL_MESSAGE),
                "Error message should have text " + ERROR_SYMBOL_MESSAGE);

        stepThree.clickCompleteButton();
        softAssert.assertEquals(stepThree
                        .getClubDescriptionTextarea()
                        .getAttribute("value"),
                INVALID_DESCRIPTION);

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_50_SYMBOLS);
        softAssert.assertTrue(stepThree
                        .getValidationTextareaCircleIcon()
                        .getAttribute("class")
                        .contains(VALID_CIRCLE_ICON),
                "Green circle check icon should appear");

        stepThree.clickCompleteButton();

        softAssert.assertTrue(driver.getCurrentUrl().equals(configProperties.getBaseUrl()),
                "Home Page should be opened after adding club");

        softAssert.assertAll();

    }

    @Test(description = "TUA-930")
    public void checkStepOneClubNameWithInvalidData() {
        String incorrectClubNameErrorMessage = "Некоректна назва гуртка";
        String enterNameClubErrorMessage = "Введіть назву гуртка";
        String invalidClubName = "123Qw*&#єЇ".repeat(11);

        stepOne = addClubPopUpComponent.getStepOneContainer();

        stepOne.getClubNameInputElement().setValue(" ÄыЁЪùייראפ");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectClubNameErrorMessage),
                "Below the field error message in the red color appears : "
                        + "Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().clearInput();
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(enterNameClubErrorMessage));
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().setValue("ƻ®©¥¼µ€");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectClubNameErrorMessage),
                "Below the field error message in the red color appear : "
                        + "Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().clearInput();
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(enterNameClubErrorMessage));
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().setValue("       ");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectClubNameErrorMessage),
                "Below the field error message in the red color appears : "
                        + "Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().clearInput();
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(enterNameClubErrorMessage));
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().setValue("@fЙ8");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectClubNameErrorMessage),
                "Below the field error message in the red color appears : Назва гуртка закоротка");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                        .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().clearInput();
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(enterNameClubErrorMessage));
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepOne.getClubNameInputElement().setValue(invalidClubName);
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectClubNameErrorMessage),
                "Below the field error message in the red color appears : Назва гуртка задовга");
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                        .getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        softAssert.assertAll();
    }

    @Test(description = "TUA-954")
    public void checkStepTwoTelephoneInvalidData() {
        String telephoneErrorMessage = "Телефон не відповідає вказаному формату";

        fillStepOneWithValidDataPreconditions();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();

        stepTwo.getTelephoneInputElement().setValue("fgtskana");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                .getErrorMessagesTextList().get(0).equals(telephoneErrorMessage),
                "The error message is displayed : Телефон не може містити літери");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepTwo.getTelephoneInputElement().clearInput().setValue("/*-+()");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                        .getErrorMessagesTextList().get(0).equals(telephoneErrorMessage),
                "The error message is displayed : Телефон не може містити спеціальні символи");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepTwo.getTelephoneInputElement().clearInput().setValue("06725841");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                        .getErrorMessagesTextList().get(0).equals(telephoneErrorMessage));
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));
    }

    @Test(description = "LVTEACH-23")
    public void checkFillInNameFieldWithInvalidData_ErrorMessage() {
        final var testData = List.of("ÄыЁЪùראפ", "ƻ®©¥¼µ€", "       ", "@fЙ8",
                "123Qw*&#єЇ".repeat(10) + "o");
        final var expectedErrorMessage = """
                Це поле може містити тільки українські та англійські літери, цифри та спеціальні символи""";

        var clubNameInputElement = stepOne.getClubNameInputElement();
        testData.forEach(data -> {
            clubNameInputElement.setValue(data);

            softAssert.assertEquals(clubNameInputElement.getErrorMessagesTextList().get(0), expectedErrorMessage,
                    "Incorrect error message: ");
            softAssert.assertTrue(clubNameInputElement.getValidationCircleIcon().isDisplayed());

            clubNameInputElement.clearInput();

            softAssert.assertTrue(clubNameInputElement.getValidationCircleIcon().isDisplayed());
        });
        softAssert.assertAll();
    }

    @Test(description = "TUA-931", dataProvider = "validClubName", dataProviderClass = AddClubWithAdminDataProvider.class)
    public void checkValidClubNameInput(String input){
        softAssert = new SoftAssert();

        stepOne.getClubNameInputElement().setValue(input);
        softAssert.assertEquals(
                stepOne.getClubNameInputElement().getValidationCircleIcon().getCssValue("color"),
                "rgba(82, 196, 26, 1)"
        );

        stepOne.getClubNameInputElement().clearInput();
        softAssert.assertEquals(
                stepOne.getClubNameInputElement().getValidationCircleIcon().getCssValue("color"),
                "rgba(255, 77, 79, 1)"
        );
        softAssert.assertEquals(
                stepOne.getClubNameInputElement().getErrorMessages().get(0).getText(),
                "Введіть назву гуртка");

        softAssert.assertAll();
    }
    
    @Test(description = "TUA-312")
    public void checkAddClubPopUpIsDisplayed(){
        WebElement element = stepOne.getNextStepButton();
        assertTrue(element.isDisplayed());
    }
}
