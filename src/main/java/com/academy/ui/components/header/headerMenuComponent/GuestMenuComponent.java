package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.RegistrationPopup.RegistrationPopupComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GuestMenuComponent extends BaseComponent {

    @FindBy(xpath = ".//li[contains(@data-menu-id, 'register')]")
    private WebElement registration;

    @FindBy(xpath = ".//li[contains(@data-menu-id, 'login')]")
    private WebElement login;

    @FindBy(xpath = "//descendant::div[contains(@class, 'modal-login')][1]")
    private WebElement loginModalForm;

    @FindBy(xpath = "//div[contains(@class, 'ant-modal-content') and contains(., 'Реєстрація')]")
    private WebElement registrationForm;

    public GuestMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click login")
    public LoginPopupComponent openLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(login)).click();
        return new LoginPopupComponent(driver, loginModalForm);
    }

    public RegistrationPopupComponent openRegistrationForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registration)).click();
        return new RegistrationPopupComponent(driver, registrationForm);
    }
}
