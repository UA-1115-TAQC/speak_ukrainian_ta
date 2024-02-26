package com.academy.ui.components.AddLocationPopUpComponent;

import com.academy.ui.components.elements.BaseInputElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddLocationInputElement extends BaseInputElement {

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-check-circle')]")
    private WebElement infoCircleHintIcon;

    @FindBy(xpath = "//div[contains(@class, 'ant-tooltip ') and not(contains(@class, 'ant-tooltip-hidden'))]//div[@class='ant-tooltip-inner']")
    private WebElement infoHintTooltip;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;

    public AddLocationInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<String> getErrorMessagesTextList() {
        return errorMessages.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }
}
