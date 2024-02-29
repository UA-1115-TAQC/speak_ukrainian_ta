package com.academy.ui.components.carousel;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public  CarouselImgComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
}
