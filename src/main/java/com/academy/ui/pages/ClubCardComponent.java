package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClubCardComponent extends BaseComponent {
    protected WebElement title;
    protected List<WebElement> directions;
    protected WebElement description;
    protected WebElement rating;
    protected WebElement address;
    protected WebElement detailsButton;

    public ClubCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getTitle(){
        if (title == null) {
            title = rootElement.findElement(By.xpath(
                    ".//descendant::div[contains(@class,'title')]"));
        }
        return title;
    }

    public void clickTitle(){
        getTitle().click();
    }

    public List<WebElement> getDirections(){
        if(directions == null){
            directions = rootElement.findElements(By.xpath(
                   ".//descendant::span[contains(@class,'ant-tag')]"));
        }
        return directions;
    }

    public WebElement getDescription(){
        if (description == null) {
            description = rootElement.findElement(By.xpath(
                    ".//descendant::p[contains(@class,'description')]"));
        }
        return description;
    }

    public WebElement getRating(){
        if (rating == null) {
            rating = rootElement.findElement(By.xpath(
                    ".//descendant::ul[contains(@class,'rating')]"));
        }
        return rating;
    }

    public WebElement getAddress(){
        if (address == null) {
            address = rootElement.findElement(By.xpath(
                    ".//descendant::div[contains(@class,'address')]"));
        }
        return address;
    }

    public void clickAddress(){
        getAddress().click();
    }

    public WebElement getDetailsButton(){
        if (detailsButton == null) {
            detailsButton = rootElement.findElement(By.xpath(
                    ".//descendant::a[contains(@class,'details-button')]"));
        }
        return detailsButton;
    }

    public void clickDetailsButton(){
        getDetailsButton().click();
    }

}
