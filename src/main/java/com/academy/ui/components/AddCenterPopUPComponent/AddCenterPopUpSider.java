package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpSider;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddCenterPopUpSider extends AddClubPopUpSider {

    @FindBy(xpath = "./descendant::span[@class='ant-steps-icon'][4]")
    private WebElement fourthStepIcon;

    @FindBy(xpath = "./descendant::div[@class='ant-steps-item-title'][4]")
    private WebElement fourthStepTitle;

    public AddCenterPopUpSider(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
