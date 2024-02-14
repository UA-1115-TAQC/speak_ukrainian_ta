package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FooterComponent extends BaseComponent {
    protected WebElement footerLogo;
    protected List<WebElement> footerSocialLinks;
    protected WebElement footerSponsorsArticle;
    protected List<WebElement> footerSponsorsLinks;
    protected WebElement footerDonateArticle;
    protected WebElement footerDonateButton;

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getFooterLogo() {
        if (footerLogo == null) {
            footerLogo = rootElement.findElement(By.xpath("//div[contains(@class,'footer-logo')]"));
        }
        return footerLogo;
    }

    public void clickFooterLogo() {
        this.getFooterLogo().click();
    }

    public WebElement getFooterSponsorsArticle() {
        if (footerSponsorsArticle == null) {
            footerSponsorsArticle = rootElement.findElement(By.xpath("//div[@class=\"footer-partners\"]/div[@class=\"article\"]"));
        }
        return footerSponsorsArticle;
    }

    public List<WebElement> getFooterSocialLinks() {
        if (footerSocialLinks == null) {
            footerSocialLinks = rootElement.findElements(By.xpath("//div[contains(@class,'links')]//a[contains(@href, 'https')]"));
        }
        return footerSocialLinks;
    }

    public WebElement getFooterDonateArticle() {
        if (footerDonateArticle == null) {
            footerDonateArticle = rootElement.findElement(By.xpath("//div[@class=\"footer-donate\"]/div[@class=\"article\"]"));
        }
        return footerDonateArticle;
    }

    public List<WebElement> getSponsorsLinks() {
        if (footerSponsorsLinks == null) {
            footerSponsorsLinks = rootElement.findElements(By.xpath("//div[contains(@class,'sponsors')]//a[contains(@href, 'https')]"));
        }
        return footerSponsorsLinks;
    }


    public WebElement getFooterDonateButton() {
        if (footerDonateButton == null) {
            footerDonateButton = rootElement.findElement(By.xpath("//button[contains(@class,'donate-button')]"));
        }
        return footerDonateButton;
    }

    public void clickFooterDonateButton() {
        this.getFooterDonateButton().click();
    }

}
