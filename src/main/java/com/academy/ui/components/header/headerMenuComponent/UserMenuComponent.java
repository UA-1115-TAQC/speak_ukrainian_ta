package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//div[contains(@class,'modal-add-club')]")
    private WebElement addClubModalForm;

    public UserMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddClubPopUpComponent openAddClubForm() {
        addClub.click();
        return new AddClubPopUpComponent(driver);
    }

    public UserMenuComponent clickLogout() {
        logout.click();
        return this;
    }
}
