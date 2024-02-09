package com.academy.ui.pages;

import com.academy.ui.pages.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.pages.advancedSearchHeader.AdvancedSearchTooltip;
import com.academy.ui.pages.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage extends Base {
    public HeaderComponent header;
    public AdvancedSearchHeaderComponent advancedSearchHeaderComponent;
    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
        this.advancedSearchHeaderComponent = getAdvancedSearchHeaderComponent();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }
    public AdvancedSearchHeaderComponent getAdvancedSearchHeaderComponent() {
        if (advancedSearchHeaderComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class, \"lower-header-box\")]"));
            advancedSearchHeaderComponent = new AdvancedSearchHeaderComponent(driver, node);
        }
        return advancedSearchHeaderComponent;
    }


    public void openURL(String url) {
        driver.get(url);
    }

}
