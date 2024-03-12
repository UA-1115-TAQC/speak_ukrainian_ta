package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AddCenterPopUpContainer extends BaseComponent {

    @FindBy(xpath = "./descendant::div[@class='modal-title']")
    public WebElement centerTitle;

    @FindBy(xpath = "./descendant::button[contains(@class,'next-btn')]")
    private WebElement nextStepButton;

    @FindBy(xpath = "./descendant::button[contains(@class,'prev-btn')]")
    private WebElement prevStepButton;

    public AddCenterPopUpContainer(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddCenterPopUpContainer clickNextStepButton(){
        nextStepButton.click();
        return this;
    }

    public AddCenterPopUpContainer clickPreviousStepButton(){
        prevStepButton.click();
        return this;
    }
}