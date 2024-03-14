package com.academy.ui.components.loginPopUpComponent;

import com.academy.ui.components.BasePopUp;
import com.academy.ui.components.elements.InputWithIconElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RestorationPasswordComponent extends BasePopUp {

    private final InputWithIconElement emailInputElement;
    @FindBy(xpath = "./descendant::div[contains(@class, 'login-header')]")
    private WebElement restorationTittle;
    @FindBy(xpath = "./descendant::button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "./descendant::button[@type='button']")
    private WebElement closeButton;
    @FindBy(xpath = "./descendant::div[contains(@class, 'ant-form-item login-input css-13m256z')][1]")
    @Getter(AccessLevel.NONE)
    private WebElement emailInput;

    public RestorationPasswordComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        emailInputElement = new InputWithIconElement(driver, emailInput);
    }

    @Step("Get title text of restoration form")
    public String getMenuHeader() {
        return restorationTittle.getText();
    }

    @Step("Click submit button")
    public RestorationPasswordComponent clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click close button for closing restoration password form")
    public RestorationPasswordComponent clickCloseButton() {
        closeButton.click();
        return this;
    }
}
