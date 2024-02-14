package com.academy.ui.pages;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.CarouselImgComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePageWithAdvancedSearch {
    public CarouselCardComponent carouselCardComponent;
    public CarouselImgComponent carouselImgComponent;
    protected WebElement challengeDescriptionHeading;
    protected WebElement challengeDescriptionText;
    protected WebElement challengeFindOutMoreButton;
    protected WebElement challengeImage;
    protected WebElement speakingClubHeading;
    protected WebElement speakingClubImage;
    protected String challengeDescriptionDivPath = "//div[contains(@class,\"challenge-description\")]";

    public HomePage(WebDriver driver) {
        super(driver);
        this.carouselCardComponent = getCarouselCardComponent();
        this.carouselImgComponent = getCarouselImgComponent();
    }

    public CarouselCardComponent getCarouselCardComponent() {
        if (carouselCardComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class,\"categories-carousel-block\")]"));
            carouselCardComponent = new CarouselCardComponent(driver, node);
        }
        return carouselCardComponent;
    }

    public CarouselImgComponent getCarouselImgComponent() {
        if (carouselImgComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class,\"about-carousel-block\")]"));
            carouselImgComponent = new CarouselImgComponent(driver, node);
        }
        return carouselImgComponent;
    }

    public WebElement getChallengeDescriptionHeading() {
        if (challengeDescriptionHeading == null) {
            challengeDescriptionHeading = driver.findElement(By.xpath(challengeDescriptionDivPath + "/h2"));
        }
        return challengeDescriptionHeading;
    }

    public WebElement getChallengeDescriptionText() {
        if (challengeDescriptionText == null) {
            challengeDescriptionText = driver.findElement(By.xpath(challengeDescriptionDivPath + "/span"));
        }
        return challengeDescriptionText;
    }

    public WebElement getChallengeFindOutMoreButton() {
        if (challengeFindOutMoreButton == null) {
            challengeFindOutMoreButton = driver.findElement(By.xpath(challengeDescriptionDivPath + "//button"));
        }
        return challengeFindOutMoreButton;
    }

    public void clickChallengeFindOutMoreButton() {
        this.getChallengeFindOutMoreButton().click();
    }

    public WebElement getChallengeImage() {
        if (challengeImage == null) {
            challengeImage = driver.findElement(By.xpath("//div[contains(@class,\"about-challenge\")]//img"));
        }
        return challengeImage;
    }

    public WebElement getSpeakingClubHeading() {
        if (speakingClubHeading == null) {
            speakingClubHeading = driver.findElement(By.xpath("//div[contains(@class,\"speakingclub-description\")]//h2"));
        }
        return speakingClubHeading;
    }

    public void clickSpeakingClubHeading() {
        this.getSpeakingClubHeading().click();
    }

    public WebElement getSpeakingClubImage() {
        if (speakingClubImage == null) {
            speakingClubImage = driver.findElement(By.xpath("//img[contains(@class,\"banner-image\")]"));
        }
        return speakingClubImage;
    }

    public void clickSpeakingClubImage() {
        this.getSpeakingClubImage().click();
    }
}
