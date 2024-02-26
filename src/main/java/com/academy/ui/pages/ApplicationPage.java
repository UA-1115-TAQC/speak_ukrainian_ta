package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationPage extends ProfilePage{
    public ApplicationPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    @FindBy(xpath = "descendant::div[contains(@class, 'contentTitle')]")
    private WebElement title;

    @Getter
    @FindBy(xpath = "descendant::input[contains(@class, 'searchBox')]")
    private WebElement searchInput;

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'filterSelectStatuses')]//span[contains(., 'статуси')]/..")
    private WebElement statusesDropDown;

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'filterSelectStatuses')]//span[contains(., 'заявки')]/..")
    private WebElement applicationsDropDown;

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'filterSelectRight')]")
    private WebElement childrenDropDown;

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'noRegistrations')]")
    private WebElement noApplicationsTitle;

    public void searchInputSendKey(String searchText){
        searchInput.sendKeys(searchText);
    }

    public void statusesDropDownClick(){ statusesDropDown.click(); }
    public void applicationsDropDownClick(){ applicationsDropDown.click(); }
    public void childrenDropDownClick(){ childrenDropDown.click(); }

}
