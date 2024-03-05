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
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

@Getter
public class HeaderComponent extends BaseComponent {
    public LoginPopupComponent loginPopupComponent;

    @FindBy(xpath = ".//div[contains(@class, 'user-profile')]")
    protected WebElement profileMenuButton;

    @FindBy(xpath = ".//a[@href='/dev/news']")
    protected WebElement newsButton;

    @FindBy(xpath = ".//a[@href='/dev/clubs']")
    protected WebElement clubsButton;
    //@FindBy(xpath = ".//a[@href='/about']") - for a test to pass on the production level site
    @FindBy(xpath = ".//a[@href='/dev/about']")
    protected WebElement aboutUsButton;

    @FindBy(xpath = "//li[contains(@data-menu-id,'about')]")
    protected WebElement aboutUsButtonContainer;

    @FindBy(xpath = ".//a[@href='/dev/service']")
    protected WebElement serviceButton;

    @FindBy(xpath = ".//button[contains(@class,'add-club-button')]")
    protected WebElement addClubButton;

    @FindBy(xpath = "//li[contains(@data-menu-id, 'login')]")
    protected WebElement loginDropDownMenu;

    @FindBy(xpath = "//span[contains(@class,\"challenge-text\")]")
    protected WebElement challengeButton;

    @FindBy(xpath = "//ul[contains(@id,\"challenge_ONE-popup\")]")
    protected WebElement headerChallengeDropdownNode;

    @FindBy(xpath = "//div[contains(@class, 'city')]")
    protected WebElement cityButton;

    @FindBy(xpath = "//div[contains(@class, 'city')]/span[1]")
    protected WebElement locationIcon;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu-light')]")
    protected WebElement cityMenuNode;

    @FindBy(xpath = "//ul[contains(@class, 'ant-dropdown-menu')]")
    protected WebElement profileMenuNode;

    @FindBy(xpath = "//li[contains(@class, 'ant-dropdown-menu-item-only-child')]")
    protected List<WebElement> cityMenuElements;

    @FindBy(xpath = "//span[contains(@class,'avatarIfLogin')]")
    private WebElement isLoggedIn;

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

    public WebElement openCityMenu() {
        cityButton.click();
        return cityMenuNode;
    }

    public AllNewsPage newsButtonClick() {
        newsButton.click();

        return new AllNewsPage(driver);
    }

    public BaseChallengePage challengesClick() {
        challengeButton.click();
        clickElement(driver, headerChallengeDropdownNode);
        return new BaseChallengePage(driver);
    }

    public ClubsPage clickClubsPageButton() {
        clubsButton.click();
        return new ClubsPage(driver);
    }

    public AboutUsPage clickAboutUsButton() {
        aboutUsButton.click();
        return new AboutUsPage(driver);
    }

    public ServicePage clickServiceButton() {
        serviceButton.click();
        return new ServicePage(driver);
    }

    public AddClubPopUpComponent addClubButtonClick() {
        waitUntilIsLoggedIn(10);
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

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

    public List<WebElement> getCityMenuElements() {
        if (cityMenuElements == null || cityMenuElements.isEmpty())
            cityMenuElements = openCityMenu().findElements(By.xpath("//li[contains(@class, 'ant-dropdown-menu-item-only-child')]"));
        return cityMenuElements;
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
}
