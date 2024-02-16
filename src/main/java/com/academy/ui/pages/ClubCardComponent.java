package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
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
    protected List<DirectionTagComponent> directionTags;
    protected WebElement description;
    protected WebElement rating;
    protected WebElement address;
    protected WebElement detailsButton;

    public ClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickTitle() {
        getTitle().click();
    }

    public List<DirectionTagComponent> getDirections() {
        if (directionTags == null) {
            directionTags = new ArrayList<>();
            List<WebElement> tags = rootElement.findElements(By.xpath(".//span[contains(@class,'ant-tag')]"));
            for (WebElement tag : tags) {
                directionTags.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directionTags;
    }

    public WebElement getDescription() {
        if (description == null) {
            description = rootElement.findElement(By.xpath(".//descendant::p[contains(@class,'description')]"));
        }
        return description;
    }

    public WebElement getRating() {
        if (rating == null) {
            rating = rootElement.findElement(By.xpath(".//descendant::ul[contains(@class,'rating')]"));
        }
        return rating;
    }

    public WebElement getAddress() {
        if (address == null) {
            address = rootElement.findElement(By.xpath(".//descendant::div[contains(@class,'address')]"));
        }
        return address;
    }

    public void clickAddress() {
        getAddress().click();
    }

    public WebElement getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = rootElement.findElement(By.xpath(".//descendant::a[contains(@class,'details-button')]"));
        }
        return detailsButton;
    }

    public ClubPage clickDetailsButton() {
        getDetailsButton().click();
        return new ClubPage(driver);
    }

}
