package com.academy.ui.homePage;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CardCarouselBlockTest extends BaseTestRunner {

    private SoftAssert softAssert;

    @BeforeTest
    public void initializeSoftAssert(){
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-863")
    public void checkLoggedinClickableBlockAndButton(){
//        TODO login
        List<ClubDirectionCard> directionCards = homePage.getCarouselCardComponent().getAllCarouselCards();
        ClubDirectionCard directionCard;
        ClubsPage clubsPage;

        for(int i = 0; i < directionCards.size(); i++){
            directionCard = getDirectionCard(i);
            String directionName = directionCard.getClubCardHeading().getText();

            System.out.println("directionName1 "+directionName);

            clubsPage = directionCard.clickCard();

//            softAssert.assertTrue(clubsPage.getSearchSider().isDirectionBoxChecked(directionName));

            homePage = backToHomePage();

            directionCard = getDirectionCard(i);

            directionName = directionCard.getClubCardHeading().getText();
            System.out.println("directionName2 "+directionName);

            clubsPage = directionCard.clickClubCardButton();

//            softAssert.assertTrue(clubsPage.getSearchSider().isDirectionBoxChecked(directionName));

            homePage = backToHomePage();
        }
    }

    private HomePage backToHomePage(){
        driver.navigate().back();
        homePage.waitUntilHomePageIsLoaded();
        return new HomePage(driver);
    }

    private ClubDirectionCard getDirectionCard(int index){
        CarouselCardComponent carousel = homePage.getCarouselCardComponent();
        while(!carousel.checkThatTheClubDirectionCardObtainedByIndexIsActive(index)){

//            System.out.println("***************");

            carousel = carousel.clickRightArrowButton();
        }
        return homePage.getCarouselCardComponent().getClubDirectionCardByIndex(index);
    }

}
