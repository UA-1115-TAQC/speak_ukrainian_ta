package com.academy.ui.homePage;

import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.runners.HomePageTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePage_CardCarousel_Buttons_Test extends HomePageTestRunner {
    @Test(description = "TUA-21")
    @Issue("TUA-21")
    public void checkAllClubsButton(){
        home.carouselCardComponent.clickCarouselCardAllClubsButton().waitUntilClubsPageIsLoaded(15);
        softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"),"The clubs page isn't opened");
        softAssert.assertAll();
    }

    @Test(description = "TUA-21")
    @Issue("TUA-21")
    public void checkViewMoreButtonOnCards(){
       home.carouselCardComponent.getActiveCarouselCardByIndex(0).clickClubCardButton().waitUntilClubsPageIsLoaded(15);
       softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"),"The clubs page isn't opened");
       softAssert.assertAll();
       }
   @Test(description = "TUA-21")
   @Issue("TUA-21")
   public  void checkArrowButtons(){
       List<ClubDirectionCard> initialActiveCards = home.carouselCardComponent.getActiveCarouselCards();
       List<ClubDirectionCard> newActiveCards= home.carouselCardComponent.clickRightArrowButton().getActiveCarouselCards();
       softAssert.assertNotEquals(newActiveCards, initialActiveCards,"The navigation by right arrow didn't work");
       newActiveCards= home.carouselCardComponent.clickLeftArrowButton().getActiveCarouselCards();
       softAssert.assertEquals(initialActiveCards, newActiveCards,"The navigation by left arrow didn't work");
       softAssert.assertAll();
   }
   @Test(description = "TUA-21")
   @Issue("TUA-21")
    public void checkNavigationBySlickdots(){
       List<ClubDirectionCard> initialActiveCards = home.carouselCardComponent.getActiveCarouselCards();
       List<ClubDirectionCard> newActiveCards= home.carouselCardComponent.clickSlickDotByIndex(1).getActiveCarouselCards();
       softAssert.assertNotEquals(newActiveCards, initialActiveCards,"The navigation by slick dots didn't work");
       newActiveCards= home.carouselCardComponent.clickSlickDotByIndex(0).getActiveCarouselCards();
       softAssert.assertEquals(newActiveCards, initialActiveCards,"The navigation by slick dots didn't work");
       softAssert.assertAll();
   }
}



