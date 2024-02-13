package com.academy.ui.pages.carousel;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicCarouselComponent extends BaseComponent {
    public BasicCarouselComponent(WebDriver driver, WebElement rootElement ) {
        super(driver, rootElement);
    }
    protected WebElement leftArrowButton;
    protected WebElement rightArrowButton;
    protected List<WebElement> slickDots;
    protected WebElement sliderContainer;
    public WebElement getLeftArrowButton() {
        if (leftArrowButton == null) {
            leftArrowButton = rootElement.findElement(By.xpath(".//span[contains(@aria-label, 'arrow-left')]"));
        }
        return leftArrowButton;
    }
    public void clickLeftArrowButton() {
        this.getLeftArrowButton().click();
    }
    public WebElement getRightArrowButton() {
        if (rightArrowButton == null) {
            rightArrowButton = rootElement.findElement(By.xpath(".//span[contains(@aria-label, 'arrow-right')]"));
        }
        return rightArrowButton;
    }
    public void clickRightArrowButton () {
        this.getRightArrowButton().click();
    }
    public List<WebElement> getSlickDots () {
        if (slickDots == null) {
            slickDots = new ArrayList<>();
            slickDots.addAll(rootElement.findElements(By.xpath(".//ul[contains(@class,\"slick-dots\")]/li")));
        }
        return slickDots;
    }
    public WebElement getSlickDotByIndex (int index){
        if(index >=0 && index < this.getSlickDots().size()) {
            return this.getSlickDots().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (this.getSlickDots().size()-1)+", inclusive");
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
        if (sliderContainer == null) {
            sliderContainer = rootElement.findElement(By.xpath(".//div[contains(@class,\"slick-slider\")]"));
        }
        return sliderContainer;
    }
    }

