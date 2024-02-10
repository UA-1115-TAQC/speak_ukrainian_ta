package com.academy.ui.pages;

import com.academy.ui.pages.header.HeaderComponent;
import com.academy.ui.pages.header.LoginPopupComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage extends Base {
    public HeaderComponent header;
    public LoginPopupComponent loginPopupComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
        this.loginPopupComponent = getLogonPopupComponent();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }

    public LoginPopupComponent getLogonPopupComponent() {
        if (loginPopupComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class, 'right-side-menu')]"));
            loginPopupComponent = new LoginPopupComponent(driver, node);
        }
        return loginPopupComponent;
    }

    public void openURL(String url) {
        driver.get(url);
    }
}
