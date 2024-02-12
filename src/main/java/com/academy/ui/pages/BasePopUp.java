package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePopUp extends BaseComponent {
    protected WebElement closeButton;

    public BasePopUp(WebDriver driver, String className) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'" + className + "')]")));
    }

    public boolean waitPopUpOpen(java.time.Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(e -> rootElement.isDisplayed());
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
