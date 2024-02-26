package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class RatingComponent extends BasePopUp {

    @FindBy(xpath = ".//li[@class='ant-rate-star ant-rate-star-full']")
    private WebElement fullStarRate;

    @FindBy(xpath = ".//li[@class='ant-rate-star ant-rate-star-half']")
    private WebElement halfStarRate;

    @FindBy(xpath = ".//li[@class='ant-rate-star ant-rate-star-zero']")
    private WebElement zeroStarRate;

    public RatingComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

}