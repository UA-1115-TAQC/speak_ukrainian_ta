package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuestMenuComponent extends BaseComponent {

    @FindBy(xpath = ".//li[contains(@data-menu-id, 'register')]")
    private WebElement registration;

    @FindBy(xpath = ".//li[contains(@data-menu-id, 'login')]")
    private WebElement login;

    @FindBy(xpath = "//descendant::div[contains(@class, 'modal-login')][1]")
    private WebElement loginModalForm;

    public GuestMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LoginPopupComponent openLoginForm() {
        login.click();
        return new LoginPopupComponent(driver, loginModalForm);
    }


}
