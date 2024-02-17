package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePopUp extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'ant-modal-centered') and not(contains(@style,'display'))]/descendant::button[@class='ant-modal-close']")
    private WebElement closeButton;

    public BasePopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }
    public void waitPopUpOpen(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(e -> driver.findElement(By.xpath("//div[contains(@class,'ant-modal')]")).isDisplayed());
    }

}