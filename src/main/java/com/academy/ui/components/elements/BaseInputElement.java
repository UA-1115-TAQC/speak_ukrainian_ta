package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;


@Getter
public class BaseInputElement extends BaseComponent {
    @FindBy(xpath = ".//input")
    protected WebElement input;

    public BaseInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Set input String value {value}")
    public BaseInputElement setValue(String value) {
        input.sendKeys(value);
        return this;
    }

    @Step("Set input Key value {key}")
    public BaseInputElement setKey(Keys key) {
        input.sendKeys(key);
        return this;
    }

    @Step("Clear input")
    public BaseInputElement clearInput() {
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            //obsolete mac os version
            input.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            input.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }

}
