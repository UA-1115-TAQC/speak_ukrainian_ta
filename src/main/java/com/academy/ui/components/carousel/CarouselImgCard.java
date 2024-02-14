package com.academy.ui.components.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CarouselImgCard {
    public CarouselImgCard( WebElement rootImgCard){
        this.rootImgCard = rootImgCard;
    }
    protected WebElement rootImgCard;
    protected WebElement BackgroundImage;
    protected WebElement cardHeading;
    protected WebElement cardText;
    protected WebElement CardButton;

    public WebElement getBackgroundImage() {
        if(BackgroundImage == null){
            BackgroundImage = rootImgCard.findElement(By.xpath(".//div[@class=\"carousel-item\"]"));
        }
        return BackgroundImage;
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
        if(CardButton == null){
            CardButton = rootImgCard.findElement(By.xpath(".//a"));
        }
        return CardButton;
    }
    public void clickCardButton(){
        this.getCardButton().click();
    }
}
