package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.pages.SearchCertificatePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

@Getter
public class UserMenuComponent extends BaseComponent {

    @FindBy(xpath = ".//li[contains(@data-menu-id, 'add_club')]")
    private WebElement addClub;
    @FindBy(xpath = ".//li[contains(@data-menu-id, 'add_centre')]")
    private WebElement addCentre;
    @FindBy(xpath = ".//li[contains(@data-menu-id, 'search_certificates')]")
    private WebElement searchCertificates;
    @FindBy(xpath = ".//li[contains(@data-menu-id, 'profile')]")
    private WebElement profile;
    @FindBy(xpath = ".//li[contains(@data-menu-id, 'logout')]")
    private WebElement logout;

    public UserMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Open add club form")
    public AddClubPopUpComponent openAddClubForm() {
        clickElement(driver, addClub);
        return new AddClubPopUpComponent(driver);
    }

    @Step("Open add centre form")
    public AddCenterPopUpComponent openAddCentreForm() {
        clickElement(driver, addCentre);
        return new AddCenterPopUpComponent(driver);
    }

    @Step("Open search certificate page")
    public SearchCertificatePage clickSearchCertificate() {
        clickElement(driver, searchCertificates);
        return new SearchCertificatePage(driver);
    }

    @Step("Open profile page")
    public ProfilePage clickProfile() {
        clickElement(driver, profile);
        return new ProfilePage(driver);
    }

    @Step("Click logout")
    public UserMenuComponent clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
        return this;
    }
}
