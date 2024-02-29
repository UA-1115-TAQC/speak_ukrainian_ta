package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void openAddCentreForm() {
        clickElement(driver, addCentre);
//        return new AddCentreFormComponent(driver, addCentre); // todo
    }

    public void clickSearchCertificate() {
        clickElement(driver, searchCertificates); // todo
    }

    public ProfilePage clickProfile() {
        clickElement(driver, profile);
        return new ProfilePage(driver);
    }

    public UserMenuComponent clickLogout() {
        logout.click();
        return this;
    }
}
