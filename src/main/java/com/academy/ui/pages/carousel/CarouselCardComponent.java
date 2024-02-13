package com.academy.ui.pages.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarouselCardComponent extends BasicCarouselComponent{
  public  CarouselCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    protected WebElement carouselCardHeading;
  protected WebElement carouselCardAllClubsButton;
    public WebElement getCarouselCardHeading(){
        if(carouselCardHeading == null){
            carouselCardHeading = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/h2"));
        }
        return carouselCardHeading;
    }
    public WebElement getCarouselCardAllClubsButton(){
        if(carouselCardAllClubsButton == null){
            carouselCardAllClubsButton = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/a/button"));
        }
        return carouselCardAllClubsButton;
    }
    public void clickCarouselCardAllClubsButton(){
        this.getCarouselCardAllClubsButton().click();
    }
}
