package com.academy.ui.components.AddLocationPopUpComponent;

import com.academy.ui.components.elements.BaseDropdownElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DropdownElement extends BaseDropdownElement {

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']" +
            "//span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private WebElement errorMessage;

    public DropdownElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
