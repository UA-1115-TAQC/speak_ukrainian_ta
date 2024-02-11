package com.academy.ui.pages.carousel;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicCarouselComponent extends BaseComponent {
    public BasicCarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    protected WebElement LeftArrowButton;
    protected WebElement RightArrowButton;
    protected List<WebElement> SlickDots;
    protected WebElement SliderContainer;
    public WebElement getLeftArrowButton() {
        if (LeftArrowButton == null) {
            LeftArrowButton = rootElement.findElement(By.xpath("//div[contains(@class,'categories-carousel-block')]/span[@aria-label='arrow-left']"));
        }
        return LeftArrowButton;
    }

    public void clickLeftArrowButton() {
        this.getLeftArrowButton().click();
    }
    public WebElement getRightArrowButton() {
        if (RightArrowButton == null) {
            RightArrowButton = rootElement.findElement(By.xpath("//div[contains(@class,'categories-carousel-block')]/span[@aria-label='arrow-right']"));
        }
        return RightArrowButton;
    }

        public void clickRightArrowButton () {
            this.getRightArrowButton().click();
        }

        public List<WebElement> getSlickDots () {
            if (SlickDots == null) {
                SlickDots = new ArrayList<>();
                List<WebElement> dotsElements = rootElement.findElements(By.xpath("//ul[contains(@class,\"slick-dots\")]/li"));
                for (WebElement dotElement : dotsElements) {
                    SlickDots.add(dotElement);
                }
            }
            return SlickDots;
        }

        public WebElement getSlickDotByIndex ( int index){
            return this.getSlickDots().get(index);
        }

        public void clickSlickDotByIndex ( int index){
            this.getSlickDotByIndex(index).click();
        }
        public WebElement getActiveSlickDot () {
            WebElement activeSlickDot = null;
            for (WebElement dot : this.getSlickDots()) {
                if (Objects.equals(dot.getAttribute("class"), "slick-active")) {
                    activeSlickDot = dot;
                }
            }
            return activeSlickDot;
        }

        public void clickActiveSlickDot () {
            this.getActiveSlickDot().click();
        }

        public WebElement getSliderContainer () {
            if (SliderContainer == null) {
                SliderContainer = rootElement.findElement(By.xpath("//div[contains(@class,\"slick-slider\")]"));
            }
            return SliderContainer;
        }
    }

