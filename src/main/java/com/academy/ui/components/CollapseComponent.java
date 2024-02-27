package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CollapseComponent extends BaseComponent{

    @FindBy(xpath = ".//div[@class ='ant-collapse-header']")
    private WebElement collapseHeader;

    @FindBy(xpath = ".//div[@class ='ant-collapse-expand-icon']/span")
    private WebElement collapseExpandIcon;

    @FindBy(xpath = ".//span[@class ='ant-collapse-header-text']")
    private WebElement collapseHeaderText;

    @FindBy(xpath = ".//div[contains(@class,'ant-collapse-content-active') or contains(@class,'ant-collapse-content-inactive')]")
    private WebElement collapseContentBox;


    public CollapseComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public CollapseComponent clickCollapseHeader() {
        collapseHeader.click();
        return this;
    }
}
