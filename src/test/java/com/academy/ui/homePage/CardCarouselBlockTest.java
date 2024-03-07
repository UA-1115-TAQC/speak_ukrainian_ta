package com.academy.ui.homePage;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CardCarouselBlockTest extends BaseTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    private void login(){
        LoginPopupComponent loginForm = homePage.getHeader().openGuestMenu().openLoginForm();
        loginForm.enterEmail(configProperties.getUserEmail());
        loginForm.enterPassword(configProperties.getUserPassword());
        loginForm.clickSubmitButton();
        homePage.waitUntilHomePageIsVisible();
    }

    @Test(description = "TUA-863")
    public void checkLoggedInClickableBlockAndButton(){
        softAssert = new SoftAssert();
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
