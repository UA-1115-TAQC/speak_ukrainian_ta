package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LocationListElement extends BaseComponent {

    @FindBy(xpath = "./descendant::h4[@class='ant-list-item-meta-title']")
    private WebElement locationTitle;

    @FindBy(xpath = "./descendant::div[@class='ant-list-item-meta-description']")
    private WebElement descriptionTitle;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-item-action']//span[@aria-label='edit']")
    private WebElement editIcon;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-item-action']//span[@aria-label='delete']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='ant-popover-inner-content']")
    private WebElement popConfirm;

    @FindBy(xpath = "//div[@class='ant-popover-inner-content']/descendant::span[@aria-label='exclamation-circle']")
    private WebElement popConfirmIcon;

    @FindBy(xpath = "//div[@class='ant-popover-inner-content']/descendant::div[@class='ant-popconfirm-title']")
    private WebElement popConfirmTitle;

    @FindBy(xpath = "//div[@class='ant-popover-inner-content']/descendant::button[contains(@class,'popConfirm-cancel-button')]")
    private WebElement popConfirmCancelButton;

    @FindBy(xpath = "//div[@class='ant-popover-inner-content']/descendant::button[contains(@class,'popConfirm-ok-button')]")
    private WebElement popConfirmOkButton;

    public LocationListElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LocationListElement clickEditIcon(){
        editIcon.click();
        return this;
    }

    public LocationListElement clickDeleteIcon(){
        deleteIcon.click();
        return this;
    }

    public LocationListElement clickPopConfirmCancelButton(){
        popConfirmCancelButton.click();
        return this;
    }

    public LocationListElement clickPopConfirmOkButton(){
        popConfirmOkButton.click();
        return this;
    }
}
