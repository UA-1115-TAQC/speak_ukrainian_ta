package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.BasePopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddCenterPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//div[contains(@class,'addCenter')]")
    private WebElement addCenterPopUp;

    @FindBy(xpath = "./descendant::div[@class='side']")
    private WebElement siderElement;

    private AddCenterPopUpSider sider;

    public AddCenterPopUpComponent(WebDriver driver) {
        super(driver, driver.findElement(By.xpath("//div[contains(@class,'addCenter')]")));
        sider = new AddCenterPopUpSider(driver, siderElement);
    }

}
