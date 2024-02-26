package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CenterCardComponent extends BaseComponent{
    @FindBy(xpath = ".//div[contains(@class,'center-title')]")
    protected WebElement title;

    @FindBy(xpath = ".//p[contains(@class,'center-description-in-block')]")
    protected WebElement description;

    @FindBy(xpath = ".//ul[contains(@class,'center-rating')]")
    protected WebElement rating;

    @FindBy(xpath = ".//div[contains(@class,'address')]")
    protected WebElement address;

    @FindBy(xpath = ".//a[contains(@class,'details-button')]")
    protected WebElement detailsButton;

    public CenterCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickTitle() {
        getTitle().click();
    }

    public void clickAddress() {
        getAddress().click();
    }

    public void clickDetailsButton() {
        getDetailsButton().click();
    }
}
