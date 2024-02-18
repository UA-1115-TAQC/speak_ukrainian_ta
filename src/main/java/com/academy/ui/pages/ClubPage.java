package com.academy.ui.pages;

import com.academy.ui.components.CommentsClubComponent;
import com.academy.ui.components.FooterComponent;
import com.academy.ui.pages.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



@Getter
public class ClubPage extends BasePage {

    private CommentsClubComponent commentsClubComponent;

    @FindBy(xpath = "//div[@class='comments-container']")
    private WebElement commentsClubComponentWebelement;

    private FooterComponent footerComponent;

    @FindBy(xpath = "//footer[contains(@class,'ant-layout-footer footer')]")
    private WebElement footerComponentWebelement;

    private HeaderComponent headerComponent;

    @FindBy(xpath = "//header[contains(@class,'ant-layout-header header')]")
    private WebElement headerComponentWebelement;


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

    public CommentsClubComponent getCommentsClubComponent(){
        return commentsClubComponent == null ?
                commentsClubComponent = new CommentsClubComponent(driver, commentsClubComponentWebelement):
                commentsClubComponent;
    }

    public HeaderComponent getHeaderComponent(){
        return headerComponent == null ?
                headerComponent = new HeaderComponent(driver,headerComponentWebelement):
                headerComponent;
    }

    private FooterComponent getFooterComponent(){
        return footerComponent == null ?
                footerComponent = new FooterComponent(driver,footerComponentWebelement):
                footerComponent;
    }

    public String getDescriptionOgTheClub(){
        return getContent().getText();
    }


}


