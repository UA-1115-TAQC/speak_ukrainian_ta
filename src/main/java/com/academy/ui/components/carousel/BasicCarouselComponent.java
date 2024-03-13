package com.academy.ui.components.carousel;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Getter
public class BasicCarouselComponent<T extends BasicCarouselComponent<T>> extends BaseComponent {
    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-left')]")
    protected WebElement leftArrowButton;
    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-right')]")
    protected WebElement rightArrowButton;
    @FindBy(xpath = ".//ul[contains(@class,\"slick-dots\")]/li")
    protected List<WebElement> slickDots;
    @FindBy(xpath = ".//ul[contains(@class,\"slick-dots\")]")
    protected WebElement slickDotsContainer;
    @FindBy(xpath = ".//div[contains(@class,\"slick-slider\")]")
    protected WebElement sliderContainer;
    protected  WebDriverWait wait;

    public BasicCarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Click left arrow button")
    public T clickLeftArrowButton() {
        this.getLeftArrowButton().click();
        return (T) this;
    }
    @Step("Click right arrow button")
    public T clickRightArrowButton() {
        this.getRightArrowButton().click();
        return (T) this;
    }
    @Step("Get slick dot by index: {index}")
    public WebElement getSlickDotByIndex(int index) {
        if (index >= 0 && index < this.getSlickDots().size()) {
            return this.getSlickDots().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (this.getSlickDots().size() - 1) + ", inclusive");
    }
    @Step("Click slick dot by index: {index}")
    public T clickSlickDotByIndex(int index) {
        this.getSlickDotByIndex(index).click();
        wait.until(ExpectedConditions.attributeContains(getSlickDotByIndex(index), "background", "rgb(250, 140, 22)"));
        return (T) this;
    }

    @Step("Get active slick dot from the carousel")
    public WebElement getActiveSlickDot() {
        WebElement activeSlickDot = null;
        for (WebElement dot : this.getSlickDots()) {
            if (Objects.equals(dot.getAttribute("class"), "slick-active")) {
                activeSlickDot = dot;
            }
        }
        return activeSlickDot;
    }

    @Step("Click active slick dot in the carousel")
    public T clickActiveSlickDot() {
        this.getActiveSlickDot().click();
        return (T) this;
    }

    public String getSlickDotColor(WebElement slickDot) {
        return Color.fromString(slickDot.getCssValue("background-color")).asHex();
    }
}

