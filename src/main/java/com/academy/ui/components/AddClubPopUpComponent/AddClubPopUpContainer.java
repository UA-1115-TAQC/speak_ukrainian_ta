package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AddClubPopUpContainer extends BaseComponent {

    @FindBy(xpath = "./descendant::div[contains(@class,'add-club-header')]")
    public WebElement clubTitle;

    public AddClubPopUpContainer(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

}