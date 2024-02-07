package com.academy.ui.pages.header;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent extends BaseComponent {
    protected WebElement profileMenuButton;
    protected WebElement groupButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getProfileMenuButton() {
        if (profileMenuButton == null) {
            profileMenuButton = rootElement.findElement(By.xpath(".//div[contains(@class, 'user-profile')]"));
        }
        return profileMenuButton;
    }

    public WebElement getGroupButton() {
        return rootElement.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[2]/ul/li[1]"));
    }

    public void clickMenu() {
        this.getProfileMenuButton().click();
    }
}
