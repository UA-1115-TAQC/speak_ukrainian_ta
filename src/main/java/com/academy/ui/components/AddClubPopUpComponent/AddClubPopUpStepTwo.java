package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
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

    @FindBy(xpath = "./descendant::ul[@class='ant-list-items']/li[@class='ant-list-item']")
    private List<WebElement> locationList;

    @FindBy(xpath = "//div[contains(@class,'ant-message-top')]")
    private WebElement topMessage;

    @FindBy(xpath = "//descendant::div[contains(@class,'modal-add-club')][2]")
    @Getter(AccessLevel.NONE) private WebElement locationPopUp;

    @Getter(AccessLevel.NONE)
    private HashMap<String, DayTimeCheckboxElement> dayTimeCheckboxElementsCollection;
    @Getter(AccessLevel.NONE)
    private List<LocationListElement> listOfLocationElements;
    @Getter(AccessLevel.NONE)
    private AddLocationPopUpComponent addLocationPopUpComponent;
    private AddClubInputElement telephoneInputElement;
    private AddClubInputElement facebookInputElement;
    private AddClubInputElement whatsappInputElement;
    private AddClubInputElement emailInputElement;
    private AddClubInputElement skypeInputElement;
    private AddClubInputElement siteInputElement;

    public AddClubPopUpStepTwo(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.telephoneInputElement = new AddClubInputElement(driver, telephoneInput);
        this.facebookInputElement = new AddClubInputElement(driver, facebookInput);
        this.whatsappInputElement = new AddClubInputElement(driver, whatsappInput);
        this.emailInputElement = new AddClubInputElement(driver, emailInput);
        this.skypeInputElement = new AddClubInputElement(driver, skypeInput);
        this.siteInputElement = new AddClubInputElement(driver, siteInput);
    }

    public AddClubPopUpStepTwo clickSwitchButton(){
        switchButton.click();
        return this;
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

    public AddClubPopUpStepTwo clickOnDayCheckbox(String day){
        dayTimeCheckboxElementsCollection.get(day).getCheckbox().click();
        return this;
    }

    public AddLocationPopUpComponent clickAddLocationButton(){
        addLocationButton.click();
        addLocationPopUpComponent = new AddLocationPopUpComponent(driver, locationPopUp);
        return addLocationPopUpComponent;
    }

    public List<String> getLocationsNameList(){
        List<String> list = new ArrayList<>();
        getListOfLocationElements().forEach(location -> list.add(location.getLocationTitle().getText()));
        return list;
    }
}
