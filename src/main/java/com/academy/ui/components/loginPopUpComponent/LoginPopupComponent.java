package com.academy.ui.components.loginPopUpComponent;

import com.academy.ui.components.AddClubPopUpComponent.InputElement;
import com.academy.ui.components.BasePopUp;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPopupComponent extends BasePopUp {

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
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//a[contains(@class, 'restore-password-button')]")
    private WebElement forgotPassword;
    @FindBy(xpath = "//span[@aria-label='eye']")
    private WebElement showPassword;
    @FindBy(xpath = "//span[@aria-label='eye-invisible']")
    private WebElement hidePassword;
    @FindBy(xpath = "//body/descendant::div[contains(@class, 'modal-login')][2]")
    private WebElement innerLoginMenu;
    @FindBy(xpath = "./descendant::div[@class='ant-form-item-control-input']")
    private WebElement itemInputForm;
    private InputElement inputElement;

    public LoginPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputElement = new InputElement(driver, itemInputForm);
    }

    public RestorationPasswordComponent getRestorationPasswordComponent() {
        return new RestorationPasswordComponent(driver, innerLoginMenu);
    }
}
