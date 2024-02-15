package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePopUp extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'ant-modal-centered') and not(contains(@style,'display'))]/descendant::button[@class='ant-modal-close']")
    private WebElement closeButton;

    public BasePopUp(WebDriver driver, String className) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'" + className + "')]")));

    }

    public boolean waitPopUpOpen(java.time.Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(e -> rootElement.isDisplayed());
    }

    public void clickCloseButton() {
        closeButton.click();
    }
}