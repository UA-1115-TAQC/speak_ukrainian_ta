package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.AddChildPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.BasePopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddCenterPopUpComponent extends BasePopUp {
    @FindBy(xpath = "./descendant::main[contains(@class,'add-center-container')]//div[contains(@class,'ant-steps-item-active')]//span[@class='ant-steps-icon']")
    private WebElement activeStep;
    @Getter(AccessLevel.NONE)
    private AddCenterPopUpStepOne stepOneContainer;
    @FindBy(xpath = "./descendant::main[contains(@class,'add-center-container')]")
    private WebElement stepContainerElement;
    public AddCenterPopUpComponent(WebDriver driver){
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'addCenter')]")));
    }
    public AddCenterPopUpStepOne getStepOneContainer() {
        if (activeStep.getAttribute("innerText").equals("1")) {
            stepOneContainer = new AddCenterPopUpStepOne(driver, stepContainerElement);
        }
        return stepOneContainer;
    }
}
