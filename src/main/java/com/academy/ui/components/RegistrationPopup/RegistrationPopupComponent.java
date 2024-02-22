package com.academy.ui.components.RegistrationPopup;


import com.academy.ui.components.AddClubPopUpComponent.InputElement;
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
    protected InputElement lastNameInput;

    protected InputElement firstNameInput;

    protected InputElement phoneInput;

    protected InputElement emailInput;

    protected InputElement passwordInput;

    protected InputElement passwordConfirmationInput;

    @FindBy(xpath="//button[contains(@class, 'registration-button')]\n")
    protected WebElement registrationButton;

    public RegistrationPopupComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class, 'ant-modal-content') and contains(., 'Реєстрація')]")));
        lastNameInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='lastName' and @class='ant-input']")));
        firstNameInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='firstName' and @class='ant-input']")));
        phoneInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='phone' and @class='ant-input']")));
        emailInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='email' and @class='ant-input']")));
        passwordInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='password' and @class='ant-input']")));
        passwordConfirmationInput = new InputElement(driver, rootElement.findElement(By.xpath("./descendant::input[@id='confirm' and @class='ant-input']")));
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

