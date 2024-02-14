package com.academy.ui.components;

import com.academy.ui.pages.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseComponent extends Base {

    protected WebElement rootElement;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
    }

    public WebElement getWebElement() {
        return this.rootElement;
    }
}
