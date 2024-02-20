package com.academy.ui.pages;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.CarouselImgComponent;
import com.academy.ui.pages.challenges.ChallengeTeachInUkrainian;
import com.academy.ui.pages.challenges.ChallengeUkrainianClubSpeakPage;
import com.academy.ui.pages.facebookpages.LanguageSphereFacebookPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Getter
public class HomePage extends BasePageWithAdvancedSearch {
    public CarouselImgComponent carouselImgComponent;
    public CarouselCardComponent carouselCardComponent;
    @FindBy(xpath="//div[contains(@class,\"about-carousel-block\")]")
    protected WebElement carouselImgComponentWebElement;
    @FindBy(xpath="//div[contains(@class,\"categories-carousel-block\")]")
    protected WebElement carouselCardComponentWebElement;
    @FindBy(xpath= CHALLENGE_DESCRIPTION_PATH + "/h2")
    protected WebElement challengeDescriptionHeading;
    @FindBy(xpath=CHALLENGE_DESCRIPTION_PATH + "/span")
    protected WebElement challengeDescriptionText;
    @FindBy(xpath=CHALLENGE_DESCRIPTION_PATH +"//button")
    protected WebElement challengeFindOutMoreButton;
    @FindBy(xpath="//div[contains(@class,\"about-challenge\")]//img")
    protected WebElement challengeImage;
    @FindBy(xpath="//div[contains(@class,\"speakingclub-description\")]//h2")
    protected WebElement speakingClubHeading;
    @FindBy(xpath="//img[contains(@class,\"banner-image\")]")
    protected WebElement speakingClubImage;
    protected final String  CHALLENGE_DESCRIPTION_PATH = "//div[contains(@class,\"challenge-description\")]";

    public HomePage(WebDriver driver) {
        super(driver);
        this.carouselCardComponent = getCarouselCardComponent();
        this.carouselImgComponent = getCarouselImgComponent();
    }

    public CarouselCardComponent getCarouselCardComponent() {
        return carouselCardComponent == null ?
                carouselCardComponent = new CarouselCardComponent(driver, carouselCardComponentWebElement) :
                carouselCardComponent;
    }

    public CarouselImgComponent getCarouselImgComponent() {
        return carouselImgComponent == null ?
                carouselImgComponent = new CarouselImgComponent(driver, carouselImgComponentWebElement) :
                carouselImgComponent;
    }
    public ChallengeTeachInUkrainian clickChallengeFindOutMoreButton() {
        this.getChallengeFindOutMoreButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //add wait condition - wait until the page is loaded
        return new ChallengeTeachInUkrainian(driver);
    }
    public ChallengeUkrainianClubSpeakPage clickSpeakingClubHeading() {
        this.getSpeakingClubHeading().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //add wait condition - wait until the page is loaded
        return new ChallengeUkrainianClubSpeakPage(driver);
    }
    public LanguageSphereFacebookPage clickSpeakingClubImage(){
        int previousTabAmount = getTabHandles().size();
        this.getSpeakingClubImage().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfWindowsToBe( previousTabAmount +1));
        switchToANewTabByItsIndex(previousTabAmount);
        LanguageSphereFacebookPage languageSphereFacebookPage =  new LanguageSphereFacebookPage(driver);
        wait.until(ExpectedConditions.visibilityOf(languageSphereFacebookPage.getFacebookLogo()));
        return languageSphereFacebookPage;
    }
}
