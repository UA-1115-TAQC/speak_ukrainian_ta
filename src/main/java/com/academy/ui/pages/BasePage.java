package com.academy.ui.pages;

import com.academy.ui.pages.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.pages.advancedSearchHeader.AdvancedSearchTooltip;
import com.academy.ui.pages.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage extends Base {
    public HeaderComponent header;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }

    public void openURL(String url) {
        driver.get(url);
    }

}
