package com.academy.ui.pages.challenges;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class ChallengeTeachInUkrainian extends BaseChallengePage{
    public ChallengeTeachInUkrainian(WebDriver driver){
        super(driver);
    }
    protected final  String  CHALLENGE_IMAGE_PATH =" //div[contains(@class,\"title\") and not(contains(@role,\"menuitem\"))]";
    @FindBy(xpath = CHALLENGE_IMAGE_PATH +"/span[contains(@class,\"text\")]")
    protected WebElement challengeImageText;

    @FindBy(xpath = CHALLENGE_IMAGE_PATH +"/span[contains(@class,\"content\")]")
    protected WebElement challengeImageTextContent;
}
