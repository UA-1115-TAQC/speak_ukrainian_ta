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
            String rootElementClass = "categories-carousel-block";
            WebElement node = driver.findElement(By.xpath(String.format("//div[contains(@class,\"%s\")]",rootElementClass)));
            carouselCardComponent = new CarouselCardComponent(driver, node,rootElementClass);
        }
        return carouselCardComponent;
    }
    public CarouselImgComponent getCarouselImgComponent(){
        if (carouselImgComponent == null) {
            String rootElementClass = "about-carousel-block";
            WebElement node = driver.findElement(By.xpath(String.format("//div[contains(@class,\"%s\")]",rootElementClass)));
            carouselImgComponent = new CarouselImgComponent(driver, node, rootElementClass);
        }
        return carouselImgComponent;
    }
}
