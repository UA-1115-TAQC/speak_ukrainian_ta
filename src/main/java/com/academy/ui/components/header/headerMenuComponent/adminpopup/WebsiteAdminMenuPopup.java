package com.academy.ui.components.header.headerMenuComponent.adminpopup;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class WebsiteAdminMenuPopup extends BaseComponent {

    @FindBy(xpath = "//li[contains(@data-menu-id, 'banners')]")
    private WebElement banners;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'news')]")
    private WebElement news;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'about')]")
    private WebElement about;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'contact_types')]")
    private WebElement contacts;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'faq')]")
    private WebElement faq;

    public WebsiteAdminMenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickBanners() {
        clickElement(driver, banners); // todo
    }

    public void clickNews() {
        clickElement(driver, news); // todo
    }

    public void clickContacts() {
        clickElement(driver, contacts); // todo
    }

    public void clickAbout() {
        clickElement(driver, about); // todo
    }

    public void clickFaq() {
        clickElement(driver, faq); // todo
    }
}
