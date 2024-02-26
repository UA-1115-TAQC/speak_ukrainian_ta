package com.academy.ui.pages;

import com.academy.ui.components.CommentsClubComponent;
import com.academy.ui.components.RatingComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class ClubPage extends BasePage {

    private CommentsClubComponent commentsClubComponent;

    @FindBy(xpath = "//div[@class='comments-container']")
    private WebElement commentsClubComponentWebelement;

    private RatingComponent ratingComponent;

    @FindBy(xpath = "//ul[@role='radiogroup']")
    private WebElement ratingComponentWebelement;


    @FindBy(xpath = "//span[text()='Записатись на гурток']")
    private WebElement signUpForTheGroupButton;

    @FindBy(xpath = "//span[text()='Написати менеджеру']")
    private WebElement writeToTheManagerButton;

    @FindBy(xpath = "//div[@class='content']")
    private WebElement content;

    @FindBy(xpath = "//span[@class='club-name']")
    private WebElement clubName;

    @FindBy(xpath = "//div[@class='tags ']//span[@class='name']")
    private WebElement categoryClubTag;

    @FindBy(xpath = "//textarea[@placeholder='Додайте опис']")
    private WebElement addDescriptionInput;

    @FindBy(xpath = "//button[contains(@class,'MessageToClubManager')]")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class='ModalHint_title__ragND']")
    private WebElement popUpForNotRegisterUser;

    @FindBy(xpath = "//button[@class='ant-modal-close']")
    private WebElement closePopUpButton;


    public ClubPage(WebDriver driver) {
        super(driver);
        this.ratingComponent = getRatingComponent();
        this.commentsClubComponent = getCommentsClubComponent();
    }

    public CommentsClubComponent getCommentsClubComponent() {
        return commentsClubComponent == null ?
                commentsClubComponent = new CommentsClubComponent(driver, commentsClubComponentWebelement) :
                commentsClubComponent;
    }

    public RatingComponent getRatingComponent() {
        return ratingComponent == null ?
                ratingComponent = new RatingComponent(driver, ratingComponentWebelement) :
                ratingComponent;
    }


    public void clickOnSignUpForTheGroupButton() {
        signUpForTheGroupButton.click();
    }


    public ClubPage clickOnWriteToManagerButton() {
        writeToTheManagerButton.click();
        return this;
    }

    public ClubPage addTextToManager(String text) {
        addDescriptionInput.sendKeys(text);
        return this;
    }

    public ClubPage clickOnSubmitButton() {
        submitButton.click();
        return this;
    }

    public String getNameOfTheClub() {
        return clubName.getText();
    }

    public String getDescriptionOgTheClub() {
        return getContent().getText();
    }

    public ClubPage checkVisibilityOfPopUpForNotRegisterUser() {
        popUpForNotRegisterUser.isDisplayed();
        return this;
    }

    public ClubPage clickOnClosePopUpButton() {
        closePopUpButton.click();
        return this;
    }


}


