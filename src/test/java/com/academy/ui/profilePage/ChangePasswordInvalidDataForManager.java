package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.randomvaluesgenerators.RandomPasswordGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Arrays;

//change to LoginWithManagerTestRunner
public class ChangePasswordInvalidDataForManager extends LoginWithManagerTestRunner{
    private EditProfilePopUp editProfilePopUpComponent;
    SoftAssert softAssert;
    private String newPassword;
    protected RandomPasswordGenerator passwordGenerator;
    WebDriverWait wait;
    private final String WRONG_LENGTH = "Пароль не може бути коротшим, ніж 8 та довшим, ніж 20 символів";
    private final String NO_SPECIAL_SYMBOLS = "Пароль повинен містити великі/маленькі літери латинського алфавіту, цифри та спеціальні символи";
    private final String CONFIRMATION_FAILED = "'Значення поля ‘Підтвердити новий пароль’ має бути еквівалентним значенню поля ‘Новий пароль’";
    private final Object[][] invalidPasswordErrors = {
//            {"1pas;","1pas;",WRONG_LENGTH, ""},
//            {"passpasspasspass","passpasspasspass",NO_SPECIAL_SYMBOLS, ""},
//            {"1Pas", "1Pas",WRONG_LENGTH, NO_SPECIAL_SYMBOLS, ""},
//            {"1;P","1;P", WRONG_LENGTH, ""},
            {"1pas;","11pas;",WRONG_LENGTH, CONFIRMATION_FAILED},
            {"passpasspasspass","11passpasspasspass",NO_SPECIAL_SYMBOLS, CONFIRMATION_FAILED},
            {"1Pas", "111Pas",WRONG_LENGTH, NO_SPECIAL_SYMBOLS, CONFIRMATION_FAILED},
            {"1;P","1111;P", WRONG_LENGTH, CONFIRMATION_FAILED}
    };
    @BeforeMethod
    public void setup(){
        softAssert= new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUp(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickManagerButton();
        editProfilePopUpComponent.clickCheckBox();
    }
    @Test(description = "TUA-848")
    public void test(){
        for (int i = 0; i < invalidPasswordErrors.length; i++) {
            InputWithIconElement currPass = editProfilePopUpComponent.getCurrentPasswordInput();
            InputWithIconElement newPass = editProfilePopUpComponent.getNewPasswordInput();
            InputWithIconElement confPass = editProfilePopUpComponent.getConfirmPasswordInput();

            String confPassTest = (String) invalidPasswordErrors[i][invalidPasswordErrors[i].length-1];
            newPass.clearInput();
            currPass.clearInput();
            confPass.clearInput();

            fillNewPasswordFieldWithInvalidDataAndVerify(currPass, configProperties.getManagerPassword(), true);
            fillNewPasswordFieldWithInvalidDataAndVerify(newPass, (String) invalidPasswordErrors[i][0], false);
            for (int j = 0; j < newPass.getErrorMessagesTextList().size(); j++) {
                softAssert.assertEquals(invalidPasswordErrors[i][j], newPass.getErrorMessagesTextList().get(j));
            }
            fillNewPasswordFieldWithInvalidDataAndVerify(confPass,(String) invalidPasswordErrors[i][1], confPassTest.isEmpty());
            System.out.println(confPassTest);

            System.out.println(Arrays.toString(confPass.getErrorMessagesTextList().toArray()));
            String confPassError = confPass.getErrorMessagesTextList().isEmpty() ? "" : confPass.getErrorMessagesTextList().getFirst();
            System.out.println(confPassError);
            softAssert.assertEquals(confPassTest, confPassError);
        }

        softAssert.assertAll();
    }

    private void fillNewPasswordFieldWithInvalidDataAndVerify(InputWithIconElement inputWithIconElement, String pwd, boolean validity){
        inputWithIconElement.getInput().sendKeys(pwd);
        wait.until(ExpectedConditions.visibilityOf(inputWithIconElement.getValidationCircleIcon()));
        wait.until(ExpectedConditions.attributeContains(inputWithIconElement.getInput(),"value",pwd));
        if (!validity) {
            softAssert.assertTrue(inputWithIconElement.getValidationCircleIcon().getCssValue("color")
                    .contains("rgba(255, 77, 79, 1)"), "The validation icon is not highlighted in red");
        } else {
            softAssert.assertTrue(inputWithIconElement.getValidationCircleIcon().getCssValue("color")
                    .contains("rgba(82, 196, 26, 1)"), "The validation icon is not highlighted in green");
        }
    }
}
