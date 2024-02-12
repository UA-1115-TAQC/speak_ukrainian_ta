package com.academy.ui.pages.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarouselCardComponent extends BasicCarouselComponent{
  public  CarouselCardComponent(WebDriver driver, WebElement rootElement, String rootElementClass) {
        super(driver, rootElement, rootElementClass);
    }
    protected WebElement CarouselCardHeading;
  protected WebElement CarouselCardAllClubsButton;
    public WebElement getCarouselCardHeading(){
        if(CarouselCardHeading == null){
            CarouselCardHeading = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/h2"));
        }
        return CarouselCardHeading;
    }
    public WebElement getCarouselCardAllClubsButton(){
        if(CarouselCardAllClubsButton == null){
            CarouselCardAllClubsButton = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/a/button"));
        }
        return CarouselCardAllClubsButton;
    }
    public void clickCarouselCardAllClubsButton(){
        this.getCarouselCardAllClubsButton().click();
    }
}
