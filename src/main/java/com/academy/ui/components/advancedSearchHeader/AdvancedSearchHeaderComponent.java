package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
@Getter
public class AdvancedSearchHeaderComponent extends BaseComponent {
    @FindBy(xpath = "//h2[@class='city-name']")
    protected WebElement advancedSearchTextHeading;
    @FindBy(xpath = "//div[contains(@class, \"search\")]//input[@type=\"search\"]")
    protected WebElement selectionSearchInputField;
    @FindBy(xpath = "//span[@class='ant-select-selection-placeholder']")
    protected WebElement selectionSearchInputFieldPlaceholder;
    @FindBy(xpath = "//div[contains(@class, \"search-icon-group\")]/span[@aria-label=\"search\"]")
    protected WebElement searchIcon;
    @FindBy(xpath = "//div[contains(@class, \"search-icon-group\")]/span[@aria-label=\"control\"]")
    protected WebElement advancedSearchIcon;
    @FindBy(xpath = "//span[@aria-label=\"close-circle\"]")
    protected WebElement searchInputCloseButton;
    protected AdvancedSearchTooltip advancedSearchTooltip;

    public AdvancedSearchHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public void setTextSelectionSearchInputField(String text) {
        this.selectionSearchInputField.sendKeys(text);
    }

    public String getTextSelectionSearchInputField() {
        return this.getSelectionSearchInputField().getAttribute("value");
    }

    public AdvancedSearchTooltip clickSelectionSearchInputField() {
        this.getSelectionSearchInputField().click();
        WebElement node = driver.findElement(By.xpath("//div[contains(@class, \"rc-virtual-list-holder-inner\")]"));
        return advancedSearchTooltip = new AdvancedSearchTooltip(driver, node);
    }
//there is a bug - when you click on this icon after entering some text, nothing happens
    public void clickSearchIcon() {
        this.searchIcon.click();
    }

    public ClubsPage clickAdvancedSearchIcon() {
        this.advancedSearchIcon.click();
        return new ClubsPage(driver);
    }
}
