package com.academy.ui.pages.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClubDirectionCard {
    public ClubDirectionCard(WebElement root){
        this.rootCard = root;
    }
    protected WebElement rootCard;
    protected WebElement clubCardImage;
    protected WebElement clubCardHeading;
    protected WebElement clubCardText;
    protected WebElement clubCardButton;
    protected WebElement clubCardButtonPointer;
    public WebElement getRootCard(){
        return rootCard;
    }
    public WebElement getClubCardImage(){
        if(clubCardImage ==null){
            clubCardImage = rootCard.findElement(By.xpath(".//div[contains(@class,\"icon-box\")]/img"));
        }
        return clubCardImage;
    }
    public WebElement getClubCardHeading(){
        if(clubCardHeading ==null){
            clubCardHeading = rootCard.findElement(By.xpath(".//div[contains(@class,\"name\")]"));
        }
        return clubCardHeading;
    }
    public WebElement getClubCardText(){
        if(clubCardText ==null){
            clubCardText = rootCard.findElement(By.xpath(".//div[contains(@class,\"description\")]"));
        }
        return clubCardText;
    }
    public WebElement getClubCardButton(){
        if(clubCardButton ==null){
            clubCardButton = rootCard.findElement(By.xpath(".//div[contains(@class,\"details\")]"));
        }
        return clubCardButton;
    }
    public void clickClubCardButton(){
        this.getClubCardButton().click();
    }
    public WebElement getClubCardButtonPointer(){
        if(clubCardButtonPointer ==null){
            clubCardButtonPointer = rootCard.findElement(By.xpath(".//span[@aria-label=\"arrow-right\"]"));
        }
        return clubCardButtonPointer;
    }
    public void clickClubCardButtonPointer(){
        this.getClubCardButtonPointer().click();
    }
}
