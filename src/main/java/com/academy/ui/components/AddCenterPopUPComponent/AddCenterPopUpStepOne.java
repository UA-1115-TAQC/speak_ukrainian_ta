package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AddCenterPopUpStepOne extends AddCenterPopUpContainer {

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement centerInputTitle;

    @FindBy(xpath = ".//input[@id='basic_name']")
    private WebElement centerNameInput;

    @FindBy(xpath = ".//form[@id='basic']/descendant::span[contains(@class,'anticon-info-circle')]")
    private WebElement infoCircleHintIcon;

    @FindBy(xpath = "//div[contains(@class, 'ant-tooltip ') and not(contains(@class, 'ant-tooltip-hidden'))]//div[@class='ant-tooltip-inner']")
    private WebElement infoHintTooltip;

    @FindBy(xpath = ".//div[(@id='basic_name_help')]/descendant::div[@class='ant-form-item-explain-error']")
    private WebElement nameErrorMessage;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement centerLocationTitle;

    @FindBy(xpath = "./descendant::button[contains(@class,'add-location-btn')]")
    private WebElement addLocationButton;

    @FindBy(xpath = "./descendant::div[@id='basic_locations_help']")
    private WebElement noDataLocationElement;

    @FindBy(xpath = ".//div[(@id='basic_locations_help')]/descendant::div[@class='ant-form-item-explain-error']")
    private WebElement locationErrorMessage;

    @FindBy(xpath = ".//div[(@id='basic_locations')]/div[@class='checkbox-item']/label")
    private List<WebElement> locationsElementsList;

    @FindBy(xpath = ".//div[(@id='basic_locations')]/descendant::input[@class='ant-checkbox-input']")
    private List<WebElement> locationsCheckboxesList;

    @FindBy(xpath = ".//div[(@id='basic_locations')]/descendant::label[contains(@class,'ant-checkbox-wrapper-checked')]")
    private List<WebElement> checkedLocationsList;


    @Getter(AccessLevel.NONE)
    private AddLocationPopUpComponent addLocationPopUpComponent;

    public AddCenterPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddLocationPopUpComponent clickAddLocationButton() {
        addLocationButton.click();
        addLocationPopUpComponent = new AddLocationPopUpComponent(driver);
        return addLocationPopUpComponent;
    }

    public AddCenterPopUpStepOne clickLocationCheckboxByName(String name) {
        locationsElementsList.stream()
                .filter(location -> (location.getAttribute("innerText").equals(name)))
                .forEach(WebElement::click);
        return this;
    }

}
