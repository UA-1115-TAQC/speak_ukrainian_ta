package com.academy.ui.homePage;

import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageWithLoggedInUserTest extends LogInWithUserTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp_init() {
        softAssert = new SoftAssert();
    }

    @Test(description = "Verify visibility of header and footer with logged in user")
    @Description("Verify that the header and the footer are visible when user is logged in")
    @Issue("TUA-868")
    public void verifyHeaderFooterVisibleOnHomePage() {
        softAssert.assertTrue(homePage.header.isComponentVisible());
        softAssert.assertTrue(homePage.footer.isComponentVisible());
        softAssert.assertAll();
    }

    @Test
    @Description("""
            Verify that active carousel item indicator with suggested clubs
            correspond to the active blocks on the carousel""")
    @Issue("TUA-861")
    public void verifyActiveSlickDotCorrespondsToClubCards() {
        final var expectedActiveDotColor = "#fa8c16";
        final var expectedClubTitles = List.of(
                "Художня студія, мистецтво, дизайн",
                "Вокальна студія, музика, музичні інструменти",
                "Акторська майстерність, театр",
                "Особистісний розвиток");

        var clubCarousel = homePage.getCarouselCardComponent();
        softAssert.assertEquals(clubCarousel.getSlickDotColor(clubCarousel.getSlickDots().get(0)),
                expectedActiveDotColor);

        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .filter(s -> clubCarousel.getSlickDotColor(s).equals(expectedActiveDotColor))
                .count() == 1);

        int slickDotYLocation = clubCarousel.getActiveSlickDot().getLocation().y;
        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .skip(1)
                .allMatch(s -> s.getLocation().y == slickDotYLocation));

        clubCarousel.clickSlickDotByIndex(1);
        softAssert.assertEquals(clubCarousel.getSlickDotColor(clubCarousel.getSlickDotByIndex(1)),
                expectedActiveDotColor);

        softAssert.assertTrue(clubCarousel.getSlickDots().stream()
                .filter(s -> clubCarousel.getSlickDotColor(s).equals(expectedActiveDotColor))
                .count() == 1);
        softAssert.assertEquals(clubCarousel.getActiveCarouselCards().stream()
                .map(c -> c.getClubCardHeading().getText())
                .collect(Collectors.toList()), expectedClubTitles);

        softAssert.assertAll();
    }
}
