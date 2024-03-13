package com.academy.ui.profilePage;

import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class ProfilePageClubsLogoSetByDefault extends LoginWithManagerTestRunner {
    protected SoftAssert softAssert;
    ProfilePage profilePage;
    WebDriverWait wait;
    List<ClubCardWithEditComponent> clubs;

    @BeforeMethod
    public void precondition(){
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        profilePage = homePage.header.openUserMenu().clickProfile();
        clubs = profilePage.getClubCardComponents();
    }

    @Test(description = "TUA-971")
    public void test() {
        for (ClubCardWithEditComponent club: clubs) {
            WebElement logo = club.getLogo();

            WebElement parentLogo = logo.findElement(By.xpath(".."));
            // check whether image logo wasn't set by the user
            boolean defaultLogo = !parentLogo.getTagName().equalsIgnoreCase("span");

            // check whether logo image matches one of the description tags images
            if (defaultLogo) {
                List<String> urls = club.getDirectionTags().stream()
                        .map(directionTag -> directionTag.findElement(By.className("icon")))
                        .map(ProfilePageClubsLogoSetByDefault::getMaskUrl)
                        .toList();
                softAssert.assertTrue(urls.contains(logo.getAttribute("url")));
            }
        }
    }
    // extract url from mask in style attribute of an element
    private static String getMaskUrl(WebElement divElement) {
        // Get the style attribute value
        String styleAttribute = divElement.getAttribute("style");

        // Extract the URL from the mask property
        String maskUrl = "";
        String[] styleDeclarations = styleAttribute.split(";");
        for (String declaration : styleDeclarations) {
            if (declaration.trim().startsWith("mask:")) {
                String[] parts = declaration.split("url\\(");
                if (parts.length > 1) {
                    maskUrl = parts[1].split("\\)")[0].trim();
                    break;
                }
            }
        }

        return maskUrl;
    }
}
