package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
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

    public BaseInputElement setValue(String value) {
        input.sendKeys(value);
        return this;
    }

    public BaseInputElement clearInput() {
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            input.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            input.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }

}
