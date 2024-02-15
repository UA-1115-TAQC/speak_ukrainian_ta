package com.academy.ui.components.carousel;

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
public class CarouselImgComponent extends BasicCarouselComponent {
    protected HashMap<Integer, WebElement> switchingCarouselImgCards;
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
    public CarouselImgCard getActiveCarouselImgCard()  {
        int dataIndex = 0;
        for (int i = 0; i < this.getCarouselImgCards().size(); i++){
            if( getCarouselImgCards().get(i).getAttribute("class").contains("active")){
                dataIndex=i;
            }

        }
        return new CarouselImgCard( driver, this.getCarouselImgCards().get(dataIndex));
    }
}
