package com.academy.ui.pages;

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

    public SearchCertificatePage setTextSelectionSearchInputField(String text) {
        selectionSearchInputField.sendKeys(text);
        return this;
    }

    public SearchCertificatePage clickSearchCertificate() {
        searchButton.click();
        return this;
    }

    public SearchCertificatePage clearSearchingText() {
        clearSearchingTextButton.click();
        return this;
    }
}
