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

    @FindBy(xpath = "//ul[@role='radiogroup']")
    private List<WebElement> radioGroupRating;

    public RatingComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public int getClubRating() {
        for (WebElement ratingElement : radioGroupRating) {
            List<WebElement> fullStarRate = ratingElement.findElements(By.xpath(".//li[@class='ant-rate-star ant-rate-star-full']"));
            if (!fullStarRate.isEmpty()) {
                return fullStarRate.size();
            }
            List<WebElement> zeroStarRate = ratingElement.findElements(By.xpath(".//li[@class='ant-rate-star ant-rate-star-zero']"));
            if (!zeroStarRate.isEmpty()) {
                return 0;
            }
        }
        return -1;
    }
}