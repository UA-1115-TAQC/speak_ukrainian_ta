package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.BasePopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//div[contains(@class,'addCenter')]")
    private WebElement addCenterPopUp;

    public AddCenterPopUpComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'addCenter')]")));
    }

}
