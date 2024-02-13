package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HomePage extends BasePageWithAdvancedSearch {

    protected WebElement challengeDescriptionHeading;
    protected WebElement challengeDescriptionText;
    protected WebElement challengeFindOutMoreButton;
    protected WebElement challengeImage;
    protected WebElement speakingClubHeading;
    protected WebElement speakingClubImage;
    protected String challengeDescriptionDivPath = "//div[contains(@class,\"challenge-description\")]";
    public HomePage(WebDriver driver) {
        super(driver);
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
