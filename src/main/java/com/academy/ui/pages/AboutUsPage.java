package com.academy.ui.pages;

import com.academy.ui.SocialInfoComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AboutUsPage extends BasePageWithAdvancedSearch{

    @FindBy (xpath = ".//div[@class='title']")
    private WebElement banner;

    @FindBy (xpath = ".//div[@class='title']/span[@class='text']")
    private WebElement bannerFirstRowText;

    @FindBy (xpath = ".//div[@class='title']/span[@class='content']")
    private WebElement bannerSecondRowText;

    @FindBy (xpath = ".//div[@class='social-info']")
    private WebElement socialInfoXpath;

    @FindBy(xpath = ".//div[@class ='social-info']/descendant::button")
    private WebElement helpProjectButton;

    private SocialInfoComponent socialInfoComponent;

    public AboutUsPage(WebDriver driver) {
        super(driver);
        this.socialInfoComponent = new SocialInfoComponent(driver, socialInfoXpath);
    }

    public void clickHelpProjectButton() {
        helpProjectButton.click();
    }
}
