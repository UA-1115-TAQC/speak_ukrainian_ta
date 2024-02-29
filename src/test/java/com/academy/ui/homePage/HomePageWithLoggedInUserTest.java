package com.academy.ui.homePage;

import com.academy.ui.runners.LoginWithUserTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

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
    public void verifyActiveClubsCarouselIndicatorCorrespondCarouselBlock() {
        final var expectedActiveSlickDotColor = "#fa8c16";
        final var carouselCardTitleExpected = List.of(
                "Художня студія, мистецтво, дизайн",
                "Вокальна студія, музика, музичні інструменти",
                "Акторська майстерність, театр",
                "Особистісний розвиток");

        var carouselCardComponent = homePage.getCarouselCardComponent();
        //check first slick dot in orange color
        softAssert.assertEquals(carouselCardComponent.getSlickDotColor(carouselCardComponent.getSlickDots().get(0)),
                        expectedActiveSlickDotColor);

        //check other slickDots color are not orange
        softAssert.assertTrue(carouselCardComponent.getSlickDots().stream()
                        .filter(s -> carouselCardComponent.getSlickDotColor(s).equals(expectedActiveSlickDotColor))
                        .count() == 1);

        //check slickDot y alignment
        int slickDotYLocation = carouselCardComponent.getActiveSlickDot().getLocation().y;
        softAssert.assertTrue(carouselCardComponent.getSlickDots().stream().skip(1).allMatch(s -> s.getLocation().y == slickDotYLocation));

        //click second slickDot
        carouselCardComponent.clickSlickDotByIndex(1);
        //check color of the second slickDot is orange
        softAssert.assertEquals(carouselCardComponent.getSlickDotColor(carouselCardComponent.getSlickDotByIndex(1)),
                expectedActiveSlickDotColor);

        //check other slickDots color are not orange
        softAssert.assertTrue(carouselCardComponent.getSlickDots().stream()
                .filter(s -> carouselCardComponent.getSlickDotColor(s).equals(expectedActiveSlickDotColor))
                .count() == 1);
        //check active carousel item indicator with suggested clubs correspond to the active blocks on the carousel
        softAssert.assertEquals(carouselCardComponent.getActiveCarouselCards().stream()
                                .map(c -> c.getClubCardHeading().getText()).collect(Collectors.toList()), carouselCardTitleExpected);

        softAssert.assertAll();
    }
}
