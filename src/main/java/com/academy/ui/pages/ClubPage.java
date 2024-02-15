package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;


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

    @FindBy(xpath = "//div[@class='ant-comment-content-detail']")
    private List<WebElement> comments;

    public ClubPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSignUpForTheGroupButton() {
        signUpForTheGroupButton.click();
    }

    public void clickOnWriteToTheManagerButton() {
        writeToTheManagerButton.click();
    }

    public List<String> getComments() {
        return comments.stream().map(WebElement::getText).collect(Collectors.toList());

    }

    public String getNameOfTheClub() {
        return clubName.getText();
    }

}


