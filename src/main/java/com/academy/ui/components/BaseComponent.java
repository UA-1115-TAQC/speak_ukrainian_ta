package com.academy.ui.components;

import com.academy.ui.pages.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BaseComponent extends Base {

    protected WebElement rootElement;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public WebElement getWebElement() {
        return this.rootElement;
    }
}
