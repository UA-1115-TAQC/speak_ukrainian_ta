package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.BaseComponent;
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

    public ClubCheckboxLogoNameComponent clickOnCheckbox() {
        clubCheckbox.click();
        return this;
    }

}
