package com.academy.ui.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ComplaintPage extends ProfilePage{

    public ComplaintPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//div[contains(@class, 'contentTitle')]")
    private WebElement title;
    
    @FindBy(xpath = ".//span[contains(text(), 'непрочитані')]")
    private WebElement unreadTitle;

    @FindBy(xpath = ".//span[contains(., 'непрочитані')]/preceding-sibling::span[contains(@class, 'checkbox')]")
    private WebElement unreadCheckBox;

    @FindBy(xpath = ".//span[contains(text(), 'без відповіді')]")
    private WebElement unansweredTitle;

    @FindBy(xpath = ".//span[contains(., 'без відповіді')]/preceding-sibling::span[contains(@class, 'checkbox')]")
    private WebElement unansweredCheckBox;
    
    @FindBy(xpath = ".//span[contains(@class, 'anticon-search')]")
    private WebElement searchIcon;

    @FindBy(xpath = ".//span[contains(@class, 'searchInput')]//input")
    private WebElement searchInput;
    
    @FindBy(xpath = ".//button[contains(@class, 'search-button')]")
    private WebElement searchButton;
    
    @FindBy(xpath = ".//div[contains(@class, 'noMessages')]")
    private WebElement noComplaintTitle;

    @Step("Click on unread in check box")
    public ComplaintPage unreadCheckBoxClick(){
        unreadCheckBox.click();
        return this;
    }

    @Step("Click on unanswered in check box")
    public ComplaintPage unansweredCheckBoxClick(){
        unansweredCheckBox.click();
        return this;
    }

    @Step("Send text to search input")
    public ComplaintPage searchInputSendKeys(String searchText){
        searchInput.sendKeys(searchText);
        return this;
    }

    @Step("Click on search button")
    public ComplaintPage searchButtonClick() {
        searchButton.click();
        return this;
    }
}
