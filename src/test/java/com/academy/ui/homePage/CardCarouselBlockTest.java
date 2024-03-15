package com.academy.ui.homePage;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CardCarouselBlockTest extends LoginWithManagerTestRunner {

    @Test
    @Description("[logged in] Verify that clickable is all block and the 'Переглянути' button on the carousel with suggested clubs")
    @Issue("TUA-863")
    public void checkLoggedInClickableBlockAndButton(){
        SoftAssert softAssert = new SoftAssert();
        List<ClubDirectionCard> directionCards = homePage.getCarouselCardComponent().getAllCarouselCards();
        ClubDirectionCard directionCard;
        ClubsPage clubsPage;

        for(int i = 0; i < directionCards.size(); i++){
            directionCard = getDirectionCard(i);
            String directionName = directionCard.getClubCardHeading().getText();
            clubsPage = directionCard.clickCard();
            softAssert.assertTrue(clubsPage.getSearchSider().isDirectionBoxChecked(directionName));
            homePage = backToHomePage();

            directionCard = getDirectionCard(i);
            clubsPage = directionCard.clickClubCardButton();
            softAssert.assertTrue(clubsPage.getSearchSider().isDirectionBoxChecked(directionName));
            homePage = backToHomePage();
        }
        softAssert.assertAll();
    }

    private HomePage backToHomePage(){
        driver.navigate().back();
        homePage.waitUntilHomePageIsLoaded();
        return new HomePage(driver);
    }

    private ClubDirectionCard getDirectionCard(int index){
        CarouselCardComponent carousel = homePage.getCarouselCardComponent();
        while(!carousel.checkThatTheClubDirectionCardObtainedByIndexIsActive(index)){
            carousel = homePage.clickClubCarouselRightArrow();
        }
        return homePage.getCarouselCardComponent().getClubDirectionCardByIndex(index);
    }

}
