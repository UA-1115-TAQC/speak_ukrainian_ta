package com.academy.ui.components.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPopupElement extends BaseInputElement {
    @FindBy(xpath = "./descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;
    @FindBy(xpath = "./descendant::span[@class='ant-input-suffix']/span[@aria-label='mail']")
    private WebElement staticIcon;
    @FindBy(xpath = "./descendant::span[@class='ant-input-suffix']/span[contains(@aria-label, 'eye-invisible') or contains(@aria-label, 'eye')]")
    private WebElement passwordVisibilityIcon;
    @FindBy(xpath = "./descendant::label[@title='Емейл']")
    private WebElement emailTittle;
    @FindBy(xpath = "./descendant::label[@title='Пароль']")
    private WebElement passwordTittle;

    public LoginPopupElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LoginPopupElement clickPasswordVisibilityIcon() {
        passwordVisibilityIcon.click();
        return this;
    }

    public String getEmailText() {
        return emailTittle.getText();
    }

    public String getPasswordText() {
        return passwordTittle.getText();
    }
}
