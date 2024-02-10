package com.academy.ui.pages.header;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPopupComponent extends BaseComponent {
    protected WebElement loginPopupHeaderText;
    protected WebElement closeLoginPopupButton;
    protected WebElement loginMenu;
    protected WebElement googleIcon;
    protected WebElement facebookIcon;
    protected WebElement authorizationByGoogle;
    protected WebElement getAuthorizationByFacebook;
    protected WebElement loginInput;
    protected WebElement passwordInput;
    protected WebElement submitButton;
    protected WebElement forgotPassword;
    protected WebElement showPasswordCircle;
    protected WebElement hidePasswordCircle;


    public LoginPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getLoginPopupMenu() {
        if (loginMenu == null) {
            loginMenu = rootElement.findElement(By.xpath("//div[contains(@class, 'right-side-menu')]"));
        }
        return loginMenu;
    }

    public  WebElement getLoginPopupHeaderText() {
        if (loginPopupHeaderText == null) {
            loginPopupHeaderText = rootElement.findElement(By.xpath("//div[@class='login-header']"));
        }
        return loginPopupHeaderText;
    }

    public WebElement getCloseLoginPopupButton() {
        if (closeLoginPopupButton == null) {
            closeLoginPopupButton = rootElement.findElement(By.xpath("//div[contains(@class, 'modal-login')]//button[@aria-label= 'Close']"));
        }
        return closeLoginPopupButton;
    }

    public WebElement getGoogleIcon() {
        if (googleIcon == null) {
            googleIcon = rootElement.findElement(By.xpath("//img[contains(@src, 'google')]"));
        }
        return googleIcon;
    }

    public WebElement getFacebookIcon() {
        if (facebookIcon == null) {
            facebookIcon = rootElement.findElement(By.xpath("//img[contains(@src, 'facebook')]"));
        }
        return facebookIcon;
    }

    public WebElement getAuthorizationByGoogle() {
        if (authorizationByGoogle == null) {
            authorizationByGoogle = rootElement.findElement(By.xpath("//a[contains(@href, 'authorize/google')]"));
        }
        return authorizationByGoogle;
    }

    public WebElement getGetAuthorizationByFacebook() {
        if (getAuthorizationByFacebook == null) {
            getAuthorizationByFacebook = rootElement.findElement(By.xpath("//a[contains(@href, 'authorize/facebook')]"));
        }
        return getAuthorizationByFacebook;
    }

    public WebElement getLoginInput() {
        if (loginInput == null) {
            loginInput = rootElement.findElement(By.xpath("//input[@id='basic_email']"));
        }
        return loginInput;
    }

    public WebElement getPasswordInput() {
        if (passwordInput == null) {
            passwordInput = rootElement.findElement(By.xpath("//input[@id='basic_password']"));
        }
        return passwordInput;
    }

    public WebElement getSubmitButton() {
        if (submitButton == null) {
            passwordInput = rootElement.findElement(By.xpath("//button[@type='submit']"));
        }
        return submitButton;
    }

    public WebElement getForgotPassword() {
        if (forgotPassword == null) {
            forgotPassword = rootElement.findElement(By.xpath("//a[contains(@class, 'restore-password-button')]"));
        }
        return forgotPassword;
    }

    public WebElement getShowPasswordCircle() {
        if (showPasswordCircle == null) {
            showPasswordCircle = rootElement.findElement(By.xpath("//span[@aria-label='eye']"));
        }
        return showPasswordCircle;
    }

    public WebElement getHidePasswordCircle() {
        if (hidePasswordCircle == null) {
            hidePasswordCircle = rootElement.findElement(By.xpath("//span[@aria-label='eye-invisible']"));
        }
        return hidePasswordCircle;
    }

    public void clickLoginPopupMenu() {
        getLoginPopupMenu().click();
    }
}
