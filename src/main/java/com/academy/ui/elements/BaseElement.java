package com.academy.ui.elements;

import com.academy.ui.pages.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseElement extends Base {

    protected WebElement element;

    public BaseElement(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.element = rootElement;
    }

    public WebElement getWebElement() {
        return this.element;
    }
}
