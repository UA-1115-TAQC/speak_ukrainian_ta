package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.components.elements.BaseDropdownElement;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.academy.ui.runners.randomvaluesgenerators.RandomAlphanumericStringGenerator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.time.Duration;

import static org.testng.Assert.assertTrue;


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
    private String validClubDescriptionSample = "Welcome to the cutting-edge realm of Automated Testing Club, where precision meets innovation! " +
            " Unleash the power of code and algorithms in our state-of-the-art facility. " +
            "Explore the depths of test automation, where every line of code is a testament to efficiency and accuracy. " +
            "Join a community of tech enthusiasts, QA experts, and automation wizards who strive for perfection in the digital testing landscape. " +
            "Elevate your skills, share insights, and collaborate on projects that push the boundaries of automated testing. ";
    private static final String VALID_CIRCLE_ICON = "check-circle";
    private static final String INVALID_CIRCLE_ICON = "close-circle";
    private AddClubPopUpComponent addClubPopUpComponent;
    private AddClubPopUpSider sider;
    private AddClubPopUpStepOne stepOne;
    private AddClubPopUpStepTwo stepTwo;
    private AddClubPopUpStepThree stepThree;
    private SoftAssert softAssert;
    private ProfilePage profilePage;
    protected WebDriverWait wait;
    protected String uniqueClubName;

    @BeforeMethod(description = "Preconditions: Get addClubPopUp and stepOne components, make softAssert object")
    public void addClubPopUpTestPrecondition() {
        addClubPopUpComponent = homePage.header.addClubButtonClick();
        stepOne = addClubPopUpComponent.getStepOneContainer();
        addClubPopUpComponent.waitPopUpOpen(10);
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(45));
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

    @Test
    @Description("Verify that a admin can add a location of a club")
    @Issue("TUA-237")
    public void checkAddLocationFormAfterFillingMandatoryFieldsWithValidData() {

        fillStepOneWithValidDataPreconditions();

        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        AddLocationPopUpComponent addLocationPopUpComponent = stepTwo.clickAddLocationButton();

        addLocationPopUpComponent.getLocationNameInputElement().setValue("Україна");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationNameInputElement()
                .getValidationCircleIcon().getAttribute("class").contains(VALID_CIRCLE_ICON));

        BaseDropdownElement cityDropdownElement = addLocationPopUpComponent.getLocationCityDropdownElement().clickDropdown();
        cityDropdownElement.selectValue("Київ");
        softAssert.assertTrue(addLocationPopUpComponent.getLocationCityDropdownElement()
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

    @Test(description = "New club added with corrected data")
    @Description("Verify that new club with corrected data after error messages is added on the website")
    @Issue("TUA-928")
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
        List<String> name_errors = stepOne.getClubNameInputElement().getErrorMessagesTextList();
        softAssert.assertTrue(name_errors.contains(ERROR_SYMBOL_MESSAGE),
                "Error message for club name should have text " + ERROR_SYMBOL_MESSAGE + " instead of " + name_errors);

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
                "Green circle check icon should appear on the club name input");

        stepOne.selectCategory(CATEGORY);

        AddClubPopUpStepTwo stepTwo = stepOne.clickNextStepButton();
        AddClubInputElement telephoneInput = stepTwo.getTelephoneInputElement();
        telephoneInput.setValue(INVALID_TELEPHONE_NUMBER);
        softAssert.assertTrue(telephoneInput
                        .getValidationCircleIcon()
                        .getAttribute("class")
                        .contains(INVALID_CIRCLE_ICON),
                "Red circle close icon should appear on the club telephone input");
        List<String> telephone_errors = telephoneInput.getErrorMessagesTextList();
        softAssert.assertTrue(telephone_errors.contains(TELEPHONE_ERROR_SYMBOL_MESSAGE),
                "Error message should have text " + TELEPHONE_ERROR_SYMBOL_MESSAGE + " instead of " + telephone_errors);

        softAssert.assertTrue(telephone_errors.contains(TELEPHONE_ERROR_FORMAT_MESSAGE),
                "Error message should have text " + TELEPHONE_ERROR_FORMAT_MESSAGE + " instead of " + telephone_errors);

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
                "Green circle check icon should appear on the club telephone input");


        AddClubPopUpStepThree stepThree = stepTwo.clickNextStepButton();
        stepThree.setDescriptionValue(INVALID_DESCRIPTION);
        softAssert.assertTrue(stepThree
                        .getValidationTextareaCircleIcon()
                        .getAttribute("class")
                        .contains(INVALID_CIRCLE_ICON),
                "Red circle close icon should appear on the club description textarea");
        List<String> description_errors = stepThree.getErrorMessagesTextList();
        softAssert.assertTrue(description_errors.contains(ERROR_SYMBOL_MESSAGE),
                "Error message should have text " + ERROR_SYMBOL_MESSAGE + " instead of " + telephone_errors);

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
                "Green circle check icon should appear on the club description textarea");

        stepThree.clickCompleteButton();

        softAssert.assertTrue(driver.getCurrentUrl().equals(configProperties.getBaseUrl()),
                "Home Page should be opened after adding club");

        softAssert.assertAll();

    }

    @Test
    @Description("Verify that 'Назва' field doesn't accept not allowed characters and error message appears")
    @Issue("TUA-930")
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

    @Test(description = "Test fails because for all test inputs same message")
    @Description("Verify error messages for 'Номер телефону' field of 'Додати локацію' pop-up when creating a club")
    @Issue("TUA-954")
    public void checkStepTwoTelephoneInvalidData() {
        String specialSymbolsErrorMessage = "Телефон не може містити спеціальні символи";
        String lettersErrorMessage = "Телефон не може містити літери";
        String incorrectFormatErrorMessage = "Телефон не відповідає вказаному формату";

        fillStepOneWithValidDataPreconditions();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();

        stepTwo.getTelephoneInputElement().setValue("fgtskana");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                        .getErrorMessagesTextList().get(0).equals(lettersErrorMessage),
                "The error message is displayed : Телефон не може містити літери");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepTwo.getTelephoneInputElement().clearInput().setValue("/*-+()");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                        .getErrorMessagesTextList().get(0).equals(specialSymbolsErrorMessage),
                "The error message is displayed : Телефон не може містити спеціальні символи");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        stepTwo.getTelephoneInputElement().clearInput().setValue("06725841");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                .getErrorMessagesTextList().get(0).equals(incorrectFormatErrorMessage));
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().
                getValidationCircleIcon().getAttribute("aria-label").equals(INVALID_CIRCLE_ICON));

        softAssert.assertAll();
    }

    @Test(description = "The valid circle icon appeared after entering valid data into the description field")
    @Description("Verify that the ‘Опис’ text field is filled in " +
            "with valid data when a user enters from 40 to 1500 symbols into the field")
    @Issue("TUA-172")
    public void checkValidationIconWithValidDataForDescriptionField() {
        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_40_SYMBOLS);
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON));

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1000_SYMBOLS);
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON));

        stepThree.clearDescriptionTextarea().setDescriptionValue(TEXT_1500_SYMBOLS);
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("aria-label").contains(VALID_CIRCLE_ICON));

        softAssert.assertAll();
    }

    @Test(description = "TUA-929")
    public void addingNewClubWithValidData() {
        String description = "Дуже гарний і довгий опис, який повністю описує важливість цього для вас";
        ProfilePage profilePage = new ProfilePage(driver);
        SwitchPaginationComponent switchPaginationComponent = new SwitchPaginationComponent(driver, profilePage.getClubsSpace());
        List<ClubCardComponent> clubCardComponentList;

        stepOne = addClubPopUpComponent.getStepOneContainer();
        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(VALID_MIN_AGE)
                .setMaxAgeInput(VALID_MAX_AGE);
        softAssert.assertTrue(stepOne.getClubNameInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-check-circle"));

        stepOne.clickNextStepButton();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue("0670694739");
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getValidationCircleIcon().getAttribute("class").contains("anticon-check-circle"));
        stepTwo.clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.setDescriptionValue(description);
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("class").contains("anticon-check-circle"));
        stepThree.clickCompleteButton();

        softAssert.assertFalse(addClubPopUpComponent.getWebElement().getAttribute("style").contains("display: none;"), "Pop-up still opened");
        softAssert.assertTrue(profilePage.getMyProfileTitle().isDisplayed(), "Profile page doesn't open");

        if (switchPaginationComponent.isPaginationPresent()) {
            switchPaginationComponent.getLastPage();
        }

        clubCardComponentList = profilePage.getClubsElements();
        softAssert.assertTrue(clubCardComponentList.stream().anyMatch(item -> item.getDescription().getText().equals(description)), "There is no such element on page");
        homePage.header.openUserMenu();
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that 'Назва' field doesn't accept not allowed characters and error message appears")
    @Issue("LVTEACH-23")
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

    @Test(dataProvider = "validClubName", dataProviderClass = AddClubWithAdminDataProvider.class)
    @Description("Verify that 'Назва' field accepts allowed characters combinations")
    @Issue("TUA-931")
    public void checkValidClubNameInput(String input) {
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

    @Test(description = "TUA-765")
    public void checkDescriptionWithLessThan40Symbols() {
        String wrongDescriptionTest = "Short description";

        fillStepOneWithValidDataPreconditions();
        fillStepTwoWithValidDataPreconditions();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(wrongDescriptionTest);
        softAssert.assertTrue(stepThree.getValidationTextareaCircleIcon().getAttribute("aria-label").contains(INVALID_CIRCLE_ICON));

        List<String> errors = Arrays.asList("Некоректний опис гуртка", "Опис гуртка може містити від 40 до 1500 символів.");
        for (WebElement error : stepThree.getErrorMessagesTextarea()) {
            softAssert.assertTrue(errors.contains(error.getAttribute("innerText")));
        }
    }

    @Test()
    @Description("Verify that pop-up 'Додати гурток' appears when clicking 'Додати гурток' button at 'Home' page")
    @Issue("TUA-312")
    public void checkAddClubPopUpIsDisplayed() {
        WebElement element = stepOne.getNextStepButton();
        assertTrue(element.isDisplayed());
    }

    @Test(description = "LVTEACH-22")
    @Description("Verify adding new club with valid data")
    @Issue("LVTEACH-22")
    public void verifyAddingANewClubWithValidData() {
        softAssert.assertTrue(stepOne.getClubTitle().getText().contains("Додати гурток"),
                "The the AddClub component isn't displayed");
        softAssert.assertTrue(stepOne.getClubNameInputElement().getInput().isDisplayed(),
                "The first step of the AddClub component isn't displayed");
        sider = addClubPopUpComponent.getSider();
        verifyIconOnTheSiderIsActive(sider.getFirstStepTitle(), "Основна інформація",
                sider.getFirstStepIcon(), sider.getFirstStepIconBackground());
        uniqueClubName = RandomAlphanumericStringGenerator.generateRandomString(8, 15, 3);
        fillStepOneWithValidData(uniqueClubName);
        verifyGreenChecksOnStepOne();
        stepOne.clickNextStepButton();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        sider = addClubPopUpComponent.getSider();
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getInput().isDisplayed(),
                "The second step of the AddClub component isn't displayed");
        verifyIconOnTheSiderIsActive(sider.getSecondStepTitle(), "Контакти",
                sider.getSecondStepIcon(), sider.getSecondStepIconBackground());
        fillStepTwoWithValidData();
        verifyGreenChecksOnStepTwo();
        stepTwo.clickNextStepButton();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        sider = addClubPopUpComponent.getSider();
        softAssert.assertTrue(stepThree.getClubDescriptionTextarea().isDisplayed(),
                "The third step of the AddClub component isn't displayed");
        verifyIconOnTheSiderIsActive(sider.getThirdStepTitle(), "Опис",
                sider.getThirdStepIcon(), sider.getThirdStepIconBackground());
        stepThree.setDescriptionValue(validClubDescriptionSample);
        verifyGreenChecksOnStepThree();
        wait.until(driver -> stepThree.getClubDescriptionTextarea().getText().contains(validClubDescriptionSample));
        stepThree.clickCompleteButton();
        profilePage = new ProfilePage(driver);
        driver.getCurrentUrl();
        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        wait.until(ExpectedConditions.visibilityOf(profilePage.getPhoneUser()));
        profilePage.clickMyClubsAndCentersOnDropdown();
        profilePage.clickMyClubsOnDropdown();
        softAssert.assertTrue(getARecentlyAddedClubCardByClubName(uniqueClubName).getClubNameWithoutTitle().isDisplayed(),
                "The added club isn't displayed  in my profile");
        softAssert.assertTrue(getARecentlyAddedClubCardByClubName(uniqueClubName).getClubNameWithoutTitle().getText().contains(uniqueClubName),
                "The added card isn't displayed  in my profile");


        //Check DB - that club was added //todo
        softAssert.assertAll();
    }

    private ClubCardWithEditComponent getARecentlyAddedClubCardByClubName(String clubName) {
        SwitchPaginationComponent switchPagination = profilePage.getSwitchPagination();
        switchPagination.scrollIntoView(driver, switchPagination.getWebElement());

        switchPagination.getPaginationItems().get((profilePage.getSwitchPagination().getPaginationItems().size() - 1)).click();
        for (ClubCardWithEditComponent card : profilePage.getClubCardComponents()) {
            if (card.getClubNameWithoutTitle().isDisplayed() && card.getClubNameWithoutTitle().getText().contains(clubName)) {
                return card;
            }
        }
        return null;
    }

    private void verifyIconOnTheSiderIsActive(WebElement title, String stepTitleText, WebElement icon, WebElement iconBackground) {
        softAssert.assertEquals(title.getText(), stepTitleText,
                "The sider doesn't contain the '" + stepTitleText + "' text describing the current step of the club addition process");
        softAssert.assertTrue(icon.getCssValue("color").contains("rgba(255, 255, 255, 1)"),
                "The color of the current icon on the sider isn't white to show that it is active");
        softAssert.assertFalse(iconBackground.getCssValue("background").contains("rgb(232, 232, 232)"),
                "The background of the current icon on the sider isn't highlighted in orange to show that it is active");
    }

    private void verifyGreenChecksOnStepOne() {
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(VALID_CIRCLE_ICON));
        softAssert.assertTrue(stepOne.getClubNameInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(VALID_CIRCLE_ICON));
    }

    private void fillStepOneWithValidData(String uniqueClubName) {
        stepOne.getClubNameInputElement().setValue(uniqueClubName);
        stepOne.selectCategory(CATEGORY)
                .setMinAgeInput(VALID_MIN_AGE)
                .setMaxAgeInput(VALID_MAX_AGE);
    }

    private void fillStepTwoWithValidData() {
        stepTwo.getTelephoneInputElement().setValue(VALID_TELEPHONE_NUMBER);
    }

    private void verifyGreenChecksOnStepTwo() {
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                .getValidationCircleIcon().getAttribute("aria-label").equals(VALID_CIRCLE_ICON));
    }

    private void verifyGreenChecksOnStepThree() {
        softAssert.assertTrue(stepThree
                        .getValidationTextareaCircleIcon()
                        .getAttribute("class")
                        .contains(VALID_CIRCLE_ICON),
                "Green circle check icon should appear");
    }
}
