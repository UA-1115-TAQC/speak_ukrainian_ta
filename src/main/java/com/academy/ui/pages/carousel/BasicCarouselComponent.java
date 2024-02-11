package com.academy.ui.pages.carousel;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicCarouselComponent extends BaseComponent {
    public BasicCarouselComponent(WebDriver driver, WebElement rootElement, String rootElementClass ) {
        super(driver, rootElement);
        this.rootElementClass = rootElementClass ;
    }
    protected String rootElementClass;
    protected WebElement LeftArrowButton;
    protected WebElement RightArrowButton;
    protected List<WebElement> SlickDots;
    protected WebElement SliderContainer;
    public WebElement getLeftArrowButton() {
        if (LeftArrowButton == null) {
            System.out.println(String.format("//div[contains(@class,'%s')]/span[@aria-label='arrow-left']", rootElementClass));
            LeftArrowButton = rootElement.findElement(By.xpath(String.format("//div[contains(@class,'%s')]/span[@aria-label='arrow-left']", rootElementClass)));
        }
        return LeftArrowButton;
    }
    public void clickLeftArrowButton() {
        this.getLeftArrowButton().click();
    }
    public WebElement getRightArrowButton() {
        if (RightArrowButton == null) {
            RightArrowButton = rootElement.findElement(By.xpath(String.format("//div[contains(@class,'%s')]/span[@aria-label='arrow-right']", rootElementClass)));
        }
        return RightArrowButton;
    }
    public void clickRightArrowButton () {
        this.getRightArrowButton().click();
    }
    public List<WebElement> getSlickDots () {
        if (SlickDots == null) {
            SlickDots = new ArrayList<>();
            SlickDots.addAll(rootElement.findElements(By.xpath("//ul[contains(@class,\"slick-dots\")]/li")));
        }
        return SlickDots;
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
        if (SliderContainer == null) {
            SliderContainer = rootElement.findElement(By.xpath("//div[contains(@class,\"slick-slider\")]"));
        }
        return SliderContainer;
    }
    }

