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
            PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
            this.rootElement = rootElement;
    }

    public WebElement getWebElement() {
        return this.rootElement;
    }
}
