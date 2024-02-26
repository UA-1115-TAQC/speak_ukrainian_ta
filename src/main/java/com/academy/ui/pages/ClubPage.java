package com.academy.ui.pages;


import com.academy.ui.components.CommentsClubComponent;
import com.academy.ui.components.RatingComponent;

import com.academy.ui.components.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubPage extends BasePage {


    @FindBy(xpath = ".//div[@class='ant-comment-inner']")
    @Getter(AccessLevel.NONE)
    private List<WebElement> commentsClubComponentWebElement;


    @FindBy(xpath = ".//ul[@role='radiogroup']")
    @Getter(AccessLevel.NONE)
    private List<WebElement> ratingComponentWebElement;

    @FindBy(xpath = "//span[text()='Записатись на гурток']")
    private WebElement signUpForTheGroupButton;

    @FindBy(xpath = "//span[text()='Написати менеджеру']")
    private WebElement writeToTheManagerButton;

    @FindBy(xpath = "//div[@class='content']")
    private WebElement clubDescription;

    @FindBy(xpath = "//span[@class='club-name']")
    private WebElement clubName;

    @FindBy(xpath = "//div[@class='tags ']//span[@class='name']")
    private WebElement categoryClubName;

    @FindBy(xpath = "//span[@class='text']")
    private WebElement addressClub;

    @FindBy(xpath = "//div[@class='age']")
    private WebElement ageOfTheAudienceClub;

    @FindBy(xpath = "//a[contains(@href,'com.ua/')]")
    private WebElement siteOfTheClub;

    @FindBy(xpath = "//span[contains(text(),'380')]")
    private WebElement contactNumber;

    @FindBy(xpath = "//div[contains(@class,'ant-tooltip-placement-top')]")
    private WebElement tooltipMessage;

    @FindBy(xpath = "//div[@class='ant-modal-content']")
    private WebElement popUpMessage;

    @FindBy(xpath = "//button[contains(@class,'comment-button')]")
    private WebElement leaveCommentButton;

    @FindBy(xpath = "//span[text()='Написати менеджеру']")
    @Getter(AccessLevel.NONE)
    private WebElement writeToTheManagerWebElement;

    @FindBy(xpath = "//div[@class='ant-modal-content']")
    @Getter(AccessLevel.NONE)
    private WebElement signUpToTheClubWebElement;

    @FindBy(xpath = "//div[contains(@class,'comment-modal')]")
    @Getter(AccessLevel.NONE)
    private WebElement leaveCommentWebElement;


    private List<CommentsClubComponent> commentsClub;
    private List<RatingComponent> rating;

    public ClubPage(WebDriver driver) {
        super(driver);
        commentsClub = initCommentsClubComponents();
        rating = getRatingComponents();
    }

    public ClubPage hoverOverButtonSignUpForTheGroup() {
        Actions actions = new Actions(driver);
        actions.moveToElement(signUpForTheGroupButton).perform();
        return this;

    }

    public ClubPage clickOnLeaveCommentButton() {
        leaveCommentButton.click();
        return this;
    }

    public boolean checkIfHintMessageDisplayed() {
        tooltipMessage.isDisplayed();
        return true;
    }

    public List<CommentsClubComponent> initCommentsClubComponents() {
        return commentsClubComponentWebElement
                .stream()
                .map(webElement -> new CommentsClubComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    public List<RatingComponent> getRatingComponents() {
        return ratingComponentWebElement
                .stream()
                .map(webElement -> new RatingComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    public WriteToManagerPopUpComponent openWriteToManagePopUp() {
        writeToTheManagerButton.click();
        return new WriteToManagerPopUpComponent(driver, writeToTheManagerWebElement);
    }

    public SignUpToClubPopUpComponent openSignUpToClubPopUp() {
        signUpForTheGroupButton.click();
        return new SignUpToClubPopUpComponent(driver, signUpToTheClubWebElement);
    }

    public LeaveCommentPopUpComponent openLeaveCommentPopUpComponent() {
        leaveCommentButton.click();
        return new LeaveCommentPopUpComponent(driver, leaveCommentWebElement);
    }


}


