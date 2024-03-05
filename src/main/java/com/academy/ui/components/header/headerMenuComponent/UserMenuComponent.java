package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ProfilePage;
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

    public AddClubPopUpComponent openAddClubForm() {
        clickElement(driver, addClub);
        return new AddClubPopUpComponent(driver);
    }

    public AddCenterPopUpComponent openAddCentreForm() {
        clickElement(driver, addCentre);
        return new AddCenterPopUpComponent(driver);
    }

    public void clickSearchCertificate() {
        clickElement(driver, searchCertificates); // todo
    }

    public ProfilePage clickProfile() {
        clickElement(driver, profile);
        return new ProfilePage(driver);
    }

    public UserMenuComponent clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
        return this;
    }
}
