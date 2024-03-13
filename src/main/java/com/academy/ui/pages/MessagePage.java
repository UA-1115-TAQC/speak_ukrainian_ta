package com.academy.ui.pages;

import com.academy.ui.components.MessageComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MessagePage extends ProfilePage{
    public MessagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[contains(@class, 'input') and @placeholder='Пошук...']")
    private WebElement searchInput;
    
    @FindBy(xpath = ".//span[contains(@class, 'selection-item')]")
    private WebElement selectedItemDropDown;
    
    @FindBy(xpath=".//div[contains(@class, 'select-selector')]")
    private WebElement dropDown;
    
    @FindBy(xpath = ".//div[contains(@class, 'select-item') and contains(text(), 'нові')]")
    private WebElement newFirstItemDropDown;
    
    @FindBy(xpath = ".//div[contains(@class, 'select-item') and contains(text(), 'старі')]")
    private WebElement oldFirstItemDropDown;
    
    @FindBy(xpath = ".//span[text()='Показати тільки непрочитані повідомлення: ']")
    private WebElement showUnreadMessageTitle;

    @FindBy(xpath = ".//span[text()='Повідомлення без відповіді: ']")
    private WebElement showUnansweredMessageTitle;

    @FindBy(xpath = ".//span[text()='Показати тільки непрочитані повідомлення: ']/following-sibling::button//span[@class='ant-switch-inner']")
    private WebElement unreadMessagesSwitch;

    @FindBy(xpath = ".//span[text()='Повідомлення без відповіді: ']/following-sibling::button//span[@class='ant-switch-inner']")
    private WebElement unansweredMessagesSwitch;

    @FindBy(xpath = ".//div[contains(@class, 'noMessages')]")
    private WebElement noMessagesTitle;

    @FindBy(xpath = ".//ul[contains(@class, 'ant-list-items')]//div[contains(@class, 'collapse ')]")
    private List<WebElement> messageElements;

    public List<MessageComponent> getMessageElements(){
        return messageElements.stream().map(el -> new MessageComponent(driver, el)).collect(Collectors.toList());
    }
    @Step("Click on unread messages switch")
    public MessagePage unreadMessagesSwitchClick(){
        unreadMessagesSwitch.click();
        return this;
    }
    @Step("Click on unanswered messages switch")
    public MessagePage unansweredMessagesSwitchClick(){
        unansweredMessagesSwitch.click();
        return this;
    }
    @Step("Send text to search input")
    public void sendTextToSearchInput(String searchText){
        searchInput.sendKeys(searchText);
    }
}
