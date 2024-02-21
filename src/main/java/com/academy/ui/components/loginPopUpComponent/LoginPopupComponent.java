package com.academy.ui.components.loginPopUpComponent;

import com.academy.ui.components.BasePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPopupComponent extends BasePopUp {

    private final InputWithIconElement passwordInputElement;
    private final InputWithIconElement emailInputElement;
    @FindBy(xpath = "./descendant::div[@class='login-header']")
    private WebElement loginPopUpTitle;
    @FindBy(xpath = "./descendant::img[contains(@src, 'google')]")
    private WebElement googleIcon;
    @FindBy(xpath = "./descendant::img[contains(@src, 'facebook')]")
    private WebElement facebookIcon;
    @FindBy(xpath = "./descendant::a[contains(@href, 'authorize/google')]")
    private WebElement authorizationByGoogle;
    @FindBy(xpath = "./descendant::a[contains(@href, 'authorize/facebook')]")
    private WebElement authorizationByFacebook;
    @FindBy(xpath = "./descendant::span[contains(@class, 'label-or')]")
    private WebElement labelOr;
    @FindBy(xpath = "./descendant::button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "./descendant::a[contains(@class, 'restore-password-button')]")
    private WebElement restorePasswordButton;
    @FindBy(xpath = "//body/descendant::div[contains(@class, 'modal-login')][2]")
    private WebElement restorePasswordPopUp;
    @FindBy(xpath = "./descendant::div[contains(@class, 'ant-form-item login-input css-13m256z')][1]")
    @Getter(AccessLevel.NONE)
    private WebElement emailInput;
    @FindBy(xpath = "./descendant::div[contains(@class, 'ant-form-item login-input css-13m256z')][2]")
    @Getter(AccessLevel.NONE)
    private WebElement passwordInput;

    public LoginPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        passwordInputElement = new InputWithIconElement(driver, passwordInput);
        emailInputElement = new InputWithIconElement(driver, emailInput);
    }

    public String getMenuHeaderText() {
        return loginPopUpTitle.getText();
    }

    public String getAuthorizationLabelTextOr() {
        return labelOr.getText();
    }

    public LoginPopupComponent clickSubmitButton() {
        submitButton.click();
        return this;
    }

    private RestorationPasswordComponent getRestorationPasswordComponent() {
        return new RestorationPasswordComponent(driver, restorePasswordPopUp);
    }

    public RestorationPasswordComponent clickRestorePasswordButton() {
        restorePasswordButton.click();
        return getRestorationPasswordComponent();
    }
}
