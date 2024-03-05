package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FooterComponent extends BaseComponent {

    @FindBy(xpath = "./descendant::div[contains(@class,'footer-logo')]")
    private WebElement logo;

    @FindBy(xpath = "./descendant::div[contains(@class,'text')]")
    private WebElement mottoUnderLogo;

    @FindBy(xpath = "./descendant::div[contains(@class,'links')]//a[contains(@href, 'https')]")
    private List<WebElement> socialLinks;

    @FindBy(xpath = "./descendant::div[contains(@class,'links')]//a[contains(@href, 'https')][1]")
    private WebElement facebookLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'links')]//a[contains(@href, 'https')][2]")
    private WebElement youTubeLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'links')]//a[contains(@href, 'https')][3]")
    private WebElement instagramLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'qubstudio')]")
    private WebElement copyrightNotice;

    @FindBy(xpath = "./descendant::div[@class=\"footer-partners\"]/div[@class=\"article\"]")
    private WebElement sponsorsTitle;

    @FindBy(xpath = "./descendant::div[@class='footer-partners']//a/img")
    private WebElement sponsorsImg;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')]")
    private List<WebElement> sponsorsLinks;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][1]")
    private WebElement softServeLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][2]")
    private WebElement movaObyednueLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][3]")
    private WebElement ederaLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][4]")
    private WebElement eMovaLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][5]")
    private WebElement krainaFMLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][6]")
    private WebElement ucfLink;

    @FindBy(xpath = "./descendant::div[contains(@class,'sponsors')]//a[contains(@href, 'https')][7]")
    private WebElement prostirSvobodiLink;

    @FindBy(xpath = "./descendant::div[@class=\"footer-donate\"]/div[@class=\"article\"]")
    private WebElement donateTitle;

    @FindBy(xpath = "./descendant::div[@class='desc']")
    private WebElement donateExplanation;

    @FindBy(xpath = "./descendant::button[contains(@class,'donate-button')]")
    private WebElement donateButton;

    @FindBy(xpath = "./descendant::div[@class='footer-social']")
    private WebElement socialBlock;

    @FindBy(xpath = "./descendant::div[@class='footer-partners']")
    private WebElement partnersBlock;

    @FindBy(xpath = "./descendant::div[@class='footer-donate']")
    private WebElement donateBlock;

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickOnLogo() {
        logo.click();
    }

    public List<String> getFooterSocialLinks() {
        return socialLinks.stream()
                .map(link -> link.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public List<String> getFooterSponsorsLinks() {
        return sponsorsLinks.stream()
                .map(link -> link.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public void clickOnDonateButton() {
        donateButton.click();
    }

    public void clickOnFacebookLink() {
        facebookLink.click();
    }

    public void clickOnYouTubeLink() {
        youTubeLink.click();
    }

    public void clickOnInstagramLink() {
        instagramLink.click();
    }

    public void clickOnSoftServeLink() {
        softServeLink.click();
    }

    public void clickOnMovaObyednueLink() {
        movaObyednueLink.click();
    }

    public void clickOnEderaLink() {
        ederaLink.click();
    }

    public void clickOneEMovaLink() {
        eMovaLink.click();
    }

    public void clickOnKrainaFMLink() {
        krainaFMLink.click();
    }

    public void clickOnUcfLink() {
        ucfLink.click();
    }

    public void clickOnProstirSvobodiLink() {
        prostirSvobodiLink.click();
    }

    public boolean isSocialBlockIsDisplayed(){
        socialBlock.isDisplayed();
        return true;
    }

    public boolean isPartnerBlockIsDisplayed(){
        partnersBlock.isDisplayed();
        return true;
    }

    public boolean isDonateBlockIsDisplayed(){
        donateBlock.isDisplayed();
        return true;
    }

}
