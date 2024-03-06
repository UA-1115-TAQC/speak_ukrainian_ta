package com.academy.ui.homePage;

import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.runners.HomePageTestRunner;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import java.util.List;


public class TestCarouselItemIndicatorCorrespondToActiveBlocks extends HomePageTestRunner {

    private static final String ACTIVE_EXPECTED_COLOR = "#fa8c16";
    private static final String INACTIVE_EXPECTED_COLOR = "#c4c4c4";

    @Test(description = "TUA-829")
    public void checkThatCarouselIndicatorWithSuggestedClubsCorrespondToActiveBlocks() {
        homePage.carouselCardComponent.clickSlickDotByIndex(0);
        String activeDot = homePage.carouselCardComponent.getActiveSlickDot().getCssValue("background-color");
        String hexColor = Color.fromString(activeDot).asHex();
        softAssert.assertEquals(hexColor, ACTIVE_EXPECTED_COLOR, "Button color is not as expected");


        String inActiveDot = homePage.carouselCardComponent.getSlickDotByIndex(2).
                getCssValue("background-color");

        String hexColor2 = Color.fromString(inActiveDot).asHex();
        softAssert.assertEquals(hexColor2, INACTIVE_EXPECTED_COLOR, "Button color is not as expected");

        List<ClubDirectionCard> clubAtFirstPage = homePage.carouselCardComponent.getActiveCarouselCards();
        homePage.carouselCardComponent.clickSlickDotByIndex(1);
        List<ClubDirectionCard> clubAtSecondPage = homePage.carouselCardComponent.getActiveCarouselCards();
        softAssert.assertNotEquals(clubAtFirstPage, clubAtSecondPage);

        softAssert.assertAll();

    }

}



