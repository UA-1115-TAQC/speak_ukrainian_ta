package com.academy.ui.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ApplicationPage extends ProfilePage{
    public ApplicationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "descendant::div[contains(@class, 'contentTitle')]")
    private WebElement title;

    @FindBy(xpath = "descendant::input[contains(@class, 'searchBox')]")
    private WebElement searchInput;

    @FindBy(xpath = ".//div[contains(@class, 'filterSelectStatuses')]//span[contains(., 'статуси')]/..")
    private WebElement statusesDropDown;

    @FindBy(xpath = ".//div[contains(@class, 'filterSelectStatuses')]//span[contains(., 'заявки')]/..")
    private WebElement applicationsDropDown;

    @FindBy(xpath = ".//div[contains(@class, 'filterSelectRight')]")
    private WebElement childrenDropDown;

    @FindBy(xpath = ".//div[contains(@class, 'noRegistrations')]")
    private WebElement noApplicationsTitle;

    @Step("Send text to search input")
    public ApplicationPage searchInputSendKey(String searchText){
        searchInput.sendKeys(searchText);
        return this;
    }
    @Step("Click on statuses in drop down")
    public ApplicationPage statusesDropDownClick(){
        statusesDropDown.click();
        return this;
    }
    @Step("Click on applications in drop down")
    public ApplicationPage applicationsDropDownClick(){
        applicationsDropDown.click();
        return this;
    }
    @Step("Click on children in drop down")
    public ApplicationPage childrenDropDownClick(){
        childrenDropDown.click();
        return this;
    }
}
