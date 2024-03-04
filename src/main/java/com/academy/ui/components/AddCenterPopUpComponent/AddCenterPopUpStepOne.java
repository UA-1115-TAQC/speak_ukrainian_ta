package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class AddCenterPopUpStepOne extends AddCenterPopUpContainer{
    @FindBy(xpath = "//input[@id=\"basic_name\"]")
    protected WebElement centerNameInput;
    @FindBy(xpath = "//button[contains(@class,\"add-location-btn\")]")
    protected WebElement addLocationButton;
   protected AddLocationPopUpCenterComponent addLocationPopUpComponent;

    public AddCenterPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public void setTextCenterNameInput(String text){
        getCenterNameInput().sendKeys(text);
    }
    public AddLocationPopUpCenterComponent clickAddLocationButton(){
        getAddLocationButton().click();
      addLocationPopUpComponent = new AddLocationPopUpCenterComponent(driver);
        return addLocationPopUpComponent;
    }
}
