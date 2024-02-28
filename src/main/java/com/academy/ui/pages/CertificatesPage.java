package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CertificatesPage extends ProfilePage{
    public CertificatesPage(WebDriver driver) {
        super(driver);
    }
    @Getter
    @FindBy(xpath = "descendant::div[contains(@class, 'contentTitle')]")
    private WebElement title;

    @Getter
    @FindBy(xpath = "descendant::div[contains(@class, 'noMessages')]")
    private WebElement noCertificatesTitle;

}
