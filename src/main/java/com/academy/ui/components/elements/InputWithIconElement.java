package com.academy.ui.components.elements;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InputWithIconElement extends BaseInputElement {
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
    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;
    @FindBy(xpath = ".//span[contains(@class, 'registration-box')]")
    private WebElement wholeInputElement;

    public InputWithIconElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click password visibility icon")
    public InputWithIconElement clickPasswordVisibilityIcon() {
        passwordVisibilityIcon.click();
        return this;
    }

    @Step("Get email title text")
    public String getEmailText() {
        return emailTittle.getText();
    }

    @Step("Get password title text")
    public String getPasswordText() {
        return passwordTittle.getText();
    }

    @Step("Get list of error messages")
    public List<String> getErrorMessagesTextList() {
        return errorMessages.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }
}
