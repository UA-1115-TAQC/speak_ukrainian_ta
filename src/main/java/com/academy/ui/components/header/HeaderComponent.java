package com.academy.ui.components.header;


import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.header.headerMenuComponent.AdminMenuComponent;
import com.academy.ui.components.header.headerMenuComponent.GuestMenuComponent;
import com.academy.ui.components.header.headerMenuComponent.UserMenuComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.AboutUsPage;
import com.academy.ui.pages.AllNewsPage;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.ServicePage;
import com.academy.ui.pages.challenges.BaseChallengePage;
import lombok.Getter;
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

    @FindBy(xpath = ".//a[@href='/dev/about']")
    protected WebElement aboutUsButton;

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

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public HeaderChallengesDropdown clickChallengeButton() {
        this.getChallengeButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(getHeaderChallengeDropdownNode()));
        return new HeaderChallengesDropdown(driver, getHeaderChallengeDropdownNode());
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(e -> isLoggedIn.isDisplayed());
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
}
