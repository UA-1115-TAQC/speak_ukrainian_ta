package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePopUp extends Base {
    protected WebElement closeButton;

    public BasePopUp(WebDriver driver) {
        super(driver);
    }

    public WebElement getCloseButton() {
        if (closeButton == null) {
            closeButton = driver.findElement(By.xpath("//div[contains(@class,'ant-modal-centered') and not(contains(@style,'display'))]/descendant::button[@class='ant-modal-close']"));
        }
        return closeButton;
    }

    public void clickCloseButton() {
        getCloseButton().click();
    }
}
