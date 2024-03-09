package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ClubCheckboxLogoNameComponent extends BaseComponent {

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement clubCheckbox;

    @FindBy(xpath = ".//div[@class='icon-box']//img")
    private WebElement clubLogo;

    @FindBy(xpath = ".//span[@class='club-name']")
    private WebElement clubName;

    public ClubCheckboxLogoNameComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click on club checkbox on the fourth step of Add center pop-up")
    public ClubCheckboxLogoNameComponent clickOnCheckbox() {
        clubCheckbox.click();
        return this;
    }

}
