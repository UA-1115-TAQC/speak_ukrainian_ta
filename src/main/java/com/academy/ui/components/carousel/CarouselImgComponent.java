package com.academy.ui.components.carousel;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
@Getter
public class CarouselImgComponent extends BasicCarouselComponent <CarouselImgComponent>{
    @Getter(AccessLevel.NONE)
    protected HashMap<Integer, WebElement> switchingCarouselImgCards;
    @Getter(AccessLevel.NONE)
    protected CarouselImgCard activeCarouselImgCard;
    protected Actions actions;
    protected WebDriverWait wait;
    public  CarouselImgComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofMinutes(1));
    }
    public HashMap<Integer, WebElement>getCarouselImgCards(){
        if(switchingCarouselImgCards == null){
            switchingCarouselImgCards = new HashMap<>();
            List<WebElement> imgCards = this.getSliderContainer().findElements(By.xpath(".//div[contains(@class,\"slick-slide\")]"));
            for(WebElement imgCard:imgCards){
                int dataIndex = Integer.parseInt(imgCard.getAttribute("data-index"));
                if(dataIndex >= 0 && dataIndex <= 2){
                    switchingCarouselImgCards.put(dataIndex, imgCard);
                }
            }
        }
        return switchingCarouselImgCards;
    }
    public CarouselImgCard getCarouselImgCardByDataIndex(int dataIndex){
        if(dataIndex >= 0 && dataIndex < getCarouselImgCards().size()) {
            WebElement imgCard = this.getCarouselImgCards().get(dataIndex);
            wait.until(ExpectedConditions.visibilityOf(imgCard));
            return new CarouselImgCard(driver, imgCard);
        }
        throw new IllegalArgumentException("The index must be in the range from 0 to "+(getCarouselImgCards().size()-1)+", inclusive.");
    }
    public CarouselImgCard getActiveCarouselImgCard() {
        int dataIndex = findActiveCarouselImgCardIndex();
        if (activeCarouselImgCard == null) {
            return activeCarouselImgCard = new CarouselImgCard(driver, getCarouselImgCards().get(dataIndex));
        } else {
            CarouselImgCard oldCard = activeCarouselImgCard;
            wait.until(ExpectedConditions.invisibilityOf(oldCard.getCardHeading()));
            return activeCarouselImgCard = new CarouselImgCard(driver, getCarouselImgCards().get(dataIndex));
        }
    }
    public int findActiveCarouselImgCardIndex() {
        for (int i = 0; i < getCarouselImgCards().size(); i++) {
            if (getCarouselImgCards().get(i).getAttribute("class").contains("active")) {
                return i;
            }
        }
        return 0;
    }
    public void waitUntilTheCardIsDisplayedByIndex(int i){
        if (!getCarouselImgCardByDataIndex(i).getCardButton().isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOf(getCarouselImgCardByDataIndex(i).getCardButton()));
        }
    }
    public void hoverOverCardButton(int i){
        actions.moveToElement(getCarouselImgCardByDataIndex(i).getCardButtonText());
        actions.build().perform();
    }
}
