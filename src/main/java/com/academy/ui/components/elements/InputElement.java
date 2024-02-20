package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InputElement extends BaseComponent {

    @FindBy(xpath = "./ancestor::div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;

    @FindBy(xpath = "./ancestor::div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-check-circle')]")
    private WebElement infoCircleHintIcon;

    @FindBy(xpath = "./ancestor::div[@class='ant-form-item-control-input']/descendant::span[@class='ant-input-suffix']/div[@class='icon']")
    private WebElement staticIcon;

    @FindBy(xpath = "./ancestor::div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;

    public InputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public InputElement setValue(String value) {
        rootElement.sendKeys(value);
        return this;
    }

    public List<String> getErrorMessagesTextList() {
        return errorMessages.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }
}
