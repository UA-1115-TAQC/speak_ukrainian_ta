package com.academy.ui.components.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarouselCardComponent extends BasicCarouselComponent {
    public CarouselCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    protected List<ClubDirectionCard> carouselCards;
    protected WebElement carouselCardHeading;
    protected WebElement carouselCardAllClubsButton;

    public WebElement getCarouselCardHeading() {
        if (carouselCardHeading == null) {
            carouselCardHeading = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/h2"));
        }
        return carouselCardHeading;
    }

    public WebElement getCarouselCardAllClubsButton() {
        if (carouselCardAllClubsButton == null) {
            carouselCardAllClubsButton = rootElement.findElement(By.xpath("//div[contains(@class,\"categories-header\")]/a/button"));
        }
        return carouselCardAllClubsButton;
    }

    public void clickCarouselCardAllClubsButton() {
        this.getCarouselCardAllClubsButton().click();
    }

    public List<ClubDirectionCard> getCarouselCards() {
        if (carouselCards == null) {
            carouselCards = new ArrayList<>();
            List<WebElement> cards = getSliderContainer().findElements(By.xpath(".//div[contains(@class,\"slick-slide\")]"));
            for (WebElement card : cards) {
                carouselCards.add(new ClubDirectionCard(driver, card));
            }
        }
        return carouselCards;
    }

    public ClubDirectionCard getClubDirectionCardByIndex(int index) {
        if (index >= 0 && index <= (getCarouselCards().size() - 1)) {
            return getCarouselCards().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (getCarouselCards().size() - 1) + ", inclusive");
    }

    public boolean checkThatTheClubDirectionCardObtainedByIndexIsActive(int index) {
        return getClubDirectionCardByIndex(index).getWebElement().isDisplayed();
    }
}
