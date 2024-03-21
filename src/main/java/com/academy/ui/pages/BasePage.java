package com.academy.ui.pages;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.components.header.HeaderComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@Getter
public abstract class BasePage extends BasePageWithoutHeaderAndFooter {
    public HeaderComponent header;
    public FooterComponent footer;
    @FindBy(xpath = "//div[@class='ant-message-notice-wrapper']/descendant::div[contains(@class, 'ant-message-error') or contains(@class, 'ant-message-success')]")
    private WebElement topNoticeMessage;

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

    @Step("Wait until page is completely loaded")
    public void waitUntilPageIsCompletelyLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }
}
