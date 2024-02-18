package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



@Getter
public class ClubPage extends BasePage {

    @FindBy(xpath = "//span[text()='Записатись на гурток']")
    private WebElement signUpForTheGroupButton;

    @FindBy(xpath = "//span[text()='Записатись на гурток']")
    private WebElement writeToTheManagerButton;

    @FindBy(xpath = "//div[@class='content']")
    private WebElement content;

    @FindBy(xpath = "//span[@class='club-name']")
    private WebElement clubName;

    @FindBy(xpath = "//div[@class='tags ']//span[@class='name']")
    private WebElement categoryClubTag;

    @FindBy(xpath = "//p[@class='show-more-p']")
    private WebElement showMoreButton;


    public ClubPage(WebDriver driver) {
        super(driver);
    }

    public ClubPage clickOnSignUpForTheGroupButton() {
        this.signUpForTheGroupButton.click();
        return new ClubPage(driver);
    }

    public ClubPage clickOnWriteToTheManagerButton() {
        this.writeToTheManagerButton.click();
        return new ClubPage(driver);
    }

    public String getNameOfTheClub() {
        return clubName.getText();
    }

}


