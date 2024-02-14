package com.academy.ui.pages;

import com.academy.ui.pages.advancedSearchHeader.AdvancedSearchHeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageWithAdvancedSearch extends BasePage {
    public AdvancedSearchHeaderComponent advancedSearchHeaderComponent;

    public BasePageWithAdvancedSearch(WebDriver driver) {
        super(driver);
        this.advancedSearchHeaderComponent = getAdvancedSearchHeaderComponent();
    }

    public AdvancedSearchHeaderComponent getAdvancedSearchHeaderComponent() {
        if (advancedSearchHeaderComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class, \"lower-header-box\")]"));
            advancedSearchHeaderComponent = new AdvancedSearchHeaderComponent(driver, node);
        }
        return advancedSearchHeaderComponent;
    }

}
