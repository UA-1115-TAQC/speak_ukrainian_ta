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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    @FindBy(xpath = "//div[contains(@class, \"rc-virtual-list-holder-inner\")]")
    protected WebElement advancedSearchTooltipNode;
    @FindBy(xpath = "//span[@aria-label=\"close-circle\"]")
    protected WebElement selectionSearchCloseButton;


    public AdvancedSearchHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTextSelectionSearchInputField() {
        return this.getSelectionSearchInputField().getAttribute("value");
    }

    public AdvancedSearchHeaderComponent setTextSelectionSearchInputField(String text) {
        this.selectionSearchInputField.sendKeys(text);
        return this;
    }

    public AdvancedSearchTooltip clickSelectionSearchInputField() {
        this.getSelectionSearchInputField().click();
        return new AdvancedSearchTooltip(driver, getAdvancedSearchTooltipNode());
    }

    //there is a bug - when you click on this icon after entering some text, nothing happens
    public AdvancedSearchHeaderComponent clickSearchIcon() {
        this.searchIcon.click();
        return this;
    }

    public ClubsPage clickAdvancedSearchIcon() {
        this.getAdvancedSearchIcon().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.urlContains("clubs"));
        ClubsPage clubsPage = new ClubsPage(driver);
        wait.until(ExpectedConditions.visibilityOf(clubsPage.getAdvancedSearchClubHeader().getShowOnMapButton()));
        return clubsPage;
    }

    public AdvancedSearchHeaderComponent clickSelectionSearchCloseButton() {
            if (getTextSelectionSearchInputField() != null) {
                getSelectionSearchCloseButton().click();
                return this;
            }
            throw new Error("You haven't entered any text");
    }
}
