package com.academy.ui.components.loginPopUpComponent;

import com.academy.ui.components.BasePopUp;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RestorationPasswordComponent extends BasePopUp {

    @FindBy(xpath = "./descendant::div[contains(@class, 'login-header')]")
    private WebElement restorationTittle;
    @FindBy(xpath = "./descendant::button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "./descendant::button[@type='button']")
    private WebElement closeButton;

    public RestorationPasswordComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
