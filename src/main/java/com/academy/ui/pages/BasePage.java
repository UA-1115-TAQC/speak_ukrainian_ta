package com.academy.ui.pages;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.components.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public abstract class BasePage extends BasePageWithoutHeaderAndFooter {
    public HeaderComponent header;
    public FooterComponent footer;
    @FindBy(xpath = "//div[@class='ant-message-notice-wrapper']/descendant::div[contains(@class, 'ant-message-error') or contains(@class, 'ant-message-success')]")
    private WebElement loginNotice;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
        this.footer = getFooter();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public FooterComponent getFooter() {
        if (footer == null) {
            WebElement node = driver.findElement(By.xpath(".//footer"));
            footer = new FooterComponent(driver, node);
        }
        return footer;
    }
}
