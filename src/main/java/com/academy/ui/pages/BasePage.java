package com.academy.ui.pages;

import com.academy.ui.pages.header.HeaderComponent;
import com.academy.ui.pages.header.LogonPopupComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage extends Base {
    public HeaderComponent header;
    public LogonPopupComponent logonPopupComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
        this.logonPopupComponent = getLogonPopupComponent();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }

    public LogonPopupComponent getLogonPopupComponent() {
        if (logonPopupComponent == null) {
            WebElement node = driver.findElement(By.xpath("//div[contains(@class, 'right-side-menu')]"));
            logonPopupComponent = new LogonPopupComponent(driver, node);
        }
        return logonPopupComponent;
    }

    public void openURL(String url) {
        driver.get(url);
    }
}
