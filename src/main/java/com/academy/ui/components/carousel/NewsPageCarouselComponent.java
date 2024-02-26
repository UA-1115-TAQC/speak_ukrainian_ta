package com.academy.ui.components.carousel;

import com.academy.ui.components.NewsCardComponent;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsPageCarouselComponent extends BasicCarouselComponent<NewsPageCarouselComponent> {
    private List<NewsCardComponent> newsCardComponents;

    @FindBy(xpath = "./span[contains(@aria-label, 'arrow-right')]")
    private WebElement rightArrow;

    public NewsPageCarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public NewsPageCarouselComponent clickRightArrowButton() {
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(d -> rightArrow.isEnabled());
        rightArrow.click();
        return this;
    }

    public NewsPageCarouselComponent clickLeftArrowButton() {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(d -> leftArrowButton.isEnabled());
        return super.clickLeftArrowButton();
    }

    public List<NewsCardComponent> getNewsCardComponents() {
        return newsCardComponents = sliderContainer
                .findElements(By.xpath("//div[contains(@class, 'slick-active')]"))
                .stream()
                .peek(e -> {
                    new WebDriverWait(driver, Duration.ofSeconds(1))
                            .until(d -> e.isDisplayed());
                })
                .map(e -> new NewsCardComponent(driver, e))
                .collect(Collectors.toList());
    }
}
