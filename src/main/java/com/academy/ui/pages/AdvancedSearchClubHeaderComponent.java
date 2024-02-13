package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvancedSearchClubHeaderComponent  extends AdvancedSearchHeaderComponent{
    protected WebElement ShowOnMapButton;
    public AdvancedSearchClubHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getShowOnMapButton(){
        if (ShowOnMapButton == null) {
            ShowOnMapButton = rootElement.findElement(By.xpath(".//button[contains(@class,'show-map-button')]"));
        }
        return ShowOnMapButton;
    }

    public void clickShowOnMapButton(){
        this.getShowOnMapButton().click();
    }

}
