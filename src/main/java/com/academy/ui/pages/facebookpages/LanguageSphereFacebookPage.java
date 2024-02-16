package com.academy.ui.pages.facebookpages;

import com.academy.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class LanguageSphereFacebookPage extends BasePage {
    @FindBy(xpath="//body//a[@aria-label=\"Facebook\"]")
    protected WebElement facebookLogo;
    @FindBy(xpath = "//body//span[contains(text(),'Сімейний фестиваль \"Мовосфера\"')]")
    protected WebElement initiativeHeading;
   public LanguageSphereFacebookPage(WebDriver driver){
       super(driver);
   }
}
