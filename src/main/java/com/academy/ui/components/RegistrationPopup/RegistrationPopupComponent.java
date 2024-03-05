package com.academy.ui.components.RegistrationPopup;

import com.academy.ui.components.BasePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RegistrationPopupComponent extends BasePopUp {

    protected static final String registrationPopupClassName = "modal-registration";
    @FindBy(xpath="//div[contains(@class,'registration-header')]")
    protected WebElement registrationHeader;
    @FindBy(xpath="//input[@value='ROLE_USER']/../..")
    protected WebElement userTypeButton;
    @FindBy(xpath="//input[@value='ROLE_MANAGER']/../..")
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

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][1]")
    protected WebElement lastNameWebElement;

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][2]")
    protected WebElement firstNameWebElement;

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][3]")
    protected WebElement phoneWebElement;

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][4]")
    protected WebElement emailWebElement;

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][5]")
    protected WebElement passwordWebElement;

    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item registration-input')][6]")
    protected WebElement passwordConfirmationWebElement;

    protected InputWithIconElement lastNameInput;

    protected InputWithIconElement firstNameInput;

    protected InputWithIconElement phoneInput;

    protected InputWithIconElement emailInput;

    protected InputWithIconElement passwordInput;

    protected InputWithIconElement passwordConfirmationInput;

    @FindBy(xpath=".//button[contains(@class, 'registration-button')]")
    protected WebElement registrationButton;

    public RegistrationPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        lastNameInput = new InputWithIconElement(driver, lastNameWebElement);
        firstNameInput = new InputWithIconElement(driver, firstNameWebElement);
        phoneInput = new InputWithIconElement(driver, phoneWebElement);
        emailInput = new InputWithIconElement(driver, emailWebElement);
        passwordInput = new InputWithIconElement(driver, passwordWebElement);
        passwordConfirmationInput = new InputWithIconElement(driver, passwordConfirmationWebElement);
    }
    public String getRegistrationHeaderText() {
        return registrationHeader.getAttribute("innerText");
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

    public InputWithIconElement getFirstNameInput() {
        return firstNameInput;
    }

    public InputWithIconElement getLastNameInput() {
        return lastNameInput;
    }

    public InputWithIconElement getPhoneInput() {
        return phoneInput;
    }

    public InputWithIconElement getPasswordConfirmationInput() {
        return passwordConfirmationInput;
    }

    public InputWithIconElement getPasswordInput() {
        return passwordInput;
    }

    public InputWithIconElement getEmailInput() {
        return emailInput;
    }

    public WebElement getRegistrationButton() {
        return registrationButton;
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
