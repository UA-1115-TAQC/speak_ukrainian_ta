package com.academy.ui.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SearchCertificatePage extends BasePage {

    @FindBy(xpath = "./descendant::div[@class='certificateSearchContent']/h3")
    private WebElement searchCertificateTitle;

    @FindBy(xpath = "./descendant::div[@class='certificateSearchContent']/h2")
    private WebElement resultSearchingTitle;

    @FindBy(xpath = "./descendant::div[@class='searchCertificateUser']//input[@type='text']")
    private WebElement selectionSearchInputField;

    @FindBy(xpath = "./descendant::span[(@role='img') and (@aria-label='search')]")
    private WebElement searchIcon;

    @FindBy(xpath = "./descendant::button[contains(@class, 'search-button')]")
    private WebElement searchButton;

    @FindBy(xpath = "./descendant::span[@class='ant-input-clear-icon']")
    private WebElement clearSearchingTextButton;

    public SearchCertificatePage(WebDriver driver) {
        super(driver);
    }

    @Step("Set data {text} for searching certificates")
    public SearchCertificatePage setTextSelectionSearchInputField(String text) {
        selectionSearchInputField.sendKeys(text);
        return this;
    }

    @Step("Click search button")
    public SearchCertificatePage clickSearchCertificate() {
        searchButton.click();
        return this;
    }

    @Step("Clear searching text")
    public SearchCertificatePage clearSearchingText() {
        clearSearchingTextButton.click();
        return this;
    }
}
