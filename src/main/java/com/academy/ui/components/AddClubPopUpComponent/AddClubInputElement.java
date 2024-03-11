package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.elements.BaseInputElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddClubInputElement extends BaseInputElement {

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[@class='ant-input-suffix']/div[@class='icon']")
    private WebElement staticIcon;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;

    public AddClubInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get list of error messages of input on the Add/Edit club pop-up")
    public List<String> getErrorMessagesTextList() {
        return errorMessages.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }
}