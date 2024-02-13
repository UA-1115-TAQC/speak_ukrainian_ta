package com.academy.ui.pages.component;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class ClubCardLineComponent extends BaseComponent {
    private WebElement clubLogo;
    private WebElement clubTitle;
    private WebElement clubTags;
    private WebElement clubDescription;
    private WebElement clubLink;
    public ClubCardLineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //when ClubPage will be implemented
    /*public ClubPage toClubPage() {
        getClubLink().click();
        return new ClubPage(driver);
    }*/

    public WebElement getClubLogo() {
        if (clubLogo == null) {
            clubLogo = rootElement.findElement(xpath(".//div[contains(@class, 'icon')]//img"));
        }
        return clubLogo;
    }

    public WebElement getClubTitle() {
        if (clubTitle == null) {
            clubTitle = rootElement.findElement(xpath(".//div[contains(@class, 'name')]"));
        }
        return clubTitle;
    }

    public WebElement getClubTags() {
        if (clubTags == null) {
            clubTags = rootElement.findElement(xpath(".//span[contains(@class, 'name')]"));
        }
        return clubTags;
    }

    public WebElement getClubDescription() {
        if (clubDescription == null) {
            clubDescription = driver.findElement(xpath(".//p[contains(@class, 'description')]"));
        }
        return clubDescription;
    }

    public WebElement getClubLink() {
        if (clubLink == null) {
            clubLink = rootElement.findElement(xpath(".//a//a[contains(@href, '/club/')]"));
        }
        return clubLink;
    }
}
