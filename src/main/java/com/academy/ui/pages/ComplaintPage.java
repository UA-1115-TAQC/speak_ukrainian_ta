package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComplaintPage extends ProfilePage{

    public ComplaintPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'contentTitle')]")
    private WebElement title;

    @Getter
    @FindBy(xpath = "./span[contains(text(), 'непрочитані')]")
    private WebElement unreadTitle;

    @Getter
    @FindBy(xpath = "./span[contains(., 'непрочитані')]/preceding-sibling::span[contains(@class, 'checkbox')]")
    private WebElement unreadCheckBox;

    @Getter
    @FindBy(xpath = "./span[contains(text(), 'без відповіді')]")
    private WebElement unansweredTitle;

    @Getter
    @FindBy(xpath = "./span[contains(., 'без відповіді')]/preceding-sibling::span[contains(@class, 'checkbox')]")
    private WebElement unansweredCheckBox;

    @Getter
    @FindBy(xpath = "./span[contains(@class, 'anticon-search')]")
    private WebElement searchIcon;

    @Getter
    @FindBy(xpath = "./span[contains(@class, 'searchInput')]//input")
    private WebElement searchInput;

    @Getter
    @FindBy(xpath = "./button[contains(@class, 'search-button')]")
    private WebElement searchButton;

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'noMessages')]")
    private WebElement noComplaintTitle;

    public void unreadCheckBoxClick(){ unreadCheckBox.click(); }

    public void unansweredCheckBoxClick(){ unansweredCheckBox.click(); }

    public void searchInputSendKeys(String searchText){ searchInput.sendKeys(searchText); }

    public void searchButtonClick() { searchButton.click(); }

}
