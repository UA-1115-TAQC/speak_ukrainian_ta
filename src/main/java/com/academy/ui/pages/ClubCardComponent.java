package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "./descendant::span[@class='ant-tag tag css-k8nnl9'][1]")
    protected WebElement categoryClub;


    @FindBy(xpath = ".//span[contains(@class,'ant-tag')]")
    @Getter(AccessLevel.NONE)private List<WebElement> directionTags;

    protected List<DirectionTagComponent> directions;

    public ClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<DirectionTagComponent> getDirections() {
        if (directions == null) {
            directions = new ArrayList<>();
            for (WebElement tag : directionTags) {
                directions.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directions;
    }

//    public void clickTitle() {
//        getTitle().click();
//    }

    public String getCategoryClub(){
        return categoryClub.getText();
    }

    public void clickAddress() {
        getAddress().click();
    }

    public ClubPage clickDetailsButton() {
        getDetailsButton().click();
        return new ClubPage(driver);
    }

}
