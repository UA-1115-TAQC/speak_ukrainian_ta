package com.academy.ui.components;

import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FooterComponentTest extends BaseTestRunner {
    private static final int FACEBOOK_URL = 0;
    private static final int YOUTUBE_URL = 1;
    private static final int INSTAGRAM_URL = 2;

    private FooterComponent footerComponent;

    @BeforeMethod
    public void footerPrecondition() {
        footerComponent = homePage.getFooter();
    }

    @Test
    public void click_on_youTube_icon_ok() {
        footerComponent.getFooterSocialLinks().get(YOUTUBE_URL).click();
        assertTrue(footerComponent.getFooterSocialLinks().get(YOUTUBE_URL).isDisplayed());
    }
}
