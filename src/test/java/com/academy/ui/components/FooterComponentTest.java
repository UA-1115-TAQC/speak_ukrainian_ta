package com.academy.ui.components;

import com.academy.ui.pages.BasePageWithoutHeaderAndFooter;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FooterComponentTest extends BaseTestRunner {
    private static final int FACEBOOK_URL = 0;
    private static final int YOUTUBE_URL = 1;
    private static final int INSTAGRAM_URL = 2;
    private BasePageWithoutHeaderAndFooter basePageWithoutHeaderAndFooter;
    private FooterComponent footerComponent;

    @BeforeMethod
    public void footerPrecondition() {
        footerComponent = homePage.getFooter();
        basePageWithoutHeaderAndFooter = new BasePageWithoutHeaderAndFooter(driver);
    }

    @Test(description = "TUA-945")
    public void click_on_youTube_icon_ok() {
        String expected = footerComponent.getFooterSocialLinks().get(YOUTUBE_URL);
        footerComponent.getYouTubeLink().click();
        basePageWithoutHeaderAndFooter.getTabHandles();
        basePageWithoutHeaderAndFooter.switchToANewTabByItsIndex(YOUTUBE_URL);
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }
}
