package com.academy.ui.components.header.headerMenuComponent.adminpopup;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class LocationsAdminMenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'cities')]")
    private WebElement cities;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'districts')]")
    private WebElement districts;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'stations')]")
    private WebElement stations;

    public LocationsAdminMenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickCities() {
        clickElement(driver, cities); // todo
    }

    public void clickDistricts() {
        clickElement(driver, districts); // todo
    }

    public void clickStations() {
        clickElement(driver, stations); // todo
    }
}
