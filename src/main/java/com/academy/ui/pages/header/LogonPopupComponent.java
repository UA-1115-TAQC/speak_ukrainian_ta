package com.academy.ui.pages.header;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogonPopupComponent extends BaseComponent {
    protected WebElement logonPopupHeaderText;
    protected WebElement closeLogonPopupButton;
    protected WebElement logonMenu;
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


    public LogonPopupComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getLogonPopupMenu() {
        if (logonMenu == null) {
            logonMenu = rootElement.findElement(By.xpath("//div[contains(@class, 'right-side-menu')]"));
        }
        return logonMenu;
    }

    public  WebElement getLogonPopupHeaderText() {
        if (logonPopupHeaderText == null) {
            logonPopupHeaderText = rootElement.findElement(By.xpath("//div[@class='login-header']"));
        }
        return logonPopupHeaderText;
    }

    public WebElement getCloseLogonPopupButton() {
        if (closeLogonPopupButton == null) {
            closeLogonPopupButton = rootElement.findElement(By.xpath("//button[contains(@aria-label, 'Close')]"));
        }
        return closeLogonPopupButton;
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

    public void clickLogonPopupMenu() {
        getLogonPopupMenu().click();
    }
}
