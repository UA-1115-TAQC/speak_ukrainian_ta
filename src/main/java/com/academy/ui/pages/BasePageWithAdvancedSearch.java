package com.academy.ui.pages;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BasePageWithAdvancedSearch extends BasePage {
    @Getter(AccessLevel.NONE)
    public AdvancedSearchHeaderComponent advancedSearchHeaderComponent;
    @FindBy(xpath = "//div[contains(@class, \"lower-header-box\")]")
    protected WebElement advancedSearchHeaderComponentNode;
    public BasePageWithAdvancedSearch(WebDriver driver) {
        super(driver);
        this.advancedSearchHeaderComponent = getAdvancedSearchHeaderComponent();
    }

    public AdvancedSearchHeaderComponent getAdvancedSearchHeaderComponent() {
        return advancedSearchHeaderComponent == null ?
                advancedSearchHeaderComponent = new AdvancedSearchHeaderComponent(driver, advancedSearchHeaderComponentNode)
                : advancedSearchHeaderComponent;
    }
}
