package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftSideProfileComponent extends BaseComponent{
    public LeftSideProfileComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    @FindBy(xpath = "//div[@class='menu-title']")
    private WebElement textTitle;
    @FindBy(xpath = "//a[text()='Профіль']")
    private WebElement buttonProfile;
    @FindBy(xpath = "//a[text()='Повідомлення']")
    private WebElement buttonMessages;
    @FindBy(xpath = "//a[text()='Скарги']")
    private WebElement buttonComplaints;
    @FindBy(xpath = "//a[text()='Заявки']")
    private WebElement buttonApplications;
    @FindBy(xpath = "//a[text()='Сертифікати']")
    private WebElement buttonCertificates;
    @FindBy(xpath = "//span[contains(@class, 'anticon-user icon-use')]")
    private WebElement iconButtonProfile;
    @FindBy(xpath = "//span[contains(@class, 'mail')]")
    private WebElement iconButtonMessages;
    @FindBy(xpath = "//span[contains(@class, 'exclamation')]")
    private WebElement iconButtonComplaints;
    @FindBy(xpath = "//span[contains(@class, 'check icon')]")
    private WebElement iconButtonApplications;
    @FindBy(xpath = "//span[contains(@class, 'file-done')]")
    private WebElement iconButtonCertificates;

    public void clickButtonProfile(){
        buttonProfile.click();
    }
    public void clickButtonMessages(){
        buttonMessages.click();
    }
    public void clickButtonComplaints(){
        buttonComplaints.click();
    }
    public void clickButtonApplications(){
        buttonApplications.click();
    }
    public void clickButtonCertificates(){
        buttonCertificates.click();
    }
    public WebElement getTitleLeftSide() { return textTitle; }
    public WebElement getIconButtonProfile(){ return iconButtonProfile; }
    public WebElement getIconButtonMessages(){ return iconButtonMessages; }
    public WebElement getIconButtonComplaints(){ return iconButtonComplaints; }
    public WebElement getIconButtonApplications(){ return iconButtonApplications; }
    public WebElement getIconButtonCertificates(){ return iconButtonCertificates; }

}
