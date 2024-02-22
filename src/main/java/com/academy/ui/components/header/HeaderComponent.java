package com.academy.ui.components.header;


import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.AllNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class HeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class, 'user-profile')]")
    protected WebElement profileMenuButton;
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/div[2]/ul/li[1]")
    protected WebElement groupButton;
    @FindBy(xpath = "//a[@href='/news']")
    protected WebElement newsButton;

    @FindBy(xpath = "//span[contains(@class,'avatarIfLogin')]")
    private WebElement isLoggedIn;

    @FindBy(xpath = "//button[contains(@class,'add-club-button')]")
    protected WebElement addClubButton;

    @FindBy(xpath = "//li[contains(@data-menu-id, 'login')]")
    protected WebElement loginDropDownMenu;

    @FindBy(xpath = "//span[contains(@class,\"challenge-text\")]")
    protected WebElement challengeButton;

    @FindBy(xpath = "//ul[contains(@id,\"challenge_ONE-popup\")]")
    protected WebElement headerChallengeDropdownNode;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]")
    protected WebElement profileMenuNode;


    public LoginPopupComponent loginPopupComponent;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public HeaderChallengesDropdown clickChallengeButton(){
        this.getChallengeButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(getHeaderChallengeDropdownNode()));
        return new HeaderChallengesDropdown(driver, getHeaderChallengeDropdownNode());
    }

    public AllNewsPage newsButtonClick() {
        newsButton.click();
        return new AllNewsPage(driver);
    }

    public AddClubPopUpComponent addClubButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(e -> isLoggedIn.isDisplayed());
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

    public GuestMenuComponent openGuestMenu(){
        profileMenuButton.click();
        return new GuestMenuComponent(driver, profileMenuNode);
    }
}
