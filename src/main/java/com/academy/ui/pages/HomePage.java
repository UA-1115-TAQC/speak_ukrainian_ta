package com.academy.ui.pages;

import com.academy.ui.pages.carousel.CarouselCardComponent;
import com.academy.ui.pages.carousel.CarouselImgComponent;
import com.academy.ui.pages.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public CarouselCardComponent carouselCardComponent;
    public CarouselImgComponent carouselImgComponent;
    public HomePage(WebDriver driver) {
        super(driver);
        this.carouselCardComponent = getCarouselCardComponent();
        this.carouselImgComponent = getCarouselImgComponent();
    }
    public CarouselCardComponent getCarouselCardComponent() {
        if (carouselCardComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class,\"categories-carousel-block\")]"));
            carouselCardComponent = new CarouselCardComponent(driver, node);
        }
        return carouselCardComponent;
    }
    public CarouselImgComponent getCarouselImgComponent(){
        if (carouselImgComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class,\"about-carousel-block\")]"));
            carouselImgComponent = new CarouselImgComponent(driver, node);
        }
        return carouselImgComponent;
    }
}
