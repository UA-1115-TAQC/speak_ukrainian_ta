package com.academy.ui.pages;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.CarouselImgComponent;
import com.academy.ui.pages.facebookpages.LanguageSphereFacebookPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends BasePageWithAdvancedSearch {
    protected LanguageSphereFacebookPage languageSphereFacebookPage;
    public CarouselImgComponent carouselImgComponent;
    @FindBy(xpath="//div[contains(@class,\"about-carousel-block\")]")
    protected WebElement carouselImgComponentWebElement;
    public CarouselCardComponent carouselCardComponent;
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
        return carouselCardComponent == null ? new CarouselCardComponent(driver, carouselCardComponentWebElement):carouselCardComponent;
    }

    public CarouselImgComponent getCarouselImgComponent() {
        return carouselImgComponent == null ? new CarouselImgComponent(driver, carouselImgComponentWebElement):carouselImgComponent;
    }
    //has to return a new object
    public void clickChallengeFindOutMoreButton() {
        this.challengeFindOutMoreButton.click();
    }
    //has to return a new object
    public void clickSpeakingClubHeading() {
        this.speakingClubHeading.click();


    }

    public LanguageSphereFacebookPage clickSpeakingClubImage(){
        this.speakingClubImage.click();
        return languageSphereFacebookPage = new LanguageSphereFacebookPage(driver);
    }
}
