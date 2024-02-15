package com.academy.ui.components;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class FooterComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'footer-logo')]")
    private WebElement footerLogo;

    @FindBy(xpath = "//div[contains(@class,'links')]//a[contains(@href, 'https')]")
    private List<WebElement> footerSocialLinks;

    @FindBy(xpath = "//div[@class=\"footer-partners\"]/div[@class=\"article\"]")
    private WebElement footerSponsorsArticle;

    @FindBy(xpath = "//div[contains(@class,'sponsors')]//a[contains(@href, 'https')]")
    private List<WebElement> footerSponsorsLinks;

    @FindBy(xpath = "//div[@class=\"footer-donate\"]/div[@class=\"article\"]")
    private WebElement footerDonateArticle;
    @FindBy(xpath = "//button[contains(@class,'donate-button')]")
    private WebElement footerDonateButton;

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickOnFooterLogo() {
        footerLogo.click();
    }

    public String getFooterSponsorsArticle() {
        return footerSponsorsArticle.getText();
    }

    public List<String> getFooterSocialLinks() {
        return footerSocialLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getFooterDonateArticle() {
        return footerDonateArticle.getText();
    }

    public List<String> getFooterSponsorsLinks() {
        return footerSponsorsLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickOnFooterDonateButton() {
        footerDonateButton.click();
    }
}
