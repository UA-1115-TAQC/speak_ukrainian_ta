package com.academy.ui.components;

import com.academy.ui.components.elements.BaseInputElement;
import com.academy.ui.components.elements.LocationSearchSiderElement;
import com.academy.ui.pages.ClubsPage;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AdvancedSearchSiderComponent extends BaseComponent{
    @FindBy(xpath = ".//div[contains(@class,'club-list-label')]")
    protected WebElement label;

    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Гурток/Центр']")
    protected WebElement clubOrCenterTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Місто']")
    protected WebElement cityTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Район міста']")
    protected WebElement districtTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Найближча станція метро']")
    protected WebElement metroStationTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Ремоут']")
    protected WebElement onlineTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Категорії']")
    protected WebElement directionsTitle;
    @FindBy(xpath = ".//span[contains(@class,'ant-typography') and text()='Вік дитини']")
    protected WebElement ageTitle;

    @FindBy(xpath = ".//label[contains(@class,'ant-radio-wrapper')]")
    protected List<WebElement> centerOrClubRadioButton;
    @FindBy(xpath = ".//div[@id='basic_isOnline']")
    protected WebElement onlineCheckBox;
    @FindBy(xpath = ".//div[@id='basic_categoriesName']//label[contains(@class,'ant-checkbox-wrapper')]")
    protected List<WebElement> directionsCheckBox;
    @FindBy(xpath = ".//span[@id='basic_age']//input[contains(@class,'ant-input-number-input')]")
    protected WebElement ageInput;
    @FindBy(xpath = ".//div[contains(@class,'mobile-clear-button')]")
    protected WebElement clearButton;
    @FindBy(xpath = ".//div[contains(@class,'mobile-use-button')]")
    protected WebElement useButton;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-select-in-form-item')][1]")
    @Getter(AccessLevel.NONE)private WebElement searchCityBox;
    @FindBy(xpath = "./descendant::div[contains(@class,'ant-select-in-form-item')][2]")
    @Getter(AccessLevel.NONE)private WebElement searchDistrictBox;
    @FindBy(xpath = "./descendant::div[contains(@class,'ant-select-in-form-item')][3]")
    @Getter(AccessLevel.NONE)private WebElement searchMetroBox;

    protected LocationSearchSiderElement searchCityElement;
    protected LocationSearchSiderElement searchDistrictElement;
    protected LocationSearchSiderElement searchMetroElement;

    public AdvancedSearchSiderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LocationSearchSiderElement getSearchCityElement(){
        if(searchCityElement == null){
            searchCityElement = new LocationSearchSiderElement(driver, searchCityBox);
        }
        return searchCityElement;
    }

    public LocationSearchSiderElement getSearchDistrictElement(){
        if(searchDistrictElement == null){
            searchDistrictElement = new LocationSearchSiderElement(driver, searchDistrictBox);
        }
        return searchDistrictElement;
    }

    public LocationSearchSiderElement getSearchMetroElement(){
        if(searchMetroElement == null){
            searchMetroElement = new LocationSearchSiderElement(driver, searchMetroBox);
        }
        return searchMetroElement;
    }

    public ClubsPage chooseRadioButton(String buttonName){
        for(WebElement e : getCenterOrClubRadioButton()){
            if(e.getText().equals(buttonName)){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickCityClear(){
        getSearchCityElement().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickCityDropDown(){
        getSearchCityElement().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage selectItemCity(String itemName){
        getSearchCityElement().selectItem(itemName);
        return new ClubsPage(driver);
    }

    public ClubsPage clickDistrictClear(){
        getSearchDistrictElement().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickDistrictDropDown(){
        getSearchDistrictElement().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage selectItemDistrict(String itemName){
        getSearchDistrictElement().selectItem(itemName);
        return new ClubsPage(driver);
    }

    public ClubsPage clickMetroClear(){
        getSearchMetroElement().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickMetroDropDown(){
        getSearchMetroElement().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage selectItemMetro(String itemName){
        getSearchMetroElement().selectItem(itemName);
        return new ClubsPage(driver);
    }

    public ClubsPage checkOnlineCheckBox(){
        getOnlineCheckBox().click();
        return new ClubsPage(driver);
    }

    public ClubsPage checkDirectionCheckBox(String direction){
        for(WebElement e : getDirectionsCheckBox()){
            String text = e.getText();
            if(text.equals(direction)){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public boolean  isDirectionBoxChecked(String direction){
        for(WebElement e : getDirectionsCheckBox()){
            String text = e.getText();
            if(text.equals(direction)){
                String classStr = e.getAttribute("class");
                return classStr.contains("checked");
            }
        }
        return false;
    }

    public ClubsPage enterAge(String age){
        getAgeInput().sendKeys(age);
        return new ClubsPage(driver);
    }

    public AdvancedSearchSiderComponent clearAgeInput() {
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            ageInput.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            ageInput.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }

}
