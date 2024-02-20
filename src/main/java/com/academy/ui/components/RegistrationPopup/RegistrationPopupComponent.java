package com.academy.ui.components.RegistrationPopup;


import com.academy.ui.components.BasePopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPopupComponent extends BasePopUp {

    protected static final String registrationPopupClassName = "modal-registration";
    @FindBy(xpath="//div[contains(@class,'registration-header')]")
    protected WebElement registrationHeader;
    @FindBy(xpath="//label[@class='ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item css-13m256z']//input[@value='ROLE_USER']")
    protected WebElement userTypeButton;
    @FindBy(xpath="//label[@class='ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item css-13m256z']//input[@value='ROLE_MANAGER']")
    protected WebElement managerTypeButton;
    @FindBy(xpath="//a[contains(@href, 'google')]")
    protected WebElement googleButton;
    @FindBy(xpath="//a[contains(@href, 'facebook')]")
    protected WebElement facebookButton;
    @FindBy(xpath="//label[@for='lastName' and @class='ant-form-item-required']")
    protected WebElement lastNameHeader;
    @FindBy(xpath="//label[@for='firstName' and @class='ant-form-item-required']")
    protected WebElement firstNameHeader;
    @FindBy(xpath="//label[@for='phone' and @class='ant-form-item-required']")
    protected WebElement phoneHeader;
    @FindBy(xpath="//label[@for='email' and @class='ant-form-item-required']")
    protected WebElement emailHeader;
    @FindBy(xpath="//label[@for='password' and @class='ant-form-item-required']")
    protected WebElement passwordHeader;
    @FindBy(xpath="//label[@for='confirm' and @class='ant-form-item-required']")
    protected WebElement passwordConfirmationHeader;
    @FindBy(xpath="//input[@id='lastName' and @class='ant-input']")
    protected WebElement lastNameInput;
    @FindBy(xpath="//input[@id='firstName' and @class='ant-input']")
    protected WebElement firstNameInput;
    @FindBy(xpath="//input[@id='phone' and @class='ant-input']")
    protected WebElement phoneInput;
    @FindBy(xpath="//input[@id='email' and @class='ant-input']")
    protected WebElement emailInput;
    @FindBy(xpath="//input[@id='password' and @class='ant-input']")
    protected WebElement passwordInput;
    @FindBy(xpath="//input[@id='confirm' and @class='ant-input']")
    protected WebElement passwordConfirmationInput;

    @FindBy(xpath="//div[@id='lastName_help']/div[@class='ant-form-item-explain-error']")
    protected WebElement lastNameInputError;
    @FindBy(xpath="//div[@id='firstName_help']/div[@class='ant-form-item-explain-error']")
    protected WebElement firstNameInputError;
    @FindBy(xpath="//div[@id='phone_help']/div[@class='ant-form-item-explain-error']")
    protected List<WebElement> phoneInputError;
    @FindBy(xpath="//div[@id='email_help']/div[@class='ant-form-item-explain-error']")
    protected WebElement emailInputError;
    @FindBy(xpath="//div[@id='password_help']/div[@class='ant-form-item-explain-error']")
    protected List<WebElement> passwordInputError;
    @FindBy(xpath="//div[@id='confirm_help']/div[@class='ant-form-item-explain-error']")
    protected WebElement passwordConfirmationInputError;
    @FindBy(xpath="//button[contains(@class, 'registration-button')]\n")
    protected WebElement registrationButton;

    public RegistrationPopupComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class, 'ant-modal-content')]")));
    }
    public String getRegistrationHeaderText() {
        return registrationHeader.getAttribute("innerText");
    }
    public RegistrationPopupComponent setLastNameInput(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }
    public RegistrationPopupComponent setFirstNameInput(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }
    public RegistrationPopupComponent setEmailInput(String email) { emailInput.sendKeys(email); return this;}
    public RegistrationPopupComponent setPhoneInput(String phone) { phoneInput.sendKeys(phone); return this;}
    public RegistrationPopupComponent setPasswordInput(String password) { passwordInput.sendKeys(password); return this;}
    public RegistrationPopupComponent setPasswordConfirmationInput(String phoneConfirmation) { passwordConfirmationInput.sendKeys(phoneConfirmation); return this;}

    public String getLastNameErrorText() {
        return lastNameInputError != null ? lastNameInputError.getAttribute("innerText") : null;
    }

    public String getFirstNameErrorText() {
        return firstNameInputError != null ? firstNameInputError.getAttribute("innerText") : null;
    }

    public List<String> getPhoneErrorText() {
        List<String> errors = new ArrayList<>();
        if (phoneInputError != null) {
            phoneInputError.forEach(error -> errors.add(error.getAttribute("innerText")));
        }
        return errors;
    }

    public String getEmailErrorText() {
        return emailInputError != null ? emailInputError.getAttribute("innerText") : null;
    }

    public List<String> getPasswordErrorText() {
        List<String> errors = new ArrayList<>();
        if (passwordInputError != null) {
            passwordInputError.forEach(error -> errors.add(error.getAttribute("innerText")));
        }
        return errors;
    }

    public void selectUserType(String userType) {
        if (userType.equalsIgnoreCase("ROLE_USER")) {
            userTypeButton.click();
        } else if (userType.equalsIgnoreCase("ROLE_MANAGER")) {
            managerTypeButton.click();
        } else {
            // Handle invalid userType
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public RegistrationPopupComponent clickSetUserButton() {
        userTypeButton.click();
        return this;
    }

    public RegistrationPopupComponent clickSetManagerButton() {
        managerTypeButton.click();
        return this;
    }

    public RegistrationPopupComponent clickRegisterButton() {
        registrationButton.click();
        return this;
    }

}

