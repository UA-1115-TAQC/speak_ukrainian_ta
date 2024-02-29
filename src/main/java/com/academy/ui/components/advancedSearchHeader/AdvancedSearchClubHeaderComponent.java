package com.academy.ui.components.advancedSearchHeader;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class AdvancedSearchClubHeaderComponent extends AdvancedSearchHeaderComponent {
    @FindBy(xpath = ".//button[contains(@class,'show-map-button')]")
    protected WebElement showOnMapButton;

    public AdvancedSearchClubHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickShowOnMapButton() {
        getShowOnMapButton().click();
    }

}
