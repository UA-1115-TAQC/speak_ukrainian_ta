package com.academy.ui.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClubInfoPopUp extends BasePopUp {
    @FindBy(xpath = ".//div[contains(@class, 'club-name')]")
    private WebElement clubTitle;

    @FindBy(xpath = ".//div[@class = 'tags categories']//span[contains(@class, 'name')]")
    private List<WebElement> clubTags;

    @FindBy(xpath = ".//div[@class = 'address']//span[@class = 'text']")
    private WebElement clubAddress;

    @FindBy(xpath = ".//div[@class = 'age']//span[contains(@class, 'sider-label')]")
    private WebElement clubAgeSiderLabel;

    @FindBy(xpath = ".//span[@class = 'years']")
    private WebElement clubAgeYears;

    @FindBy(xpath = ".//button//a[contains(@href, '/club/')]")
    private WebElement aboutClubButton;

    @FindBy(xpath = ".//span[@class = 'title']")
    private WebElement aboutClubTitle;

    @FindBy(xpath = ".//div[contains(@class, 'about')]//div[@class = 'description']")
    private WebElement aboutClubDescription;

    public ClubInfoPopUp(WebDriver driver, String className) {
        super(driver, className);
    }
}
