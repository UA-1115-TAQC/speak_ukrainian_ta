package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class InputElement extends BaseComponent {

    @FindBy(xpath = "./following-sibling::span[@class='ant-input-suffix']/descendant::span[@role='img']")
    private WebElement inputIcon;
    @FindBy(xpath = "./ancestor::div[contains(@class,'ant-form-item-control')]/descendant::div[@class='ant-form-item-explain-error']")
    public WebElement inputError;

    @FindBy(xpath = ".//span[@role='img'][1]")
    public WebElement loginPopUpInputEnvelopeIcon;
    @FindBy(xpath = ".//span[@role='img'][2]")
    public WebElement loginPopUpInputEmailValidationIcon;
    @FindBy(xpath = ".//span[@role='img'][4]")
    public WebElement loginPopUpInputPasswordValidationIcon;
    @FindBy(xpath = ".//input[@id='basic_email']")
    private WebElement loginPopUpBasicEmailInput;
    @FindBy(xpath = ".//input[@id='basic_password']")
    private WebElement loginPopUpBasicPasswordInput;

    @FindBy(xpath = ".//input[@id='edit_email']")
    private WebElement restorationEditEmailInput;
    @FindBy(xpath = ".//span[@role='img'][5]")
    public WebElement restorationEnvelopIcon;
    @FindBy(xpath = ".//span[@role='img'][6]")
    public WebElement restorationEditEmailValidationIcon;

    public InputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public InputElement setInput(String value) {
        rootElement.sendKeys(value);
        return this;
    }
}
