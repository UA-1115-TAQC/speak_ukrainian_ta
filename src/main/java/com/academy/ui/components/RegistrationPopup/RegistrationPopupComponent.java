package com.academy.ui.components.RegistrationPopup;

import com.academy.ui.components.BasePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import lombok.Getter;
import org.openqa.selenium.By;
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
    protected InputWithIconElement lastNameInput;

    protected InputWithIconElement firstNameInput;

    protected InputWithIconElement phoneInput;

    protected InputWithIconElement emailInput;

    protected InputWithIconElement passwordInput;

    protected InputWithIconElement passwordConfirmationInput;

    @FindBy(xpath="//button[contains(@class, 'registration-button')]\n")
    protected WebElement registrationButton;

    public RegistrationPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        lastNameInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath(".//div[contains(@class, 'ant-form-item registration-input')][1]")));
        firstNameInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='firstName' and @class='ant-input']")));
        phoneInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='phone' and @class='ant-input']")));
        emailInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='email' and @class='ant-input']")));
        passwordInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='password' and @class='ant-input']")));
        passwordConfirmationInput = new InputWithIconElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='confirm' and @class='ant-input']")));
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
