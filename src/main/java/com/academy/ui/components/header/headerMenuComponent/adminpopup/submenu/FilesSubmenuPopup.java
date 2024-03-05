package com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class FilesSubmenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'all_files')]")
    private WebElement allFiles;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'unused_files')]")
    private WebElement unusedFiles;

    public FilesSubmenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickAllFiles() {
        clickElement(driver, allFiles); // todo
    }

    public void clickUnusedFiles() {
        clickElement(driver, unusedFiles); // todo
    }
}
