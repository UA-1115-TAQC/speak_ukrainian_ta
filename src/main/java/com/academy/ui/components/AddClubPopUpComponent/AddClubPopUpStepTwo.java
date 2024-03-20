package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class AddClubPopUpStepTwo extends AddClubPopUpContainer {

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement clubLocationsTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement clubAvailableOnlineTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement clubWorkHoursTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][4]")
    private WebElement clubContactsTitle;

    @FindBy(xpath = ".//div[contains(@class,'ant-empty-normal')]")
    private WebElement noDataLocationElement;

    @FindBy(xpath = ".//span[contains(@class,'add-club-location')]")
    private WebElement addLocationButton;

    @FindBy(xpath = ".//button[contains(@class,'ant-switch')]")
    private WebElement switchButton;

    @FindBy(xpath = ".//span[contains(@class,'anticon-info-circle')]")
    private WebElement infoHintIcon;

    @FindBy(xpath = "//div[contains(@class,'ant-tooltip-inner')]")
    private WebElement infoHintIconText;

    @FindBy(xpath = ".//div[@id='basic_workDay']//input[@class='ant-checkbox-input']")
    private List<WebElement> workDaysCheckboxList;

    @FindBy(xpath = ".//span[contains(@class,'ant-checkbox-checked')]/ancestor::div[@class='checkbox-item']")
    private List<WebElement> checkedWorkDaysList;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/div[@class='checkbox-item']")
    @Getter(AccessLevel.NONE)
    private List<WebElement> dayTimeCheckboxList;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][1]")
    @Getter(AccessLevel.NONE)
    private WebElement telephoneInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][2]")
    @Getter(AccessLevel.NONE)
    private WebElement facebookInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][3]")
    @Getter(AccessLevel.NONE)
    private WebElement whatsappInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][4]")
    @Getter(AccessLevel.NONE)
    private WebElement emailInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][5]")
    @Getter(AccessLevel.NONE)
    private WebElement skypeInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][6]")
    @Getter(AccessLevel.NONE)
    private WebElement siteInput;

    @FindBy(xpath = ".//ul[@class='ant-list-items']/li[@class='ant-list-item']")
    private List<WebElement> locationList;

    @FindBy(xpath = "//descendant::div[contains(@class,'modal-add-club')][2]")
    @Getter(AccessLevel.NONE)
    private WebElement locationPopUp;

    @Getter(AccessLevel.NONE)
    private HashMap<String, DayTimeCheckboxElement> dayTimeCheckboxElementsCollection;

    @Getter(AccessLevel.NONE)
    private List<LocationListElement> listOfLocationElements;

    @Getter(AccessLevel.NONE)
    private AddLocationPopUpComponent addLocationPopUpComponent;

    private final AddClubInputElement telephoneInputElement;
    private final AddClubInputElement facebookInputElement;
    private final AddClubInputElement whatsappInputElement;
    private final AddClubInputElement emailInputElement;
    private final AddClubInputElement skypeInputElement;
    private final AddClubInputElement siteInputElement;

    public AddClubPopUpStepTwo(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.telephoneInputElement = new AddClubInputElement(driver, telephoneInput);
        this.facebookInputElement = new AddClubInputElement(driver, facebookInput);
        this.whatsappInputElement = new AddClubInputElement(driver, whatsappInput);
        this.emailInputElement = new AddClubInputElement(driver, emailInput);
        this.skypeInputElement = new AddClubInputElement(driver, skypeInput);
        this.siteInputElement = new AddClubInputElement(driver, siteInput);
    }

    @Step("Click on switch button 'Доступний онлайн' on the second step of Add/Edit club pop-up")
    public AddClubPopUpStepTwo clickSwitchButton() {
        switchButton.click();
        return this;
    }

    @Step("Check if switch button 'Доступний онлайн' is checked on the second step of Add/Edit club pop-up")
    public Boolean isSwitchButtonChecked() {
        return switchButton.getAttribute("aria-checked").equals("true");
    }

    @Step("Get list of locations on the second step of Add/Edit club pop-up")
    public List<LocationListElement> getListOfLocationElements() {
        listOfLocationElements = new ArrayList<>();
        if (!locationList.isEmpty()) {
            locationList.forEach(location -> listOfLocationElements.add(new LocationListElement(driver, location)));
        }
        return listOfLocationElements;
    }

    public AddLocationPopUpComponent clickAddLocationButton() {
        addLocationButton.click();
        addLocationPopUpComponent = new AddLocationPopUpComponent(driver, locationPopUp);
        return addLocationPopUpComponent;
    }

    @Step("Get list of location names on the second step of Add/Edit club pop-up")
    public List<String> getLocationsNameList() {
        List<String> list = new ArrayList<>();
        getListOfLocationElements().forEach(location -> list.add(location.getLocationItemTitle().getText()));
        return list;
    }

    @Step("Get Day and Time collection on the second step of Add/Edit club pop-up")
    public HashMap<String, DayTimeCheckboxElement> getDayTimeCheckboxElementsCollection() {
        dayTimeCheckboxElementsCollection = new HashMap<>();
        By locator = By.xpath(".//input[@class='ant-checkbox-input']");
        for (WebElement day : dayTimeCheckboxList) {
            dayTimeCheckboxElementsCollection.put(
                    day.findElement(locator).getAttribute("value"),
                    new DayTimeCheckboxElement(driver, day)
            );
        }
        return dayTimeCheckboxElementsCollection;
    }

    @Step("Click on the button 'Додати локацію' on the second step of Add/Edit club pop-up")
    public AddClubPopUpStepTwo clickOnCheckboxByDay(String day) {
        dayTimeCheckboxElementsCollection.get(day.toUpperCase()).getCheckbox().click();
        return this;
    }

    @Step("Click on the previous step button")
    @Override
    public AddClubPopUpStepOne clickPreviousStepButton() {
        getPreviousStepButton().click();
        return new AddClubPopUpStepOne(driver, rootElement);
    }

    @Step("Click on the next step button")
    @Override
    public AddClubPopUpStepThree clickNextStepButton() {
        getNextStepButton().click();
        return new AddClubPopUpStepThree(driver, rootElement);
    }


}
