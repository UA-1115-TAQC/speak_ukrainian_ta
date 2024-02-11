package com.academy.ui.pages.component;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClubCardLineComponent extends BaseComponent {
    private WebElement clubLogo;
    private WebElement clubTitle;
    private WebElement clubTags;
    private WebElement clubDescription;
    private WebElement clubLink;
    public ClubCardLineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getClubLogo() {
        if (clubLogo == null) {
            clubLogo = rootElement.findElement(By.xpath(".//div[contains(@class, 'icon')]//img"));
        }
        return clubLogo;
    }

    public WebElement getClubTitle() {
        if (clubTitle == null) {
            clubTitle = rootElement.findElement(By.xpath(".//div[contains(@class, 'name')]"));
        }
        return clubTitle;
    }

    public WebElement getClubTags() {
        if (clubTags == null) {
            clubTags = rootElement.findElement(By.xpath(".//span[contains(@class, 'name')]"));
        }
        return clubTags;
    }

    public WebElement getClubDescription() {
        if (clubDescription == null) {
            clubDescription = driver.findElement(By.xpath(".//p[contains(@class, 'description')]"));
        }
        return clubDescription;
    }

    public WebElement getClubLink() {
        if (clubLink == null) {
            clubLink = rootElement.findElement(By.xpath(".//a//a[contains(@href, '/club/')]"));
        }
        return clubLink;
    }
}
