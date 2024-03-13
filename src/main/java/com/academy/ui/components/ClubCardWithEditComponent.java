package com.academy.ui.components;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.pages.ApplicationPage;
import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubPage;
import com.academy.ui.pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ClubCardWithEditComponent extends ClubCardComponent {

    @FindBy(xpath = ".//div[@class='title-name']")
    protected WebElement title;

    @FindBy(xpath = ".//span[@aria-label='more']")
    private WebElement moreButton;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]")
    private WebElement moreButtonMenu;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li")
    private List<WebElement> moreButtonMenuItems;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li[1]")
    private WebElement participantsClubMenuItem;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li[2]")
    private WebElement editClubMenuItem;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li[3]")
    private WebElement deleteClubMenuItem;

    public ClubCardWithEditComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddClubPopUpComponent clickEditClub() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(editClubMenuItem)).click();
        return new AddClubPopUpComponent(driver);
    }

    public ApplicationPage clickParticipantsClub() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(participantsClubMenuItem)).click();
        return new ApplicationPage(driver);
    }

    public ProfilePage clickDeleteClub() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(deleteClubMenuItem)).click();
        return new ProfilePage(driver);
    }

    public ClubCardWithEditComponent clickMoreButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(moreButton)).click();
        return this;
    }

    @Override
    public WebElement getTitle() {
        return title;
    }

    @Override
    public String getClubName(){
        return this.title.getText();
    }
}
