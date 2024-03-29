package com.academy.ui.profilePage;


import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class PasswordFieldTestWithLoggedInUser extends LogInWithUserTestRunner {
    private EditProfilePopUp editProfilePopUpComponent;
    private final String TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR ="Пароль не може бути коротшим, ніж 8 та довшим, ніж 20 символів";
    private final String THE_NEW_PASSWORD_CANT_BE_THE_SAME_AS_EXISTING_ERROR="Значення поля ‘Новий пароль’ має відрізнятися від значення поля ‘Старий пароль’";
    private SoftAssert softAssert;
    private WebDriverWait wait;
    @BeforeMethod
    public void setup(){
        softAssert= new SoftAssert();
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUp(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickUserButton();
        editProfilePopUpComponent.clickCheckBox();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    @Test (description = "TUA-154")
    @Description("Check if the error message appears after user as 'Відвідувач' inputs " +
            "invalid data in the ‘New password’ field")
    @Tag("YuUk")
    @Issue("TUA-154")
    public void checkErrorMessageAfterUserInputsInvalidPassword(){
        checkThatAllFieldsAreFilledWithTheSameDataAsDuringRegistration();
        WebElement inputField = editProfilePopUpComponent.getNewPasswordInput().getInput();
       enterInvalidNewPasswordAndVerifyField("qwer", inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        enterInvalidNewPasswordAndVerifyField("qwertyu",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        enterInvalidNewPasswordAndVerifyField("123456789101234567899",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        enterInvalidNewPasswordAndVerifyField("123456789123456789123",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        editProfilePopUpComponent.getCurrentPasswordElement().setValue(configProperties.getUserPassword());
        enterInvalidNewPasswordAndVerifyField(configProperties.getUserPassword(), inputField,THE_NEW_PASSWORD_CANT_BE_THE_SAME_AS_EXISTING_ERROR);
        softAssert.assertAll();
    }
    private void clearInputField(WebElement inputField){
        inputField.sendKeys(Keys.COMMAND + "a");
        inputField.sendKeys(Keys.DELETE);
    }
    private void checkThatAllFieldsAreFilledWithTheSameDataAsDuringRegistration(){
        softAssert.assertEquals(editProfilePopUpComponent.getLastNameElement().getInput().getAttribute("value"), configProperties.getUserLastname(),
                "The shown last name doesn't match the last name, which was entered by a user during registration");
        softAssert.assertEquals(editProfilePopUpComponent.getFirstNameElement().getInput().getAttribute("value"), configProperties.getUserFirstname(),
                "The shown first name doesn't match the last name, which was entered by a user during registration");
        softAssert.assertEquals(editProfilePopUpComponent.getPhoneElement().getInput().getAttribute("value"), configProperties.getUserPhone(),
                "The shown phone doesn't match the last name, which was entered by a user during registration");
        softAssert.assertEquals(editProfilePopUpComponent.getEmailElement().getInput().getAttribute("value"), configProperties.getUserEmail(),
                "The shown email doesn't match the email, which was entered by a user during registration");
    }

    private void enterInvalidNewPasswordAndVerifyField(String pwd, WebElement inputField, String errorMessage){
        inputField.sendKeys(pwd);
        wait.until(driver1 -> inputField.getAttribute("value").contains(pwd));
        boolean HasMessageFlag =false;
        for(WebElement error:editProfilePopUpComponent.getErrorMessagesForChangingPassword()){
            if(error.getText().equals(errorMessage)){
                HasMessageFlag =true;
            }
        }
        softAssert.assertTrue(HasMessageFlag, "The corresponding error message " + errorMessage + " isn't displayed");
    }
}
