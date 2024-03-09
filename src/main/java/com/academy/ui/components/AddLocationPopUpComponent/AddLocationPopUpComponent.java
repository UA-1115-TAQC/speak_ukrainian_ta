package com.academy.ui.components.AddLocationPopUpComponent;

import com.academy.ui.components.BasePopUp;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddLocationPopUpComponent extends BasePopUp {

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement locationTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement locationCityTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement locationCityDistrictTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][4]")
    private WebElement locationMetroTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][5]")
    private WebElement locationAddressTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][6]")
    private WebElement locationCoordinatesTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][7]")
    private WebElement locationTelephoneTitle;

    @FindBy(xpath = "./descendant::button[@type='submit']")
    private WebElement addLocationButton;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][1]")
    @Getter(AccessLevel.NONE) private WebElement locationNameInput;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][2]")
    @Getter(AccessLevel.NONE) private WebElement locationCityDropdown;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][3]")
    @Getter(AccessLevel.NONE) private WebElement locationDistrictDropdown;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][4]")
    @Getter(AccessLevel.NONE) private WebElement locationMetroDropdown;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][5]")
    @Getter(AccessLevel.NONE) private WebElement locationAddressInput;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][6]")
    @Getter(AccessLevel.NONE) private WebElement locationCoordinatesInput;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')][7]")
    @Getter(AccessLevel.NONE) private WebElement locationTelephoneInput;

    private AddLocationInputElement locatioNameInputElement;
    private DropdownElement locatioCityDropdownElement;
    private DropdownElement locationDistrictDropdownElement;
    private DropdownElement locationMetroDropdownElement;
    private AddLocationInputElement locationAddressInputElement;
    private AddLocationInputElement locationCoordinatesInputElement;
    private AddLocationInputElement locationTelephoneInputElement;

    public AddLocationPopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.locatioNameInputElement = new AddLocationInputElement(driver,locationNameInput);
        this.locatioCityDropdownElement = new DropdownElement(driver, locationCityDropdown);
        this.locationDistrictDropdownElement = new DropdownElement(driver, locationDistrictDropdown);
        this.locationMetroDropdownElement = new DropdownElement(driver, locationMetroDropdown);
        this.locationAddressInputElement = new AddLocationInputElement(driver, locationAddressInput);
        this.locationCoordinatesInputElement = new AddLocationInputElement(driver, locationCoordinatesInput);
        this.locationTelephoneInputElement = new AddLocationInputElement(driver, locationTelephoneInput);
    }

    @Step("Click on button 'Додати' to add location on the Add location pop-up")
    public void clickAddLocationButton(){
        addLocationButton.click();
    }

}
