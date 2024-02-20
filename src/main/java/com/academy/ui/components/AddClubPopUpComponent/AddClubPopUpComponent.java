package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BasePopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddClubPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//div[contains(@class,'modal-add-club')]")
    private WebElement addClubPopUp;

    @FindBy(xpath = "./descendant::div[@class='ant-layout-sider-children']")
    private WebElement siderElement;

    @FindBy(xpath = "./descendant::main[contains(@class,'add-club-container')]")
    private WebElement stepContainerElement;

    @FindBy(xpath = "./descendant::main[contains(@class,'add-club-container')]//div[contains(@class,'ant-steps-item-active')]//span[@class='ant-steps-icon']")
    private WebElement activeStep;

    private AddClubPopUpSider sider;
    @Getter(AccessLevel.NONE)
    private AddClubPopUpStepOne stepOneContainer;
    @Getter(AccessLevel.NONE)
    private AddClubPopUpStepTwo stepTwoContainer;

    public AddClubPopUpComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'modal-add-club')]")));
        this.sider = new AddClubPopUpSider(driver, siderElement);
    }

    public AddClubPopUpStepOne getStepOneContainer() {
        if (activeStep.getAttribute("innerText").equals("1")) {
            stepOneContainer = new AddClubPopUpStepOne(driver, stepContainerElement);
        }
        return stepOneContainer;
    }

    public AddClubPopUpStepTwo getStepTwoContainer() {
        if (activeStep.getAttribute("innerText").equals("2")) {
            stepTwoContainer = new AddClubPopUpStepTwo(driver, stepContainerElement);
        }
        return stepTwoContainer;
    }
}
