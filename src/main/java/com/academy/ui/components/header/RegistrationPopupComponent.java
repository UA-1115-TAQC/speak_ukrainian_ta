package com.academy.ui.components.header;

import com.academy.ui.components.BasePopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPopupComponent extends BasePopUp {

    protected WebElement registrationHeader;
    protected WebElement userTypeButton;
    protected WebElement managerTypeButton;
    protected WebElement googleButton;
    protected WebElement facebookButton;
    protected WebElement lastNameHeader;
    protected WebElement firstNameHeader;
    protected WebElement phoneHeader;
    protected WebElement emailHeader;
    protected WebElement passwordHeader;
    protected WebElement passwordConfirmationHeader;
    protected WebElement lastNameInput;
    protected WebElement firstNameInput;
    protected WebElement phoneInput;
    protected WebElement emailInput;
    protected WebElement passwordInput;
    protected WebElement passwordConfirmationInput;

    protected WebElement lastNameInputError;
    protected WebElement firstNameInputError;
    protected List<WebElement> phoneInputError;
    protected WebElement emailInputError;
    protected List<WebElement> passwordInputError;
    protected WebElement passwordConfirmationInputError;
    protected WebElement registrationButton;

    public RegistrationPopupComponent(WebDriver driver, String CssClasName) {
        super(driver, CssClasName);
    }

    public WebElement getRegistrationHeader() {
        if (registrationHeader == null) {
            registrationHeader = driver.findElement(By.xpath("//div[contains(@class,'registration-header')]"));
        }
        return registrationHeader;
    }

    public WebElement getUserTypeButton() {
        String xpathExpression = "//label[@class='ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item css-13m256z']//input[@value='ROLE_USER']";

        if (userTypeButton == null) {
            userTypeButton = driver.findElement(By.xpath(xpathExpression));
        }
        return userTypeButton;
    }

    public WebElement getManagerTypeButton() {
        String xpathExpression = "//label[@class='ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item css-13m256z']//input[@value='ROLE_MANAGER']";

        if (managerTypeButton == null) {
            managerTypeButton = driver.findElement(By.xpath(xpathExpression));
        }
        return managerTypeButton;
    }

    public WebElement getGoogleButton() {

        String xpathExpression = "//a[contains(@href, 'google')]";

        if (googleButton == null) {
            googleButton = driver.findElement(By.xpath(xpathExpression));
        }
        return googleButton;
    }

    public WebElement getFacebookButton() {

        String xpathExpression = "//a[contains(@href, 'facebook')]";

        if (facebookButton == null) {
            facebookButton = driver.findElement(By.xpath(xpathExpression));
        }
        return facebookButton;
    }

    public WebElement getLastNameHeader() {

        String xpathExpression = "//label[@for='lastName' and @class='ant-form-item-required']";

        if (lastNameHeader == null) {
            lastNameHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return lastNameHeader;
    }

    public WebElement getFirstNameHeader() {
        String xpathExpression = "//label[@for='firstName' and @class='ant-form-item-required']";

        if (firstNameHeader == null) {
            firstNameHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return firstNameHeader;
    }

    public WebElement getPhoneHeader() {
        String xpathExpression = "//label[@for='phone' and @class='ant-form-item-required']";

        if (phoneHeader == null) {
            phoneHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return phoneHeader;
    }

    public WebElement getEmailHeader() {
        String xpathExpression = "//label[@for='email' and @class='ant-form-item-required']";

        if (emailHeader == null) {
            emailHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return emailHeader;
    }

    public WebElement getPasswordHeader() {
        String xpathExpression = "//label[@for='password' and @class='ant-form-item-required']";

        if (passwordHeader == null) {
            passwordHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return passwordHeader;
    }

    public WebElement getPasswordConfirmationHeader() {
        String xpathExpression = "//label[@for='confirm' and @class='ant-form-item-required']";

        if (passwordConfirmationHeader == null) {
            passwordConfirmationHeader = driver.findElement(By.xpath(xpathExpression));
        }
        return passwordConfirmationHeader;
    }

    public String getRegistrationHeaderText() {
        return getRegistrationHeader().getAttribute("innerText");
    }

    public WebElement getLastNameInput() {
        String xpath = "//input[@id='lastName' and @class='ant-input']";

        if (lastNameInput == null) {
            lastNameInput = driver.findElement(By.xpath(xpath));
        }
        return lastNameInput;
    }

    public void setLastNameInput(String lastName) {
        getLastNameInput().sendKeys(lastName);
    }

    public WebElement getFirstNameInput() {
        String xpath = "//input[@id='firstName' and @class='ant-input']";

        if (firstNameInput == null) {
            firstNameInput = driver.findElement(By.xpath(xpath));
        }
        return firstNameInput;
    }

    public void setFirstNameInput(String firstName) {
        getFirstNameInput().sendKeys(firstName);
    }

    public WebElement getPhoneInput() {
        String xpath = "//input[@id='phone' and @class='ant-input']";

        if (phoneInput == null) {
            phoneInput = driver.findElement(By.xpath(xpath));
        }
        return phoneInput;
    }

    public void setPhoneInput(String phone) {
        getPhoneInput().sendKeys(phone);
    }

    public WebElement getEmailInput() {
        String xpath = "//input[@id='email' and @class='ant-input']";

        if (emailInput == null) {
            emailInput = driver.findElement(By.xpath(xpath));
        }
        return emailInput;
    }

    public void setEmailInput(String email) {
        getEmailInput().sendKeys(email);
    }

    public WebElement getPasswordInput() {
        String xpath = "//input[@id='password' and @class='ant-input']";

        if (passwordInput == null) {
            passwordInput = driver.findElement(By.xpath(xpath));
        }
        return passwordInput;
    }

    public void setPasswordInput(String password) {
        getPasswordInput().sendKeys(password);
    }

    public WebElement getPasswordConfirmationInput() {
        String xpath = "//input[@id='confirm' and @class='ant-input']";

        if (passwordConfirmationInput == null) {
            passwordConfirmationInput = driver.findElement(By.xpath(xpath));
        }
        return passwordConfirmationInput;
    }

    public void setPasswordConfirmationInput(String phoneConfirmation) {
        getPhoneInput().sendKeys(phoneConfirmation);
    }

    public WebElement getLastNameInputError() {
        String xpath = "//div[@id='lastName_help']/div[@class='ant-form-item-explain-error']";
        if (lastNameInputError == null) {
            lastNameInputError = driver.findElement(By.xpath(xpath));
        }
        return lastNameInputError;
    }

    public WebElement getFirstNameInputError() {
        String xpath = "//div[@id='firstName_help']/div[@class='ant-form-item-explain-error']";
        if (firstNameInputError == null) {
            firstNameInputError = driver.findElement(By.xpath(xpath));
        }
        return firstNameInputError;
    }

    public List<WebElement> getPhoneInputError() {
        String xpath = "//div[@id='phone_help']/div[@class='ant-form-item-explain-error']";
        if (phoneInputError == null) {
            phoneInputError = driver.findElements(By.xpath(xpath));
        }
        return phoneInputError;
    }

    public WebElement getEmailInputError() {
        String xpath = "//div[@id='email_help']/div[@class='ant-form-item-explain-error']";
        if (emailInputError == null) {
            emailInputError = driver.findElement(By.xpath(xpath));
        }
        return emailInputError;
    }

    public List<WebElement> getPasswordInputError() {
        String xpath = "//div[@id='password_help']/div[@class='ant-form-item-explain-error']";
        if (passwordInputError == null) {
            passwordInputError = driver.findElements(By.xpath(xpath));
        }
        return passwordInputError;
    }

    public WebElement getPasswordConfirmationInputError() {
        String xpath = "//div[@id='confirm_help']/div[@class='ant-form-item-explain-error']";
        if (passwordConfirmationInputError == null) {
            passwordConfirmationInputError = driver.findElement(By.xpath(xpath));
        }
        return passwordConfirmationInputError;
    }

    public String getFirstNameInputValue() {
        return getFirstNameInput().getAttribute("value");
    }

    public String getLastNameInputValue() {
        return getLastNameInput().getAttribute("value");
    }

    public String getPhoneInputValue() {
        return getPhoneInput().getAttribute("value");
    }

    public String getEmailInputValue() {
        return getEmailInput().getAttribute("value");
    }

    public String getPasswordInputValue() {
        return getPasswordInput().getAttribute("value");
    }

    public String getPasswordConfirmationInputValue() {
        return getPasswordConfirmationInput().getAttribute("value");
    }

    public String getLastNameErrorText() {
        return getLastNameInputError().getAttribute("innerText");
    }

    public String getFirstNameErrorText() {
        return getFirstNameInputError().getAttribute("innerText");
    }

    public List<String> getPhoneErrorText() {
        List<String> errors = new ArrayList<>();
        getPhoneInputError().forEach(error -> errors.add(error.getAttribute("innerText")));
        return errors;
    }

    public String getEmailErrorText() {
        return getEmailInputError().getAttribute("innerText");
    }

    public List<String> getPasswordErrorText() {
        List<String> errors = new ArrayList<>();
        getPasswordInputError().forEach(error -> errors.add(error.getAttribute("innerText")));
        return errors;
    }

    public WebElement getRegistrationButton() {
        return registrationButton;
    }

    public void clickSetUserButton() {
        getUserTypeButton().click();
    }

    public void clickSetManagerButton() {
        getManagerTypeButton().click();
    }

    public void clickRegisterButton() {
        getRegistrationButton().click();
    }

}
