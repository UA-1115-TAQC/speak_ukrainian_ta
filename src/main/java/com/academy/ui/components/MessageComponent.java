package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessageComponent extends BaseComponent{
    public MessageComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @FindBy(xpath = ".//div[contains(@class, 'collapse-expand-icon')]")
    private WebElement arrowIcon;

    @Getter
    @FindBy(xpath = ".//span[contains(@class, 'header-text')]")
    private WebElement messageHeader;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'date')]")
    private WebElement date;

    @Getter
    @FindBy(xpath = ".//span[contains(@aria-label, 'circle')]")
    private WebElement messageStatusIcon;

    @Getter
    @FindBy(xpath = ".//span[contains(@aria-label, 'delete')]")
    private WebElement messageDeleteIcon;

    @Getter
    @FindBy(xpath = ".//descendant::span[contains(@class, 'avatar-circle')]")
    private WebElement userAvatar;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'userName')]")
    private WebElement userName;

    @FindBy(xpath = ".//div[@class='ant-collapse-content-box']")
    private WebElement messageText;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'btn')]")
    private WebElement answerButton;

    public String messageTextGetText(){
        String fullText = messageText.getText();
        WebElement replyButton = messageText.findElement(By.xpath("./descendant::button"));
        String buttonText = replyButton.getText();
        return fullText.replace(buttonText, "").trim();
    }
    public void arrowIconClick(){ arrowIcon.click(); }
    public void messageStatusIconClick(){ messageStatusIcon.click(); }
    public void messageDeleteIconClick(){ messageDeleteIcon.click(); }
}