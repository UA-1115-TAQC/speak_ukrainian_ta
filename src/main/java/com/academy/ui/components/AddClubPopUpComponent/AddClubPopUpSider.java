package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddClubPopUpSider extends BaseElement {

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-steps-item-wait') or contains(@class,'ant-steps-item-process')]")
    public List<WebElement> stepsList;

    @FindBy(xpath = "./descendant::span[@class='ant-steps-icon']")
    public List<WebElement> stepIconList;

    @FindBy(xpath = "./descendant::span[@class='ant-steps-item-title']")
    public List<WebElement> stepTitleList;

    public AddClubPopUpSider(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getStepIcon(int index){
        return (index >= 0 && index < stepIconList.size()) ? stepIconList.get(index) : null;
    }

    public WebElement getStepTitle(int index){
        return (index >= 0 && index < stepTitleList.size()) ? stepTitleList.get(index) : null;
    }

}
