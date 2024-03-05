package com.academy.ui.components;

import com.academy.ui.pages.ClubCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileClubCardComponent extends ClubCardComponent {
    @FindBy(xpath = ".//div[contains(@class,'title-name')]")
    private WebElement title;

    public ProfileClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public String getClubName(){
        return this.title.getText();
    }
}
