package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ChangePasswordInvalidDataForManager extends LoginWithManagerTestRunner {
    private EditProfilePopUp editProfilePopUpComponent;
    private SoftAssert softAssert;
    private WebDriverWait wait;
    private final String WRONG_LENGTH = "Пароль не може бути коротшим, ніж 8 та довшим, ніж 20 символів";
    private final String NO_SPECIAL_SYMBOLS = "Пароль повинен містити великі/маленькі літери латинського алфавіту, цифри та спеціальні символи";
    private final String CONFIRMATION_FAILED = "'Значення поля ‘Підтвердити новий пароль’ має бути еквівалентним значенню поля ‘Новий пароль’";
    private final Object[][] invalidPasswordErrors = {
//            {"1pas;", "1pas;", WRONG_LENGTH, ""},
//            {"passpasspasspass", "passpasspasspass", NO_SPECIAL_SYMBOLS, ""},
//            {"1Pas", "1Pas", WRONG_LENGTH, NO_SPECIAL_SYMBOLS, ""},
//            {"1;P", "1;P", WRONG_LENGTH, ""},
//            {"1pas;", "11pas;", WRONG_LENGTH, CONFIRMATION_FAILED},
//            {"passpasspasspass", "11passpasspasspass", NO_SPECIAL_SYMBOLS, CONFIRMATION_FAILED},
//            {"1Pas", "111Pas", WRONG_LENGTH, NO_SPECIAL_SYMBOLS, CONFIRMATION_FAILED},
            {"1;P", "1111;P", WRONG_LENGTH, CONFIRMATION_FAILED}
    };

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        initializeProfilePopUp();
    }

    @Test(description = "TUA-848")
    public void test() {
        for (Object[] invalidPasswordError : invalidPasswordErrors) {
            performPasswordValidationTests(invalidPasswordError);
        }

        softAssert.assertAll();
    }

    private void initializeProfilePopUp() {
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUp(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickManagerButton();
        editProfilePopUpComponent.clickCheckBox();
    }

    private void performPasswordValidationTests(Object[] invalidPasswordError) {
        InputWithIconElement currPass = editProfilePopUpComponent.getCurrentPasswordInput();
        InputWithIconElement newPass = editProfilePopUpComponent.getNewPasswordInput();
        InputWithIconElement confPass = editProfilePopUpComponent.getConfirmPasswordInput();

        String confPassTest = (String) invalidPasswordError[invalidPasswordError.length - 1];
        clearInputFields(currPass, newPass, confPass);

        fillNewPasswordFieldWithInvalidDataAndVerify(currPass, configProperties.getManagerPassword(), true);
        fillNewPasswordFieldWithInvalidDataAndVerify(newPass, (String) invalidPasswordError[0], false);

        List<String> newPassErrors = newPass.getErrorMessagesTextList();
        verifyErrorMessages(invalidPasswordError, newPassErrors, 2);

        fillNewPasswordFieldWithInvalidDataAndVerify(confPass, (String) invalidPasswordError[1], confPassTest.isEmpty());
        System.out.println(confPassTest);

        for(WebElement webElement : confPass.getErrorMessages()) {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }

        List<String> confPassErrors = confPass.getErrorMessagesTextList();
        System.out.println(Arrays.toString(confPassErrors.toArray()));
        String confPassError = confPassErrors.isEmpty() ? "" : confPassErrors.get(0);
        System.out.println(confPassError);
        softAssert.assertEquals(confPassTest, confPassError);
    }

    private void clearInputFields(InputWithIconElement... inputElements) {
        for (InputWithIconElement inputElement : inputElements) {
            inputElement.clearInput();
        }
    }

    private void fillNewPasswordFieldWithInvalidDataAndVerify(InputWithIconElement inputWithIconElement, String pwd, boolean validity) {
        inputWithIconElement.getInput().sendKeys(pwd);
        wait.until(ExpectedConditions.visibilityOf(inputWithIconElement.getValidationCircleIcon()));
        wait.until(ExpectedConditions.attributeContains(inputWithIconElement.getInput(), "value", pwd));

        String colorToCheck = validity ? "rgba(82, 196, 26, 1)" : "rgba(255, 77, 79, 1)";
        softAssert.assertTrue(inputWithIconElement.getValidationCircleIcon().getCssValue("color").contains(colorToCheck),
                "The validation icon is not highlighted in the expected color");
    }

    private void verifyErrorMessages(Object[] expectedErrors, List<String> actualErrors, int startIndex) {
        for (int j = 0; j < actualErrors.size(); j++) {
            softAssert.assertEquals(expectedErrors[j + startIndex], actualErrors.get(j));
        }
    }
}
