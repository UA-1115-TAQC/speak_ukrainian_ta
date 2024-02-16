package com.academy.ui.components.carousel;

import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Getter
public class CarouselCardComponent extends BasicCarouselComponent {
    public CarouselCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    protected List<ClubDirectionCard> carouselCards;
    @FindBy(xpath = "//div[contains(@class,\"categories-header\")]/h2")
    protected WebElement carouselCardHeading;
    @FindBy(xpath = "//div[contains(@class,\"categories-header\")]/a/button")
    protected WebElement carouselCardAllClubsButton;
    protected List<ClubDirectionCard> activeCarouselCards;

    public ClubsPage clickCarouselCardAllClubsButton() {
        this.getCarouselCardAllClubsButton().click();
        return new ClubsPage(driver);
    }

    public List<ClubDirectionCard> getAllCarouselCards() {
        if (carouselCards == null) {
            carouselCards = new ArrayList<>();
            List<WebElement> cards = this.getSliderContainer().findElements(By.xpath(".//div[contains(@class,\"slick-slide\")]"));
            for (WebElement card : cards) {
                carouselCards.add(new ClubDirectionCard(driver, card));
            }
        }
        return carouselCards;
    }

    public ClubDirectionCard getClubDirectionCardByIndex(int index) {
        if (index >= 0 && index <= (getAllCarouselCards().size() - 1)) {
            return getAllCarouselCards().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (getAllCarouselCards().size() - 1) + ", inclusive");
    }

    public boolean checkThatTheClubDirectionCardObtainedByIndexIsActive(int index) {
        return getClubDirectionCardByIndex(index).getClubCardHeading().isDisplayed();
    }
    public List<ClubDirectionCard> getActiveCarouselCards(){
        activeCarouselCards = new ArrayList<>();
        for(ClubDirectionCard card: getAllCarouselCards()){
            if(card.getClubCardHeading().isDisplayed()){
                activeCarouselCards.add(card);
            }
        }
        return activeCarouselCards;
    }
    public ClubDirectionCard getActiveCarouselCardByIndex(int index){
        if (index >= 0 && index <= (getActiveCarouselCards().size() - 1)) {
            return getActiveCarouselCards().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (getActiveCarouselCards().size() - 1) + ", inclusive");
    }
}
