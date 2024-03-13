package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AdminGenerateCertificatePage extends BasePage{
    @FindBy(xpath = "//label[@for=\"basic_hours\"]")
    private WebElement studyDurationLabel;
    @FindBy(xpath = "//input[@name=\"hours\"]")
    private WebElement studyDurationInput;
    public AdminGenerateCertificatePage(WebDriver driver){
        super(driver);
    }
}
