package com.academy.ui.homePage;
import com.academy.ui.components.carousel.ClubDirectionCard;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TestCarouselItemIndicatorCorrespondToActiveBlocks extends BaseTestRunner {

    private static final String ACTIVE_EXPECTED_COLOR ="#fa8c16";
    private static final String INACTIVE_EXPECTED_COLOR ="#c4c4c4";

    @Test(description = "TUA-829")
    public void checkThatCarouselIndicatorWithSuggestedClubsCorrespondToActiveBlocks(){
        homePage.carouselCardComponent.clickSlickDotByIndex(0);
        String activeColor = driver.findElement(By.xpath("//li[@class='slick-active']"))
                .getCssValue("background-color");
        String hexColor = Color.fromString(activeColor).asHex();
        Assert.assertEquals(hexColor, ACTIVE_EXPECTED_COLOR, "Button color is not as expected");


        String inActiveColor = driver.findElement(By.xpath(".//button[contains(text(),'2')]")).
                getCssValue("background-color");
        String hexColor2 = Color.fromString(inActiveColor).asHex();
        Assert.assertEquals(hexColor2, INACTIVE_EXPECTED_COLOR, "Button color is not as expected");

        List<ClubDirectionCard> clubAtFirstPage = homePage.carouselCardComponent.getActiveCarouselCards();
        homePage.carouselCardComponent.clickSlickDotByIndex(1);
        List<ClubDirectionCard> clubAtSecondPage = homePage.carouselCardComponent.getActiveCarouselCards();
        Assert.assertNotEquals(clubAtFirstPage,clubAtSecondPage);





    }

    }



