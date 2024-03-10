package com.academy.ui.components;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePopUp extends BaseComponent {

    @FindBy(xpath = "./descendant::button[@class='ant-modal-close']")
    private WebElement closeButton;

    public BasePopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }

    @Step("Wait {timeout} seconds until pop-up opens")
    public void waitPopUpOpen(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(e -> rootElement.isDisplayed());
    }

    @Step("Check pop-up is open")
    public boolean isOpen() {
        return rootElement.isDisplayed();
    }

    @Step("Click on close 'X' button")
    public void close() {
        getCloseButton().click();
    }

}