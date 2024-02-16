package com.academy.ui.components.carousel;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class CarouselImgCard extends BaseComponent {
    public CarouselImgCard(WebDriver driver, WebElement rootImgCard){
        super(driver, rootImgCard);
    }
    @FindBy(xpath = ".//div[@class=\"carousel-item\"]")
    protected WebElement backgroundImage;
    @FindBy(xpath = ".//h2")
    protected WebElement cardHeading;
    @FindBy(xpath = ".//span[contains(@class,\"description\")]")
    protected WebElement cardText;
    @FindBy(xpath = ".//a/button")
    protected WebElement cardButton;
    public void clickCardButton(){
        this.getCardButton().click();
    }
}
