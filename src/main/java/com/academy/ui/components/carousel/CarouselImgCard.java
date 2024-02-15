package com.academy.ui.components.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CarouselImgCard {
    public CarouselImgCard( WebElement rootImgCard){
        this.rootImgCard = rootImgCard;
    }
    protected WebElement rootImgCard;
    protected WebElement backgroundImage;
    protected WebElement cardHeading;
    protected WebElement cardText;
    protected WebElement cardButton;

    public WebElement getBackgroundImage() {
        if(backgroundImage == null){
            backgroundImage = rootImgCard.findElement(By.xpath(".//div[@class=\"carousel-item\"]"));
        }
        return backgroundImage;
    }
    public WebElement getCardHeading(){
        if(cardHeading==null){
            cardHeading = rootImgCard.findElement(By.xpath(".//h2"));
        }
        return cardHeading;
    }
    public WebElement getCardText(){
        if(cardText==null){
            cardText = rootImgCard.findElement(By.xpath(".//span[contains(@class,\"description\")]"));
        }
        return cardText;
    }
    public WebElement getCardButton(){
        if(cardButton == null){
            cardButton = rootImgCard.findElement(By.xpath(".//a"));
        }
        return cardButton;
    }
    public void clickCardButton(){
        this.getCardButton().click();
    }
}
