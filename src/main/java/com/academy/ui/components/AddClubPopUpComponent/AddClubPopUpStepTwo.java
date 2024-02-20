package com.academy.ui.components.AddClubPopUpComponent;

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

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-empty-normal')]")
    private WebElement noDataLocationElement;

    @FindBy(xpath = "./descendant::span[contains(@class,'add-club-location')]")
    private WebElement addLocationButton;

    @FindBy(xpath = "./descendant::button[contains(@class,'ant-switch')]")
    private WebElement switchButton;

    @FindBy(xpath = "//descendant::span[contains(@class,'anticon-info-circle')]")
    private WebElement infoHintIcon;

    @FindBy(xpath = "//descendant::div[contains(@class,'ant-tooltip-inner')]")
    private WebElement infoHintIconText;

    @FindBy(xpath = "./descendant::div[@id='basic_workDay']//input[@class='ant-checkbox-input']")
    private List<WebElement> workDaysCheckboxList;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-checkbox-checked')]/ancestor::div[@class='checkbox-item']")
    private List<WebElement> checkedWorkDaysList;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-col')]/div[@class='checkbox-item']")
    @Getter(AccessLevel.NONE)
    private List<WebElement> dayTimeCheckboxList;

    @FindBy(xpath = "./descendant::input[@id='basic_contactТелефон']")
    @Getter(AccessLevel.NONE)
    private WebElement telephoneInput;

    @FindBy(xpath = "./descendant::input[@id='basic_contactFacebook']")
    @Getter(AccessLevel.NONE)
    private WebElement facebookInput;

    @FindBy(xpath = "./descendant::input[@id='basic_contactWhatsApp']")
    @Getter(AccessLevel.NONE)
    private WebElement whatsappInput;

    @FindBy(xpath = "./descendant::input[@id='basic_contactПошта']")
    @Getter(AccessLevel.NONE)
    private WebElement emailInput;

    @FindBy(xpath = "./descendant::input[@id='basic_contactSkype']")
    @Getter(AccessLevel.NONE)
    private WebElement skypeInput;

    @FindBy(xpath = "./descendant::input[@id='basic_contactSite']")
    @Getter(AccessLevel.NONE)
    private WebElement siteInput;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-items']/li[@class='ant-list-item']")
    private List<WebElement> locationList;

    @Getter(AccessLevel.NONE)
    private HashMap<String, DayTimeCheckboxElement> dayTimeCheckboxElementsCollection;
    @Getter(AccessLevel.NONE)
    private List<LocationListElement> listOfLocationElements;

    private InputElement telephoneInputElement;
    private InputElement facebookInputElement;
    private InputElement whatsappInputElement;
    private InputElement emailInputElement;
    private InputElement skypeInputElement;
    private InputElement siteInputElement;

    public AddClubPopUpStepTwo(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.telephoneInputElement = new InputElement(driver, telephoneInput);
        this.facebookInputElement = new InputElement(driver, facebookInput);
        this.whatsappInputElement = new InputElement(driver, whatsappInput);
        this.emailInputElement = new InputElement(driver, emailInput);
        this.skypeInputElement = new InputElement(driver, skypeInput);
        this.siteInputElement = new InputElement(driver, siteInput);
    }

    public Boolean isSwitchButtonChecked() {
        return switchButton.getAttribute("aria-checked").equals("true");
    }

    public HashMap<String, DayTimeCheckboxElement> getDayTimeCheckboxElementsCollection() {
        dayTimeCheckboxElementsCollection = new HashMap<>();
        By locator = By.xpath("./descendant::input[@class='ant-checkbox-input']");
        for (WebElement day : dayTimeCheckboxList) {
            dayTimeCheckboxElementsCollection.put(
                    day.findElement(locator).getAttribute("value"),
                    new DayTimeCheckboxElement(driver, day)
            );
        }
        return dayTimeCheckboxElementsCollection;
    }

    public List<LocationListElement> getListOfLocationElements() {
        listOfLocationElements = new ArrayList<>();
        if (!locationList.isEmpty()) {
            locationList.forEach(location -> listOfLocationElements.add(new LocationListElement(driver, location)));
        }
        return listOfLocationElements;
    }

}
