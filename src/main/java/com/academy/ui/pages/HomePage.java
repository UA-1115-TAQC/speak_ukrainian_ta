package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePageWithAdvancedSearch {

    protected WebElement ChallengeDescriptionHeading;
    protected WebElement ChallengeDescriptionText;
    protected WebElement ChallengeFindOutMoreButton;
    protected WebElement ChallengeImage;
    protected WebElement SpeakingClubHeading;
    protected WebElement SpeakingClubImage;
    protected String ChallengeDescriptionDivPath = "//div[contains(@class,\"challenge-description\")]";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getChallengeDescriptionHeading() {
        if (ChallengeDescriptionHeading == null) {
            ChallengeDescriptionHeading = driver.findElement(By.xpath(ChallengeDescriptionDivPath + "/h2"));
        }
        return ChallengeDescriptionHeading;
    }

    public WebElement getChallengeDescriptionText() {
        if (ChallengeDescriptionText == null) {
            ChallengeDescriptionText = driver.findElement(By.xpath(ChallengeDescriptionDivPath + "/span"));
        }
        return ChallengeDescriptionText;
    }

    public WebElement getChallengeFindOutMoreButton() {
        if (ChallengeFindOutMoreButton == null) {
            ChallengeFindOutMoreButton = driver.findElement(By.xpath(ChallengeDescriptionDivPath + "//button"));
        }
        return ChallengeFindOutMoreButton;
    }

    public void clickChallengeFindOutMoreButton() {
        this.getChallengeFindOutMoreButton().click();
    }

    public WebElement getChallengeImage() {
        if (ChallengeImage == null) {
            ChallengeImage = driver.findElement(By.xpath("//div[contains(@class,\"about-challenge\")]//img"));
        }
        return ChallengeImage;
    }

    public WebElement getSpeakingClubHeading() {
        if (SpeakingClubHeading == null) {
            SpeakingClubHeading = driver.findElement(By.xpath("//div[contains(@class,\"speakingclub-description\")]//h2"));
        }
        return SpeakingClubHeading;
    }

    public void clickSpeakingClubHeading() {
        this.getSpeakingClubHeading().click();
    }

    public WebElement getSpeakingClubImage() {
        if (SpeakingClubImage == null) {
            SpeakingClubImage = driver.findElement(By.xpath("//img[contains(@class,\"banner-image\")]"));
        }
        return SpeakingClubImage;
    }

    public void clickSpeakingClubImage() {
        this.getSpeakingClubImage().click();
    }
}
