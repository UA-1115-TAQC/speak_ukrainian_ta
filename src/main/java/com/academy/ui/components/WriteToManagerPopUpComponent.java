package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class WriteToManagerPopUpComponent extends BasePopUp{

    @FindBy(xpath = "./descendant::textarea[contains(@class,'MessageToClubManager')]")
    private WebElement messageToClubManager;

    @FindBy(xpath = "/descendant::button[contains(@class,'Button')]")
    private WebElement submitButton;

    @FindBy(xpath = "./descendant::div[@class='ant-form-item-explain-error']")
    private WebElement hintMessage;


    public WriteToManagerPopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WriteToManagerPopUpComponent clickOnSubmitButton(){
        submitButton.click();
        return this;
    }

    public WriteToManagerPopUpComponent addTextToManager(String text) {
        messageToClubManager.sendKeys(text);
        return this;
    }

}
