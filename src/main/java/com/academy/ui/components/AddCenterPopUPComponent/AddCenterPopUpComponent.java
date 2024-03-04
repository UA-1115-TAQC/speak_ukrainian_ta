package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.BasePopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddCenterPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//div[contains(@class,'addCenter')]")
    private WebElement addCenterPopUp;

    @FindBy(xpath = "./descendant::div[@class='side']")
    private WebElement siderElement;

    @FindBy(xpath = ".//main[contains(@class,'add-center-container')]")
    private WebElement stepContainerElement;

    @FindBy(xpath = ".//main[contains(@class,'add-center-container')]//div[contains(@class,'ant-steps-item-active')]//span[@class='ant-steps-icon']")
    private WebElement activeStep;

    private AddCenterPopUpSider sider;

    @Getter(AccessLevel.NONE)
    private AddCenterPopUpStepOne stepOneContainer;

    @Getter(AccessLevel.NONE)
    private AddCenterPopUpStepTwo stepTwoContainer;

    public AddCenterPopUpComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'addCenter')]")));
        sider = new AddCenterPopUpSider(driver, siderElement);
    }

    public AddCenterPopUpStepOne getStepOneContainer(){
        if (activeStep.getAttribute("innerText").equals("1")) {
            stepOneContainer = new AddCenterPopUpStepOne(driver, stepContainerElement);
        }
        return stepOneContainer;
    }

    public AddCenterPopUpStepTwo getStepTwoContainer(){
        if (activeStep.getAttribute("innerText").equals("2")) {
            stepTwoContainer = new AddCenterPopUpStepTwo(driver, stepContainerElement);
        }
        return stepTwoContainer;
    }

}
