package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BasePopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubPopUpComponent extends BasePopUp {
    protected static final String classNameAddClubPopUP = "modal-add-club";
    public AddClubPopUpSider sider;
    public AddClubPopUpStepOne stepOneContainer;

    @FindBy(xpath ="./descendant::div[@class='ant-layout-sider-children']")
    private WebElement siderElement;

    @FindBy(xpath = "./descendant::main[contains(@class,'add-club-container')]")
    private WebElement stepOneContainerElement;

    public AddClubPopUpComponent(WebDriver driver) {
        super(driver, classNameAddClubPopUP);
        this.sider = getSider();
        this.stepOneContainer = getStepOneContainer();
    }

    public AddClubPopUpSider getSider() {
        return new AddClubPopUpSider(driver, siderElement);
    }

    public AddClubPopUpStepOne getStepOneContainer() {
        return new AddClubPopUpStepOne(driver, stepOneContainerElement);
    }
}
