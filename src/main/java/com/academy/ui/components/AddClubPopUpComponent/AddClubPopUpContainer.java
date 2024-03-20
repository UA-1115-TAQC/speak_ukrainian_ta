package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AddClubPopUpContainer extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class,'add-club-header')]")
    public WebElement clubTitle;

    @FindBy(xpath = ".//button[contains(@class,'add-club-content-next')]")
    private WebElement nextStepButton;

    @FindBy(xpath = ".//button[contains(@class,'add-club-content-prev')]")
    private WebElement previousStepButton;

    public AddClubPopUpContainer(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get next step button on the Add/Edit club pop-up")
    public AddClubPopUpContainer clickNextStepButton() {
        nextStepButton.click();
        return this;
    }

    @Step("Get previous step button on the Add/Edit club pop-up")
    public AddClubPopUpContainer clickPreviousStepButton() {
        previousStepButton.click();
        return this;
    }

}
