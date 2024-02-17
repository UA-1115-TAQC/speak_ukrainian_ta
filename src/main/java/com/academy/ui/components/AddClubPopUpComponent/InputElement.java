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

    public InputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public InputElement setInput(String value) {
        rootElement.sendKeys(value);
        return this;
    }

}
