package com.academy.ui.homePage;

import com.academy.ui.runners.LoginWithUserTestRunner;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageWithLoggedInUserTest extends LoginWithUserTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp_init() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-868")
    public void verifyHeaderFooterVisibleOnHomePage() {
        softAssert.assertTrue(homePage.header.isComponentVisible());
        softAssert.assertTrue(homePage.footer.isComponentVisible());
        softAssert.assertAll();
    }

    @Test(description = "TUA-861")
    public void verifyActiveSlickDotCorrespondsToClubCards() {
        final var expectedActiveDotColor = "#fa8c16";
        final var expectedClubTitles = List.of(
                "Художня студія, мистецтво, дизайн",
                "Вокальна студія, музика, музичні інструменти",
                "Акторська майстерність, театр",
                "Особистісний розвиток");

        var clubCarousel = homePage.getCarouselCardComponent();
        //check first slick dot in orange color
        softAssert.assertEquals(clubCarousel.getSlickDotColor(clubCarousel.getSlickDots().get(0)),
                expectedActiveDotColor);

        //check other slickDots color are not orange
        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .filter(s -> clubCarousel.getSlickDotColor(s).equals(expectedActiveDotColor))
                .count() == 1);

        //check slickDot y alignment
        int slickDotYLocation = clubCarousel.getActiveSlickDot().getLocation().y;
        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .skip(1)
                .allMatch(s -> s.getLocation().y == slickDotYLocation));

        //click second slickDot
        clubCarousel.clickSlickDotByIndex(1);
        //check color of the second slickDot is orange
        softAssert.assertEquals(clubCarousel.getSlickDotColor(clubCarousel.getSlickDotByIndex(1)),
                expectedActiveDotColor);

        //check other slickDots color are not orange
        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .filter(s -> clubCarousel.getSlickDotColor(s).equals(expectedActiveDotColor))
                .count() == 1);
        //check active carousel item indicator with suggested clubs correspond to the active blocks on the carousel
        softAssert.assertEquals(clubCarousel.getActiveCarouselCards().stream()
                .map(c -> c.getClubCardHeading().getText())
                .collect(Collectors.toList()), expectedClubTitles);

        softAssert.assertAll();
    }
}
