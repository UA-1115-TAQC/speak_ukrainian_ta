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

    @FindBy(xpath = ".//p[contains(@class,'description')]")
    protected WebElement description;

    @FindBy(xpath = ".//ul[contains(@class,'rating')]")
    protected WebElement rating;

    @FindBy(xpath = ".//div[contains(@class,'address')]")
    protected WebElement address;

    @FindBy(xpath = ".//a[contains(@class,'details-button')]")
    protected WebElement detailsButton;

    protected List<DirectionTagComponent> directionTags;

    public ClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<DirectionTagComponent> getDirections() {
        if (directionTags == null) {
            directionTags = new ArrayList<>();
            List<WebElement> tags = rootElement.findElements(By.xpath(
                    ".//span[contains(@class,'ant-tag')]"));
            for (WebElement tag : tags) {
                directionTags.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directionTags;
    }

    public void clickTitle() {
        getTitle().click();
    }

    public void clickAddress() {
        getAddress().click();
    }

    public ClubPage clickDetailsButton() {
        getDetailsButton().click();
        return new ClubPage(driver);
    }

}
