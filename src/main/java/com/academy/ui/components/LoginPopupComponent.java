package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPopupComponent extends BasePopUp {

    private static final String CLASS_NAME = "modal-login";
    @FindBy(xpath = "//div[@class='login-header']")
    private WebElement loginPopupHeaderText;
    @FindBy(xpath = "//img[contains(@src, 'google')]")
    private WebElement googleIcon;
    @FindBy(xpath = "//img[contains(@src, 'facebook')]")
    private WebElement facebookIcon;
    @FindBy(xpath = "//a[contains(@href, 'authorize/google')]")
    private WebElement authorizationByGoogle;
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
    private WebElement showPasswordCircle;
    @FindBy(xpath = "//span[@aria-label='eye-invisible']")
    private WebElement hidePasswordCircle;

    public LoginPopupComponent(WebDriver driver) {
        super(driver, CLASS_NAME);
    }
}
