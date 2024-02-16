package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BasePopUp;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddClubPopUpComponent extends BasePopUp {

    protected static final String CLASS_NAME_ADD_CLUB_POP_UP = "modal-add-club";
    public AddClubPopUpSider sider;
    public AddClubPopUpStepOne stepOneContainer;

    @FindBy(xpath ="./descendant::div[@class='ant-layout-sider-children']")
    private WebElement siderElement;

    @FindBy(xpath = "./descendant::main[contains(@class,'add-club-container')]")
    private WebElement stepOneContainerElement;

    public AddClubPopUpComponent(WebDriver driver) {
        super(driver, CLASS_NAME_ADD_CLUB_POP_UP);
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
