package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends BasePage{
    private final String CLUB_CARD_LIST_XPATH =
            "//div[contains(@class,'content-clubs-list')]";
    private List<ClubCardComponent> clubCards;


    public ClubsPage(WebDriver driver) {
        super(driver);
        clubCards = createClubComponents();
    }

    private List<ClubCardComponent> createClubComponents(){
        List<ClubCardComponent> clubs = new ArrayList<>();
        List<WebElement> clubDivs = driver.findElements(By.xpath(CLUB_CARD_LIST_XPATH + "/child::div"));
        for(WebElement element : clubDivs){
            ClubCardComponent cardComponent = new ClubCardComponent(driver, element);
            clubs.add(cardComponent);
        }
        return clubs;
    }

    public List<ClubCardComponent> getClubCards(){
        return clubCards;
    }

}
