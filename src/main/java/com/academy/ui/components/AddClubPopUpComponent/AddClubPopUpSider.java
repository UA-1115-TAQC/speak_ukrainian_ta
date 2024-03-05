package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AddClubPopUpSider extends BaseComponent {

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-steps-item-wait') or contains(@class,'ant-steps-item-process')]")
    private List<WebElement> stepsList;

    @FindBy(xpath = "./descendant::span[@class='ant-steps-icon'][1]")
    private WebElement firstStepIcon;

    @FindBy(xpath = "./descendant::span[@class='ant-steps-icon'][2]")
    private WebElement secondStepIcon;
    @FindBy(xpath = "//descendant::div[@class='ant-steps-item-icon'][2]")
    private WebElement secondStepIconBackground;

    @FindBy(xpath = "./descendant::span[@class='ant-steps-icon'][3]")
    private WebElement thirdStepIcon;

    @FindBy(xpath = "./descendant::div[@class='ant-steps-item-title'][1]")
    private WebElement firstStepTitle;

    @FindBy(xpath = "./descendant::div[@class='ant-steps-item-title'][2]")
    private WebElement secondStepTitle;

    @FindBy(xpath = "./descendant::div[@class='ant-steps-item-title'][3]")
    private WebElement thirdStepTitle;

    public AddClubPopUpSider(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

}
