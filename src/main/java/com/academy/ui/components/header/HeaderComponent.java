package com.academy.ui.components.header;


import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.header.headerMenuComponent.AdminMenuComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.components.header.headerMenuComponent.UserMenuComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.*;
import com.academy.ui.pages.challenges.BaseChallengePage;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

@Getter
public class HeaderComponent extends BaseComponent {
    public LoginPopupComponent loginPopupComponent;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu-light')]")
    protected WebElement cityMenuNode;

    @FindBy(xpath = ".//div[contains(@class, 'user-profile')]")
    protected WebElement profileMenuButton;

    @FindBy(xpath = ".//a[contains(@href,'news')]")
    protected WebElement newsButton;

    @FindBy(xpath = ".//li[.//a[contains(@href, '/news')]]")
    private WebElement newsButtonContainer;

    @FindBy(xpath = ".//a[contains(@href,'clubs')]")
    protected WebElement clubsButton;

    @FindBy(xpath = ".//a[contains(@href,'about')]")
    protected WebElement aboutUsButton;

    @FindBy(xpath = "//li[contains(@data-menu-id,'about')]")
    protected WebElement aboutUsButtonContainer;

    @FindBy(xpath = ".//li[.//a[contains(@href, '/service')]]")
    private WebElement serviceButtonContainer;

    @FindBy(xpath = ".//a[contains(@href,'service')]")
    protected WebElement serviceButton;

    @FindBy(xpath = ".//button[contains(@class,'add-club-button')]")
    protected WebElement addClubButton;

    @FindBy(xpath = "//li[contains(@data-menu-id, 'login')]")
    protected WebElement loginDropDownMenu;

    @FindBy(xpath = "//span[contains(@class,\"challenge-text\")]")
    protected WebElement challengeButton;

    @FindBy(xpath = "//ul[contains(@id,\"challenge_ONE-popup\")]")
    protected WebElement headerChallengeDropdownNode;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]")
    protected WebElement profileMenuNode;

    @FindBy(xpath = "//span[contains(@class,'avatarIfLogin')]")
    private WebElement isLoggedIn;

    @FindBy(xpath = "//span[@aria-label='environment']")
    protected WebElement locationIcon;

    @FindBy(xpath = "//div[@class='ant-dropdown-trigger city']")
    protected WebElement clubsLocationButton;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]/descendant::li[@role='menuitem']")
    protected List<WebElement> citiesLocationOfClubs;

    @FindBy(xpath = "//div[contains(@class,\"logo\")]")
    protected WebElement teachInUkrainianLogo;

    @FindBy(xpath = ".//span[contains(@class,'ant-avatar-icon')]")
    private WebElement avatar;


    @FindBy(xpath = "//li[contains(@data-menu-id, 'profile')]")
    private WebElement profilePageButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public HomePage clickTeachInUkrainianLogo(){
        this.getTeachInUkrainianLogo().click();
        return new HomePage(driver);
    }
    public HeaderChallengesDropdown clickChallengeButton() {
        this.getChallengeButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(getHeaderChallengeDropdownNode()));
        return new HeaderChallengesDropdown(driver, getHeaderChallengeDropdownNode());
    }

    public AllNewsPage newsButtonClick() {
        newsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("news"));
        return new AllNewsPage(driver);
    }

    public BaseChallengePage challengesClick() {
        challengeButton.click();
        clickElement(driver, headerChallengeDropdownNode);
        return new BaseChallengePage(driver);
    }

    public ClubsPage clickClubsPageButton() {
        clubsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("clubs"));
        return new ClubsPage(driver);
    }

    public AboutUsPage clickAboutUsButton() {
        aboutUsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("about"));
        return new AboutUsPage(driver);
    }

    public ServicePage clickServiceButton() {
        serviceButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("service"));
        return new ServicePage(driver);
    }

    @Step("click button addClub")
    public AddClubPopUpComponent addClubButtonClick() {
        waitUntilIsLoggedIn(10);
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

    @Step("Open menu")
    public GuestMenuComponent openGuestMenu() {
        profileMenuButton.click();
        return new GuestMenuComponent(driver, profileMenuNode);
    }

    public AdminMenuComponent openAdminMenu() {
        profileMenuButton.click();
        return new AdminMenuComponent(driver, profileMenuNode);
    }

    public UserMenuComponent openUserMenu() {
        profileMenuButton.click();
        return new UserMenuComponent(driver, profileMenuNode);
    }

    public boolean isLoggedIn(){
        return avatar.getAttribute("class").contains("avatarIfLogin");
    }

    public HeaderComponent waitUntilIsLoggedIn(int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(e -> isLoggedIn());
        return this;
    }
    public ProfilePage openProfilePage(){
        openUserMenu();
        profilePageButton.click();
        return new ProfilePage (driver);

    }

    public HeaderComponent clickCityLocation() {
        clubsLocationButton.click();
        return this;
    }

    public ClubsPage selectClubsCityLocation(String city) {
        citiesLocationOfClubs.stream()
                .filter(currentCity -> currentCity.getText().equals(city))
                .forEach(WebElement::click);
        return new ClubsPage(driver);
    }

    public WebElement openCityMenu() {
        clubsLocationButton.click();
        return cityMenuNode;
    }

    public HeaderComponent moveToWebElement(WebElement webElement) {
        new Actions(driver)
                .moveToElement(webElement)
                .pause(5)
                .perform();
        return this;
    }

}
