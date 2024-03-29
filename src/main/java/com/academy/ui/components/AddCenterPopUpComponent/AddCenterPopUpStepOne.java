package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.AddClubPopUpComponent.LocationListElement;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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

    @FindBy(xpath = "./descendant::span[@class='add-club-location']")
    private WebElement addLocationButtonInEditCenter;

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

    @FindBy(xpath = "//descendant::div[contains(@class,'modal-add-club')]")
    @Getter(AccessLevel.NONE)
    private WebElement locationPopUp;

    @Getter(AccessLevel.NONE)
    private AddLocationPopUpComponent addLocationPopUpComponent;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-items']/li[@class='ant-list-item']")
    private List<WebElement> locationList;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement locationTitle;

    @Getter(AccessLevel.NONE)
    private List<LocationListElement> listOfLocationElements;

    public AddCenterPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click on the button 'Додати локацію' on the first step of Add/Edit center pop-up")
    public AddLocationPopUpComponent clickAddLocationButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addLocationButton)).click();
        addLocationPopUpComponent = new AddLocationPopUpComponent(driver, locationPopUp);
        return addLocationPopUpComponent;
    }

    @Step("Click on location checkbox by name {name} on the first step of Add/Edit center pop-up")
    public AddCenterPopUpStepOne clickLocationCheckboxByName(String name) {
        locationsElementsList.stream()
                .filter(location -> (location.getAttribute("innerText").equals(name)))
                .forEach(WebElement::click);
        return this;
    }

    @Step("Set center name {name} input on the first step of Add/Edit center pop-up")
    public AddCenterPopUpStepOne setCenterName(String name) {
        centerNameInput.sendKeys(name);
        return this;
    }

    @Step("Clear center name input on the first step of Add/Edit center pop-up")
    public AddCenterPopUpStepOne clearNameInput() {
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            centerNameInput.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            centerNameInput.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }

    @Step("Get list of locations on the first step of Add/Edit center pop-up")
    public List<String> getLocationsNameList() {
        List<String> list = new ArrayList<>();
        locationsElementsList.forEach(location -> list.add(location.getText()));
        return list;
    }

    @Step("Get list of locations on the second step of Add/Edit club pop-up")
    public List<LocationListElement> getListOfLocationElements() {
        listOfLocationElements = new ArrayList<>();
        if (!locationList.isEmpty()) {
            locationList.forEach(location -> listOfLocationElements.add(new LocationListElement(driver, location)));
        }
        return listOfLocationElements;
    }
}
