package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.ClubInfoPopUp;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClubCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class,'title')]")
    protected WebElement title;

    @FindBy(xpath = ".//p[contains(@class,'description')]")
    protected WebElement description;

    @FindBy(xpath = ".//ul[contains(@class,'rating')]")
    protected WebElement rating;

    @FindBy(xpath = ".//div[contains(@class,'address')]")
    protected WebElement address;

    @FindBy(xpath = ".//a[contains(@class,'details-button')]")
    protected WebElement detailsButton;

    @FindBy(xpath = ".//span[contains(@class,'ant-tag')]")
    private List<WebElement> directionTags;

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

    public ClubInfoPopUp clickTitle() {
        getTitle().click();
        return new ClubInfoPopUp(driver);
    }

    public void clickAddress() {
        getAddress().click();
    }

    public ClubPage clickDetailsButton() {
        getDetailsButton().click();
        return new ClubPage(driver);
    }

    public List<String> getListOfDirectionsTitles() {
        List<String> list = new ArrayList<>();
        getDirections().forEach(direction -> list.add(direction.getName().getText()));
        return list;
    }
}
