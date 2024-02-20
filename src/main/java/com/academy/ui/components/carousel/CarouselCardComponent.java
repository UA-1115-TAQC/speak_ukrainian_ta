package com.academy.ui.components.carousel;

import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CarouselCardComponent extends BasicCarouselComponent  <CarouselCardComponent> {
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
        return  new ClubsPage(driver).waitUntilClubsPageIsLoaded(30);
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

    public List<ClubDirectionCard> getActiveCarouselCards() {
        if (activeCarouselCards == null) {
            activeCarouselCards = filterDisplayedCards(getAllCarouselCards());
        } else {
            List<ClubDirectionCard> oldCards = new ArrayList<>(activeCarouselCards);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            try {
                wait.until(ExpectedConditions.invisibilityOf(oldCards.get(oldCards.size() - 1).getClubCardHeading()));
                activeCarouselCards = filterDisplayedCards(getAllCarouselCards());
            } catch (TimeoutException e) {
                System.out.println("You are already at the beginning/end of the cards list");
            }
        }
        return activeCarouselCards;
    }

    private List<ClubDirectionCard> filterDisplayedCards(List<ClubDirectionCard> cards) {
        return cards.stream()
                .filter(card -> card.getClubCardHeading().isDisplayed())
                .collect(Collectors.toList());
    }


    /*public List<ClubDirectionCard> getActiveCarouselCards(){
        if(activeCarouselCards == null){
            activeCarouselCards = new ArrayList<>();
            for(ClubDirectionCard card: getAllCarouselCards()){
                if(card.getClubCardHeading().isDisplayed()){
                    activeCarouselCards.add(card);
                }
            }
        } else{
            List<ClubDirectionCard> oldCards = activeCarouselCards;
            activeCarouselCards = new ArrayList<>();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            try {
                wait.until(ExpectedConditions.invisibilityOf(oldCards.get((oldCards.size() - 1)).getClubCardHeading()));
                for (ClubDirectionCard card : getAllCarouselCards()) {
                    if (card.getClubCardHeading().isDisplayed()) {
                        activeCarouselCards.add(card);
                    }
                }
            }catch (TimeoutException e){
                activeCarouselCards = oldCards;
                System.out.println("You are already at the beginning/end of the cards list");
            }
        }
        return activeCarouselCards;
    }*/
    public ClubDirectionCard getActiveCarouselCardByIndex(int index){
        if (index >= 0 && index <= (getActiveCarouselCards().size() - 1)) {
            return getActiveCarouselCards().get(index);
        }
        throw new IllegalArgumentException("The index must be in the range between 0 and " + (getActiveCarouselCards().size() - 1) + ", inclusive");
    }
}
