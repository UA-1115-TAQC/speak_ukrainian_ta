package com.academy.ui.components;

import com.academy.ui.pages.ClubPage;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class ClubCardLineComponent extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class, 'icon')]//img")
    private WebElement clubLogo;

    @FindBy(xpath = ".//div[contains(@class, 'name')]")
    private WebElement clubTitle;

    @FindBy(xpath = ".//span[contains(@class, 'name')]")
    private List<WebElement> clubTags;

    @FindBy(xpath = ".//p[contains(@class, 'description')]")
    private WebElement clubDescription;

    @FindBy(xpath = ".//span[@class = 'oneAddress']")
    private WebElement clubAddress;

    @FindBy(xpath = ".//a//a[contains(@href, '/club/')]")
    private WebElement clubLink;

    public ClubCardLineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ClubPage clubLinkClick() {
        clubLink.click();
        return new ClubPage(driver);
    }

    public String getClubAddress() {
        return clubAddress.getText();
    }

    public List<String> getClubTags() {
        return clubTags.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public ClubInfoPopUp clubLogoClick() {
        clubLogo.click();
        ClubInfoPopUp clubInfoPopUp = new ClubInfoPopUp(driver,
                driver.findElement(xpath("//div[contains(@class, 'clubInfo')]")));
        clubInfoPopUp.waitPopUpOpen(500);
        return clubInfoPopUp;
    }
}
