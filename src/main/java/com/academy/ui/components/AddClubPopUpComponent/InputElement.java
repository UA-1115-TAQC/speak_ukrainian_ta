package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InputElement extends BaseComponent {

    @FindBy(xpath = "./following-sibling::span[@class='ant-input-suffix']/descendant::span[contains(@class,'anticon') and contains(@class,'circle')]")
    private WebElement validationIcon;

    @FindBy(xpath = "./following-sibling::span[@class='ant-input-suffix']/descendant::div[@class='icon']")
    private WebElement infoIcon;

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
