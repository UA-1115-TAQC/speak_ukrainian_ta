package com.academy.ui.components.carousel;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class ClubDirectionCard extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class,\"icon-box\")]/img")
    protected WebElement clubCardImage;
    @FindBy(xpath = ".//div[contains(@class,\"name\")]")
    protected WebElement clubCardHeading;
    @FindBy(xpath = ".//div[contains(@class,\"description\")]")
    protected WebElement clubCardText;
    @FindBy(xpath = ".//div[contains(@class,\"details\")]")
    protected WebElement clubCardButton;
    @FindBy(xpath = ".//span[@aria-label=\"arrow-right\"]")
    protected WebElement clubCardButtonPointer;
    public ClubDirectionCard(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public ClubsPage clickClubCardButton() {
        this.getClubCardButton().click();
        return new ClubsPage(driver).waitClubsPageWithSiderLoaded(30);
    }
    public ClubsPage clickClubCardButtonPointer() {
        this.getClubCardButtonPointer().click();
        return new ClubsPage(driver).waitUntilClubsPageIsLoaded(30);
    }

    public ClubsPage clickCard(){
        clubCardText.click();
        return new ClubsPage(driver).waitClubsPageWithSiderLoaded(30);
    }
}
