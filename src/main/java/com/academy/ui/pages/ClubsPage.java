package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends BasePage{
    private final String CLUB_CARD_LIST_XPATH =
            "//div[contains(@class,'content-clubs-list')]/child::div";
    private List<ClubCardComponent> clubCards;


    public ClubsPage(WebDriver driver) {
        super(driver);
        clubCards = createClubComponents();
    }

    private List<ClubCardComponent> createClubComponents(){
        List<ClubCardComponent> clubs = new ArrayList<>();
        List<WebElement> clubDivs = driver.findElements(By.xpath(CLUB_CARD_LIST_XPATH));
        for(WebElement element : clubDivs){
            clubs.add(new ClubCardComponent(driver, element));
        }
        return clubs;
    }

    public List<ClubCardComponent> getClubCards(){
        return clubCards;
    }
    
}
