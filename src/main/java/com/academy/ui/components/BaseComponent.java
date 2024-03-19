package com.academy.ui.components;

import com.academy.ui.pages.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @Step("Check visibility of the component's root element with wait")
    public boolean isComponentVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(rootElement));
        return rootElement.isDisplayed();
    }

    @Step("Scroll until the element of the pagination component is in view")
    public void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center', behavior: 'smooth'});", element);
    }

}
