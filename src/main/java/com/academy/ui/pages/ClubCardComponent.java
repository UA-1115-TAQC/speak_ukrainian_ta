package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.ClubInfoPopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClubCardComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@class='title']//img")
    protected WebElement logo;

    @FindBy(xpath = ".//div[contains(@class,'title')]")
    protected WebElement title;

    @FindBy(xpath = ".//p[contains(@class,'description')]")
    protected WebElement description;

    @FindBy(xpath = ".//ul[contains(@class,'rating')]")
    protected WebElement rating;

    @FindBy(xpath = ".//div[contains(@class,'address')]")
    protected WebElement address;


    @FindBy(xpath = "./descendant::div[@class='club-online']")
    protected WebElement clubOnline;

    @FindBy(xpath = ".//*[contains(@class,'details-button')]")
    protected WebElement detailsButton;

    @FindBy(xpath = ".//span[contains(@class,'ant-tag')]")
    private List<WebElement> directionTags;

    @FindBy(xpath = "//div[@class='ant-modal-root css-1kvr9ql']")
    @Getter(AccessLevel.NONE)private WebElement popUpWebElement;

    @FindBy(xpath = ".//span[@class='club-online-label']")
    protected WebElement onlineLabel;

    @FindBy(xpath = ".//div[@class='ant-card-body']/child::*")
    protected List<WebElement> cardChildList;

    @Getter(AccessLevel.NONE)
    protected List<DirectionTagComponent> directions;

    public ClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<DirectionTagComponent> getDirections() {
        directions = new ArrayList<>();
        for (WebElement tag : directionTags) {
            directions.add(new DirectionTagComponent(driver, tag));
        }
        return directions;
    }

    public boolean directionsContains(String text){
        getDirections();
        for (DirectionTagComponent direction : directions) {
            if(direction.getNameText().toLowerCase().contains(text.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public String getClubName(){
        return getTitle().getText();
    }

    public boolean clubNameContains(String text){
        return getClubName().toLowerCase().contains(text.toLowerCase());
    }

    public boolean descriptionContains(String text){
        return getDescription().getText().toLowerCase().contains(text.toLowerCase());
    }

    public ClubInfoPopUp clickTitle() {
        getTitle().click();
        return new ClubInfoPopUp(driver);
    }

    public void clickAddress() {
        getAddress().click();
    }

    public ClubPage clickDetailsButton() {
        getDetailsButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("club"));
        return new ClubPage(driver);
    }

}
