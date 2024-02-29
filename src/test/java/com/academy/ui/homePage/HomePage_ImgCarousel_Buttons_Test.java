package com.academy.ui.homePage;

import com.academy.ui.components.carousel.CarouselImgCard;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.challenges.ChallengeTeachInUkrainian;
import com.academy.ui.pages.challenges.ChallengeUnited;
import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

public class HomePage_ImgCarousel_Buttons_Test extends HomePageTestRunner {
    private <T> T performCommonActionsForCheckingFindOutButtonOnTheImgCarousel(String urlContains,
                                                                               Supplier<T> pageObjectSupplier,
                                                                               int rightArrowClicks) {
        for (int i = 0; i < rightArrowClicks; i++) {
            home.carouselImgComponent.clickRightArrowButton();
        }
        home.carouselImgComponent.getActiveCarouselImgCard().clickCardButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));
        softAssert.assertTrue(driver.getCurrentUrl().contains(urlContains));
        softAssert.assertAll();
        return pageObjectSupplier.get();
    }

    @Test
    public void verifyImgcarouselButton1() {
        ChallengeUnited challengeUnited = performCommonActionsForCheckingFindOutButtonOnTheImgCarousel("/challenges/5", () -> new ChallengeUnited(driver),0);
        WebElement challengeImageText = challengeUnited.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertEquals(challengeImageText.getText(), "\"Єдині\" - це 28 днів підтримки у переході на українську");
        softAssert.assertAll();
    }

    @Test
    public void verifyImgcarouselButton2() {
        ClubsPage clubsPage = performCommonActionsForCheckingFindOutButtonOnTheImgCarousel("/clubs", () -> new ClubsPage(driver),1)
                .waitUntilClubsPageIsLoaded(15);
        softAssert.assertTrue(clubsPage.getAdvancedSearchClubHeader().getAdvancedSearchTextHeading().getText().contains("Гуртки "));
        softAssert.assertAll();
    }
    @Test
    public void verifyImgcarouselButton3() {
        ChallengeTeachInUkrainian challengeTeachInUkrainian = performCommonActionsForCheckingFindOutButtonOnTheImgCarousel("/about", () -> new ChallengeTeachInUkrainian(driver),2);
        WebElement challengeImageText=challengeTeachInUkrainian.getChallengeImageText();
        wait.until(ExpectedConditions.visibilityOf(challengeImageText));
        softAssert.assertTrue(challengeImageText.getText().contains("Навчай українською"));
        softAssert.assertAll();
    }
   @Test
   public void checkArrowButtons() {
       CarouselImgCard initialActiveCard = home.carouselImgComponent.getActiveCarouselImgCard();
       CarouselImgCard newActiveCard = home.carouselImgComponent.clickRightArrowButton().getActiveCarouselImgCard();
       assertCardChanged(initialActiveCard, newActiveCard);
       newActiveCard = home.carouselImgComponent.clickLeftArrowButton().getActiveCarouselImgCard();
       assertCardUnchanged(initialActiveCard, newActiveCard);
   }

    @Test
    public void checkNavigationBySlickdots() {
        CarouselImgCard initialActiveCard = home.carouselImgComponent.getActiveCarouselImgCard();
        CarouselImgCard newActiveCard = home.carouselImgComponent.clickSlickDotByIndex(2).getActiveCarouselImgCard();
        assertCardChanged(initialActiveCard, newActiveCard);
        newActiveCard = home.carouselImgComponent.clickSlickDotByIndex(0).getActiveCarouselImgCard();
        assertCardUnchanged(initialActiveCard, newActiveCard);
    }

    private void assertCardChanged(CarouselImgCard initialCard, CarouselImgCard newCard) {
        softAssert.assertNotEquals(newCard.getCardHeading().getText(), initialCard.getCardHeading().getText());
        softAssert.assertAll();
    }

    private void assertCardUnchanged(CarouselImgCard initialCard, CarouselImgCard newCard) {
        softAssert.assertEquals(newCard.getCardHeading().getText(), initialCard.getCardHeading().getText());
        softAssert.assertAll();
    }
}
