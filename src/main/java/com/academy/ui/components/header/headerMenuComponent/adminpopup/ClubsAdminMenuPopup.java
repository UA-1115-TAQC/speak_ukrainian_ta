package com.academy.ui.components.header.headerMenuComponent.adminpopup;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class ClubsAdminMenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'categories')]")
    private WebElement categories;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'fix_club_categories')]")
    private WebElement fixClubCategories;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'club_approve')]")
    private WebElement clubApprove;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'change_club_owner')]")
    private WebElement challengeClubOwner;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'update_rating')]")
    private WebElement updateRating;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'import_data_base')]")
    private WebElement importDataBase;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'export_data_base')]")
    private WebElement exportDataBase;

    public ClubsAdminMenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickCategories() {
        clickElement(driver, categories); // todo
    }

    public void clickClubWithoutCategories() {
        clickElement(driver, fixClubCategories); // todo
    }

    public void clickChallengeClubOwner() {
        clickElement(driver, clubApprove); // todo
    }

    public void clickImportDataBase() {
        clickElement(driver, challengeClubOwner); // todo
    }

    public void clickExportDataBase() {
        clickElement(driver, updateRating); // todo
    }
}
