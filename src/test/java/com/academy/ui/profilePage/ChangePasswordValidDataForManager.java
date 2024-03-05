package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.randomvaluesgenerators.RandomPasswordGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

//change to LoginWithManagerTestRunner
public class ChangePasswordValidDataForManager extends LoginWithManagerTestRunner{
    private EditProfilePopUp editProfilePopUpComponent;
    SoftAssert softAssert;
    private String newPassword;
    protected RandomPasswordGenerator passwordGenerator;
    private  String validPassword8Characters="Qwert_12";
    private  String validPassword20Characters="123456789012345678i_";
    WebDriverWait wait;
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
    @Test(description = "TUA-849")
    public void test(){
        fillNewPasswordFieldWithValidDataAndVerify(editProfilePopUpComponent.getNewPasswordInput(),validPassword8Characters);
        clearInputField(editProfilePopUpComponent.getNewPasswordInput().getInput());
        fillNewPasswordFieldWithValidDataAndVerify(editProfilePopUpComponent.getNewPasswordInput(),validPassword20Characters);
        clearInputField(editProfilePopUpComponent.getNewPasswordInput().getInput());
        editProfilePopUpComponent.getCurrentPasswordInput().getInput().sendKeys(configProperties.getManagerPassword());
        passwordGenerator = new RandomPasswordGenerator();
        newPassword = passwordGenerator.generateRandomPassword();
        fillNewPasswordFieldWithValidDataAndVerify(editProfilePopUpComponent.getCurrentPasswordInput(), configProperties.getManagerPassword());
        fillNewPasswordFieldWithValidDataAndVerify(editProfilePopUpComponent.getNewPasswordInput(),newPassword);
        fillNewPasswordFieldWithValidDataAndVerify(editProfilePopUpComponent.getConfirmPasswordInput(),newPassword);
        editProfilePopUpComponent.clickSubmitButton();
        //verify that password is changed - appearing window
        System.out.println(newPassword);
        configProperties.setManagerPassword(newPassword); // why isn't it is set??

        //+ Verify whether user's password is changed in the DB - it seems to me that there is a bug - the pwd remains the same
        softAssert.assertAll();
    }
    private void clearInputField(WebElement inputField){
        inputField.sendKeys(Keys.COMMAND + "a");
        inputField.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputField, ""));
    }
    private void fillNewPasswordFieldWithValidDataAndVerify(InputWithIconElement inputWithIconElement, String pwd){
        inputWithIconElement.getInput().sendKeys(pwd);
        wait.until(ExpectedConditions.visibilityOf(inputWithIconElement.getValidationCircleIcon()));
        wait.until(ExpectedConditions.attributeContains(inputWithIconElement.getInput(),"value",pwd));
        softAssert.assertTrue(inputWithIconElement.getValidationCircleIcon().getCssValue("color")
                .contains("rgba(82, 196, 26, 1)"), "The validation icon is not highlighted in green");
    }
}
