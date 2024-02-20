package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BaseInputElement  extends BaseComponent {
    @FindBy(xpath = ".//input")
    protected WebElement input;
    public BaseInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public BaseInputElement setValue(String value) {
        input.sendKeys(value);
        return this;
    }
}
