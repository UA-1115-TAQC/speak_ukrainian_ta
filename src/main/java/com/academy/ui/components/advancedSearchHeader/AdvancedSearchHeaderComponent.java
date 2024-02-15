package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvancedSearchHeaderComponent extends BaseComponent {
    protected WebElement advancedSearchTextHeading;
    protected WebElement selectionSearchInputField;
    protected WebElement selectionSearchInputFieldPlaceholder;
    protected WebElement searchIcon;
    protected WebElement advancedSearchIcon;
    protected AdvancedSearchTooltip advancedSearchTooltip;

    public AdvancedSearchHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getAdvancedSearchTextHeading() {
        if (advancedSearchTextHeading == null) {
            advancedSearchTextHeading = rootElement.findElement(By.xpath("//h2[@class='city-name']"));
        }
        return advancedSearchTextHeading;
    }

    public WebElement getSelectionSearchInputField() {
        if (selectionSearchInputField == null) {
            selectionSearchInputField = rootElement.findElement(By.xpath("//div[contains(@class, \"search\")]//input[@type=\"search\"]"));
        }
        return selectionSearchInputField;
    }

    public WebElement getSelectionSearchInputFieldPlaceholder() {
        if (selectionSearchInputFieldPlaceholder == null) {
            selectionSearchInputFieldPlaceholder = rootElement.findElement(By.xpath("//span[@class='ant-select-selection-placeholder']"));
        }
        return selectionSearchInputFieldPlaceholder;
    }

    public void setTextSelectionSearchInputField(String text) {
        this.getSelectionSearchInputField().sendKeys(text);
    }

    public String getTextSelectionSearchInputField() {
        return this.getSelectionSearchInputField().getAttribute("value");
    }

    public WebElement getSearchIcon() {
        if (searchIcon == null) {
            searchIcon = rootElement.findElement(By.xpath("//div[contains(@class, \"search-icon-group\")]/span[@aria-label=\"search\"]"));
        }
        return searchIcon;
    }

    public WebElement getAdvancedSearchIcon() {
        if (advancedSearchIcon == null) {
            advancedSearchIcon = rootElement.findElement(By.xpath("//div[contains(@class, \"search-icon-group\")]/span[@aria-label=\"control\"]"));
        }
        return advancedSearchIcon;
    }

    public void clickSelectionSearchInputField() {
        this.getSelectionSearchInputField().click();
        if (advancedSearchTooltip == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class, \"rc-virtual-list-holder-inner\")]"));
            advancedSearchTooltip = new AdvancedSearchTooltip(driver, node);
        }
    }

    public AdvancedSearchTooltip getAdvancedSearchTooltip() {
        return advancedSearchTooltip;
    }

    public void clickSearchIcon() {
        this.getSearchIcon().click();
    }

    public void clickAdvancedSearchIcon() {
        this.getAdvancedSearchIcon().click();
    }

}
