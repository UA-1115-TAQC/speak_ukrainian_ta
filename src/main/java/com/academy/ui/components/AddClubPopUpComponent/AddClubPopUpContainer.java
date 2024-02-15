package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AddClubPopUpContainer extends BaseElement {

    @FindBy(xpath = "./descendant::div[contains(@class,'add-club-header')]")
    public WebElement clubTitle;

    public AddClubPopUpContainer(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getClubTitleText() {
        return clubTitle.getText();
    }
}
