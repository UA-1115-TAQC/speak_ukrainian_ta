package com.academy.ui.components.carousel;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = ".//div[contains(@class,\"slick-slider\")]")
    protected WebElement sliderContainer;
    public BasicCarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
}

