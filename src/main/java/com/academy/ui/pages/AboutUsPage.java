package com.academy.ui.pages;

import com.academy.ui.SocialInfoComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = ".//div[@class='title-content' and text()='Про ініціативу']")
    private WebElement titleAboutInitiative;

    @FindBy(xpath = ".//div[@class='content-text']/p[contains(text(),'Ініціатива\"Навчай українською\"')]")
    private WebElement descriptionAboutInitiative;

    @FindBy(xpath = ".//span[text()='Наталка Федечко']")
    private WebElement titleNatalkaFedechko;

    @FindBy(xpath = ".//img[contains(@src,'profile_222,jpg')]")
    private WebElement photoNatalkaFedechko;

    @FindBy(xpath = ".//span[text()='Наталка Федечко']/following-sibling::p/span[@class='highlight']")
    private WebElement descriptionNatalkaFedechko;

    @FindBy(xpath = ".//span[text()='Наталка Федечко']/following-sibling::p[2]")
    private WebElement textBlockNatalkaFedechko;

    @FindBy(xpath = ".//span[text()='Іванна Кобєлєва']")
    private WebElement titleIvannaKobeleva;

    @FindBy(xpath = ".//img[contains(@src,'profile_111.jpg')]")
    private WebElement photoIvannaKobeleva;

    @FindBy(xpath = ".//span[text()='Іванна Кобєлєва']/following-sibling::p/span[@class='highlight']")
    private WebElement descriptionIvannaKobeleva;

    @FindBy(xpath = ".//span[text()='Іванна Кобєлєва']/following-sibling::p[2]")
    private WebElement textBlockIvannaKobeleva;

    @FindBy(xpath = ".//div[@class='title-content' and contains(text(),'16 січня 2021')]")
    private WebElement titleToPushTeachUkrainian;

    @FindBy(xpath = ".//div[@class='content-text']/p[contains(text(),'16 січня 2021')]")
    private WebElement descriptionToPushTeachUkrainian;

    @FindBy(xpath = ".//div[@class='video']")
    private WebElement videoToPushTeachUkrainian;

    @FindBy(xpath = ".//div[@class='title-content' and text()='Амбасадори проєкту']")
    private WebElement titleAmbassadorsOfProject;

    @FindBy(xpath = ".//img[contains(@src,'Marichka_Padalkooo.png')]")
    private WebElement photoMarichkaPadalkooo;

    @FindBy(xpath = ".//p[contains(text(),'Марічка Падалко')]")
    private WebElement textBlockMarichkaPadalkooo;

    @FindBy(xpath = ".//img[contains(@src,'img_11.jpg')]")
    private WebElement photoIgorKondratiuk;

    @FindBy(xpath = ".//p[contains(text(),'Ігор Кондратюк')]")
    private WebElement textBlockIgorKondratiuk;

    @FindBy(xpath = ".//img[contains(@src,'img_22.jpg')]")
    private WebElement photoTarasTopolia;

    @FindBy(xpath = ".//p[contains(text(),'Тарас Тополя')]")
    private WebElement textBlockTarasTopolia;

    @FindBy(xpath = ".//img[contains(@src,'img_33.png')]")
    private WebElement photoSerhiyStahovskiy;

    @FindBy(xpath = ".//p[contains(text(),'Сергій Стаховський ')]")
    private WebElement textBlockSerhiyStahovskiy;

    @FindBy(xpath = ".//div[@class='title-content' and text()='Відгуки учасників челенджу']")
    private WebElement titleChallengeFeedback;

    @FindBy(xpath = ".//div[text()='Відгуки учасників челенджу']/ancestor::div[@class='content']/following-sibling::div[@class='content']//p")
    private List<WebElement> listOfChallengeComments;

    private SocialInfoComponent socialInfoComponent;

    public AboutUsPage(WebDriver driver) {
        super(driver);
        this.socialInfoComponent = new SocialInfoComponent(driver, socialInfoXpath);
    }

    @Step("Click on the button 'Допомогти проєкту'")
    public void clickHelpProjectButton() {
        helpProjectButton.click();
    }

    @Step("Get list of challenge comments")
    public List<String> getListTextOfChallengeComments() {
        List<String> list = new ArrayList<>();
        listOfChallengeComments.forEach(comment -> list.add(comment.getText()));
        return list;
    }
}
