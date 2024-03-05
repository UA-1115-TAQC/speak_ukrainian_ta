package com.academy.ui.profilePage;


import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.runners.LogInWithUserTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PasswordFieldTestWithLoggedInUser extends LogInWithUserTestRunner {
    private EditProfilePopUp editProfilePopUpComponent;
    private final String TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR ="Пароль не може бути коротшим, ніж 8 та довшим, ніж 20 символів";
    private final String THE_NEW_PASSWORD_CANT_BE_THE_SAME_AS_EXISTING_ERROR="Новий пароль не може бути таким самим як попередній";
    SoftAssert softAssert;
    @BeforeMethod (description = "TUA-154")
    public void setup(){
        softAssert= new SoftAssert();
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUp(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickUserButton();
        editProfilePopUpComponent.clickCheckBox();
    }
    @Test
    public void test(){
        checkThatAllFieldsAreFilledWithTheSameDataAsDuringRegistration();
        WebElement inputField = driver.findElement(By.xpath("//input[@id=\"edit_password\"]"));
       enterInvalidNewPasswordAndVerifyField("qwer", inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        //clear
        //  editProfilePopUpComponent.getEnterNewPasswordInput().clearInput(); doesn't work on mac - mac versions are obsolete in mac enum (no mac ventura)

        //for the 2nd time - wrong field - wrong selector on page - better via id
        enterInvalidNewPasswordAndVerifyField("qwertyu",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        enterInvalidNewPasswordAndVerifyField("123456789101234567899",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
        enterInvalidNewPasswordAndVerifyField("123456789123456789123",inputField,TOO_SHORT_OR_TOO_LONG_PASSWORD_ERROR);
        clearInputField(inputField);
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

        //assert email !!! - now unaccessible
       // softAssert.assertEquals(editProfilePopUpComponent.getEmailTitle().getAttribute("value"), configProperties.getUserEmail());
    }
    private void enterInvalidNewPasswordAndVerifyField(String pwd, WebElement inputField, String errorMessage){

        inputField.sendKeys(pwd);
       // editProfilePopUpComponent.enterNewPassword(pwd); - use this method later

        //fix
       List<WebElement> errorMessages= driver.findElements(By.xpath("//div[contains(@class,\"explain-error\")]"));
       boolean HasMessageFlag =false;
        for(WebElement error:errorMessages){
            if(!error.getText().equals(errorMessage)){
                continue;
            }else{
                HasMessageFlag =true;
            }
        }
        softAssert.assertTrue(HasMessageFlag, "The corresponding error message " + errorMessage + "isn't displayed");
    }
}
