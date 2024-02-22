package com.academy.ui.components;

import com.academy.ui.components.elements.SearchClubDropDownElement;
import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AdvancedSearchSiderComponent extends BaseComponent{
    @FindBy(xpath = ".//div[contains(@class,'club-list-label')]")
    protected WebElement label;

    @FindBy(xpath = ".//span[contains(@class,'ant-typography')]")
    protected List<WebElement> sectionTitles;

    @FindBy(xpath = ".//label[contains(@class,'ant-radio-wrapper')]")
    protected List<WebElement> centerOrClubButton;

    @FindBy(xpath = ".//div[@id='basic_isOnline']")
    protected WebElement onlineCheckBox;

    @FindBy(xpath = ".//div[@id='basic_categoriesName']//label[contains(@class,'ant-checkbox-wrapper')]")
    protected List<WebElement> directionCheckBox;

    @FindBy(xpath = ".//span[@id='basic_age']")
    protected WebElement ageInput;

    @FindBy(xpath = ".//div[contains(@class,'mobile-clear-button')]")
    protected WebElement clearButton;

    @FindBy(xpath = ".//div[contains(@class,'mobile-use-button')]")
    protected WebElement useButton;

    protected SearchClubDropDownElement searchCityDropDown;
    protected SearchClubDropDownElement searchDistrictDropDown;
    protected SearchClubDropDownElement searchMetroStationDropDown;

    public AdvancedSearchSiderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    private SearchClubDropDownElement makeSearchClubDropDownElement(String xpath){
        WebElement root = rootElement.findElement(By.xpath(xpath));
        return new SearchClubDropDownElement(driver, root);
    }

    public SearchClubDropDownElement getSearchCityDropDown(){
        if(searchCityDropDown == null){
            searchCityDropDown = makeSearchClubDropDownElement(
                    "./descendant::div[contains(@class,'ant-select-in-form-item')][1]");
        }
        return searchCityDropDown;
    }

    public SearchClubDropDownElement getSearchDistrictDropDown(){
        if(searchDistrictDropDown == null){
            searchDistrictDropDown = makeSearchClubDropDownElement(
                    "./descendant::div[contains(@class,'ant-select-in-form-item')][2]");
        }
        return searchDistrictDropDown;
    }

    public SearchClubDropDownElement getSearchMetroStationDropDown(){
        if(searchMetroStationDropDown == null){
            searchMetroStationDropDown = makeSearchClubDropDownElement(
                    "./descendant::div[contains(@class,'ant-select-in-form-item')][3]");
        }
        return searchMetroStationDropDown;
    }

    public ClubsPage chooseClubRadioButton(){
        for(WebElement e : getCenterOrClubButton()){
            if(e.getText().equals("Гурток")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickCityClear(){
        getSearchCityDropDown().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickCityDropDown(){
        getSearchCityDropDown().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage clickDistrictClear(){
        getSearchDistrictDropDown().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickDistrictDropDown(){
        getSearchDistrictDropDown().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage clickMetroStationClear(){
        getSearchMetroStationDropDown().clickClear();
        return new ClubsPage(driver);
    }

    public ClubsPage clickMetroStationDropDown(){
        getSearchMetroStationDropDown().clickDropDown();
        return new ClubsPage(driver);
    }

    public ClubsPage chooseCenterRadioButton(){
        for(WebElement e : getCenterOrClubButton()){
            if(e.getText().equals("Центр")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage checkOnlineCheckBox(){
        getOnlineCheckBox().click();
        return new ClubsPage(driver);
    }

    public ClubsPage checkDirection(String direction){
        for(WebElement e : getDirectionCheckBox()){
            String text = e.getText();
            if(text.equals(direction)){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage enterAge(String age){
        getAgeInput().findElement(By.xpath(
                ".//input[contains(@class,'ant-input-number-input')]"))
                .sendKeys(age);
        return new ClubsPage(driver);
    }


//    TODO delete
    public static void main(String[] args){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/clubs");
        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[2]/main/div/div[1]/div[2]/div/div[2]/span[2]"))
                .click();

        WebElement root = driver.findElement(By.xpath(
                "//div[@class='ant-layout-sider-children']"));
        AdvancedSearchSiderComponent as = new AdvancedSearchSiderComponent(driver, root);


        SearchClubDropDownElement s = as.getSearchCityDropDown();
//        s.clickDropDown();
//        s.getDropDownList();
//        System.out.println(s.getScrollbar().getAttribute("style"));
//        System.out.println("***********");

//        s = as.getSearchDistrictDropDown();
//        s.clickDropDown();
//        System.out.println(s.getDropDownList().getAttribute("class"));
//        System.out.println("***********");

        s = as.getSearchMetroStationDropDown();
        s.clickDropDown();
//        s.getDropDownList();
        System.out.println(s.getScrollbar().getAttribute("style"));
        System.out.println("***********");

    }


}
