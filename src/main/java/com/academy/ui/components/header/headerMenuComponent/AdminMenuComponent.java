package com.academy.ui.components.header.headerMenuComponent;

import com.academy.ui.components.header.headerMenuComponent.adminpopup.ClubsAdminMenuPopup;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.ContentAdminMenuPopup;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.WebsiteAdminMenuPopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class AdminMenuComponent extends UserMenuComponent {
    @FindBy(xpath = "//div[contains(@aria-controls, 'content-popup')]")
    private WebElement contentPopup;
    @FindBy(xpath = "//div[contains(@aria-controls, 'locations-popup')]")
    private WebElement locationsPopup;
    @FindBy(xpath = "//div[contains(@aria-controls, 'clubs-popup')]") // /html/body/div[4]/div/ul/li[4]
    private WebElement clubsPopup;
    @FindBy(xpath = "//div[contains(@aria-controls, 'website-popup')]")
    private WebElement websitePopup;

    public AdminMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ContentAdminMenuPopup openContentPopup() {
        clickElement(driver, contentPopup);
        return new ContentAdminMenuPopup(driver, contentPopup);
    }

    public ContentAdminMenuPopup openLocationsPopup() {
        clickElement(driver, locationsPopup);
        return new ContentAdminMenuPopup(driver, locationsPopup);
    }

    public ClubsAdminMenuPopup openClubsPopup() {
        clickElement(driver, clubsPopup);
        return new ClubsAdminMenuPopup(driver, clubsPopup);
    }

    public WebsiteAdminMenuPopup openWebsitePopup() {
        clickElement(driver, websitePopup);
        return new WebsiteAdminMenuPopup(driver, websitePopup);
    }
}
