package com.academy.ui.homepage_tests;

import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.runners.HomePageTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePage_CardCarousel_Buttons_Test extends HomePageTestRunner {
    @Test
    public void check_all_clubs_button(){
        home.carouselCardComponent.clickCarouselCardAllClubsButton().waitUntilClubsPageIsLoaded(15);
        Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
    }

    @Test
    public void check_view_more_button_on_cards(){
       home.carouselCardComponent.getActiveCarouselCardByIndex(0).clickClubCardButton().waitUntilClubsPageIsLoaded(15);
       Assert.assertTrue(driver.getCurrentUrl().contains("clubs"));
       }
   @Test
   public  void check_arrow_buttons(){
       List<ClubDirectionCard> initialActiveCards = home.carouselCardComponent.getActiveCarouselCards();
       List<ClubDirectionCard> newActiveCards= home.carouselCardComponent.clickRightArrowButton().getActiveCarouselCards();
       Assert.assertNotEquals(newActiveCards, initialActiveCards);
       newActiveCards= home.carouselCardComponent.clickLeftArrowButton().getActiveCarouselCards();
       Assert.assertEquals(initialActiveCards, newActiveCards);
   }
   @Test
    public void check_navigation_by_slickdots(){
       List<ClubDirectionCard> initialActiveCards = home.carouselCardComponent.getActiveCarouselCards();
       List<ClubDirectionCard> newActiveCards= home.carouselCardComponent.clickSlickDotByIndex(1).getActiveCarouselCards();
       Assert.assertNotEquals(newActiveCards, initialActiveCards);
       newActiveCards= home.carouselCardComponent.clickSlickDotByIndex(0).getActiveCarouselCards();
       Assert.assertEquals(newActiveCards, initialActiveCards);
   }
}



