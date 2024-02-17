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
    @FindBy(xpath = "./descendant::button[@class='ant-modal-close']")
    private WebElement restorationCloseButton;

    public RestorationPasswordComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
