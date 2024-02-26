package com.academy.ui;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SocialInfoComponent extends BaseComponent {
    @FindBy(xpath = ".//span[@class = 'text']")
    private WebElement socialMediaTitle;

    @FindBy(xpath = ".//div[@class='links']/a[@href !='']")
    private List<WebElement> socialMedias;

    public SocialInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickFacebookIcon() {
        socialMedias.stream()
                .filter(e -> e.getAttribute("href").contains("facebook"))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickYoutubeIcon() {
        socialMedias.stream()
                .filter(e -> e.getAttribute("href").contains("youtube"))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickInstagramIcon() {
        socialMedias.stream()
                .filter(e -> e.getAttribute("href").contains("instagram"))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickMailIcon() {
        socialMedias.stream()
                .filter(e -> e.getAttribute("href").contains("mailto"))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
