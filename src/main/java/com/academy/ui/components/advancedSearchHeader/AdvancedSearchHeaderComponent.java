package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ClubsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    @Step("Get text from the selection search input field")
    public String getTextSelectionSearchInputField() {
        return this.getSelectionSearchInputField().getAttribute("value");
    }
    @Step("Set text in selection search input field: {text}")
    public AdvancedSearchHeaderComponent setTextSelectionSearchInputField(String text) {
        String expectedInput = getTextSelectionSearchInputField() + text;
        this.selectionSearchInputField.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) driver -> getTextSelectionSearchInputField().equals(expectedInput));
        return this;
    }
    @Step("Click on selection search input field")
    public AdvancedSearchTooltip clickSelectionSearchInputField() {
        this.getSelectionSearchInputField().click();
        return new AdvancedSearchTooltip(driver, getAdvancedSearchTooltipNode());
    }

    //there is a bug - when you click on this icon after entering some text, nothing happens
    @Step("Click on the search icon")
    public AdvancedSearchHeaderComponent clickSearchIcon() {
        this.searchIcon.click();
        return this;
    }
    @Step("Click on the advanced search icon")
    public ClubsPage clickAdvancedSearchIcon() {
        this.getAdvancedSearchIcon().click();
        return  new ClubsPage(driver).waitUntilClubsPageIsLoaded(30);
    }
    @Step("Click on the close button in the selection search input")
    public AdvancedSearchHeaderComponent clickSelectionSearchCloseButton() {
            if (getTextSelectionSearchInputField() != null) {
                getSelectionSearchCloseButton().click();
                return this;
            }
            throw new Error("You haven't entered any text");
    }
}
