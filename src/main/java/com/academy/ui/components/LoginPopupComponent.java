package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPopupComponent extends BasePopUp {

    private static final String CLASS_NAME = "modal-login";
    @FindBy(xpath = "//div[@class='login-header']")
    private WebElement loginPopUpTitle;
    @FindBy(xpath = "//img[contains(@src, 'google')]")
    private WebElement googleIcon;
    @FindBy(xpath = "//img[contains(@src, 'facebook')]")
    private WebElement facebookIcon;
    @FindBy(xpath = "//a[contains(@href, 'authorize/google')]")
    private WebElement authorizationByGoogle;
    @FindBy(xpath = "//span[contains(@class, 'label-or')]")
    private WebElement titleOr;
    @FindBy(xpath = "//label[@title='Емейл']")
    private WebElement emailTittle;
    @FindBy(xpath = "//label[@title='Пароль']")
    private WebElement passwordTittle;
    @FindBy(xpath = "//a[contains(@href, 'authorize/facebook')]")
    private WebElement getAuthorizationByFacebook;
    @FindBy(xpath = "//input[@id='basic_email']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@id='basic_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//a[contains(@class, 'restore-password-button')]")
    private WebElement forgotPassword;
    @FindBy(xpath = "//span[@aria-label='eye']")
    private WebElement showPassword;
    @FindBy(xpath = "//span[@aria-label='eye-invisible']")
    private WebElement hidePassword;
    private WebElement iconSuccessInput;
    private WebElement iconErrorInput;
    private final String EMAIL_Pattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public LoginPopupComponent(WebDriver driver) {
        super(driver, CLASS_NAME);
    }

    public WebElement getSuccessIconForEmail() {
        if (iconSuccessInput == null) {
            iconSuccessInput = rootElement.findElement(By.xpath("//div[contains(@id, 'basic_email')]/descendant::span[@aria-label='check-circle']"));
        }
        return iconSuccessInput;
    }

    public WebElement getErrorIconForEmail() {
        if (iconErrorInput == null) {
            iconErrorInput = rootElement.findElement(By.xpath("//div[contains(@id, 'basic_email')]/descendant::span[@aria-label='close-circle']"));
        }
        return iconErrorInput;
    }

    public WebElement getEmailStatusIcon(String email) {
        loginInput.sendKeys(email);
        if (isValidEmail(email)) {
            return getSuccessIconForEmail();
        }
        return getErrorIconForEmail();
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_Pattern);
    }
}
