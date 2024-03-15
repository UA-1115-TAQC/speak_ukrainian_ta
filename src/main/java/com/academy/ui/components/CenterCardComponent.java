package com.academy.ui.components;

import io.qameta.allure.Step;
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

    @Step("Click on the tittle of the center card")
    public void clickTitle() {
        getTitle().click();
    }

    @Step("Click on the address of the center card")
    public void clickAddress() {
        getAddress().click();
    }

    @Step("Click on the 'Детальніше' button of the center card")
    public void clickDetailsButton() {
        getDetailsButton().click();
    }

}
