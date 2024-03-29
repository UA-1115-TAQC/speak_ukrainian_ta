package com.academy.ui.pages;

import com.academy.ui.components.carousel.CarouselCardComponent;
import com.academy.ui.components.carousel.CarouselImgComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.pages.challenges.ChallengeUkrainianClubSpeakPage;
import com.academy.ui.pages.facebookpages.LanguageSphereFacebookPage;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.qameta.allure.Description;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;

@Getter
public class HomePage extends BasePageWithAdvancedSearch {
    public CarouselImgComponent carouselImgComponent;
    public CarouselCardComponent carouselCardComponent;
    @FindBy(xpath="//div[contains(@class,\"about-carousel-block\")]")
    protected WebElement carouselImgComponentWebElement;
    @FindBy(xpath="//div[contains(@class,\"categories-carousel-block\")]")
    protected WebElement carouselCardComponentWebElement;
    @FindBy(xpath= CHALLENGE_DESCRIPTION_PATH + "/h2")
    protected WebElement challengeDescriptionHeading;
    @FindBy(xpath=CHALLENGE_DESCRIPTION_PATH + "/span")
    protected WebElement challengeDescriptionText;
    @FindBy(xpath=CHALLENGE_DESCRIPTION_PATH +"//button")
    protected WebElement challengeFindOutMoreButtonNode;
    @FindBy(xpath="//div[contains(@class,\"about-challenge\")]//img")
    protected WebElement challengeImage;
    @FindBy(xpath="//div[contains(@class,\"speakingclub-description\")]//h2")
    protected WebElement speakingClubHeading;
    @FindBy(xpath="//img[contains(@class,\"banner-image\")]")
    protected WebElement speakingClubImage;
    protected final String  CHALLENGE_DESCRIPTION_PATH = "//div[contains(@class,\"challenge-description\")]";
    protected JavascriptExecutor jsExecutor;
    protected WebDriverWait wait;
    public HomePage(WebDriver driver) {
        super(driver);
        this.carouselCardComponent = getCarouselCardComponent();
        this.carouselImgComponent = getCarouselImgComponent();
    }
    public WebElement getChallengeFindOutMoreButton(){
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getChallengeFindOutMoreButtonNode());
            wait = new WebDriverWait(driver,Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(getChallengeFindOutMoreButtonNode()));
            return getChallengeFindOutMoreButtonNode();
    }
    public CarouselCardComponent getCarouselCardComponent() {
        return carouselCardComponent == null ?
                carouselCardComponent = new CarouselCardComponent(driver, carouselCardComponentWebElement) :
                carouselCardComponent;
    }

    public CarouselImgComponent getCarouselImgComponent() {
        return carouselImgComponent == null ?
                carouselImgComponent = new CarouselImgComponent(driver, carouselImgComponentWebElement) :
                carouselImgComponent;
    }
    @Step("Click the 'Find Out More' button")
    public BaseChallengePage clickChallengeFindOutMoreButton() {
        this.getChallengeFindOutMoreButton().click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        BaseChallengePage baseChallengePage =new BaseChallengePage(driver);
        wait.until(ExpectedConditions.visibilityOf(baseChallengePage.getChallengeImageText()));
        return baseChallengePage;
    }
    @Step("Click the speaking club heading")
    public ChallengeUkrainianClubSpeakPage clickSpeakingClubHeading() {
        this.getSpeakingClubHeading().click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ChallengeUkrainianClubSpeakPage challengeUkrainianClubSpeakPage = new ChallengeUkrainianClubSpeakPage(driver);
        wait.until(ExpectedConditions.visibilityOf(challengeUkrainianClubSpeakPage.getChallengeImageText()));
        return challengeUkrainianClubSpeakPage;
    }
    @Step("Click the speaking club image")
    @Description("Clicks the image of the speaking club section and switches to a new tab.")
    public LanguageSphereFacebookPage clickSpeakingClubImage(){
        int previousTabAmount = getTabHandles().size();
        this.getSpeakingClubImage().click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfWindowsToBe( previousTabAmount +1));
        switchToANewTabByItsIndex(previousTabAmount);
        LanguageSphereFacebookPage languageSphereFacebookPage =  new LanguageSphereFacebookPage(driver);
        wait.until(ExpectedConditions.visibilityOf(languageSphereFacebookPage.getFacebookLogo()));
        return languageSphereFacebookPage;
    }

    public HomePage scrollToAllClubsButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", carouselCardComponent.getCarouselCardAllClubsButton());
        return this;
    }

    public HomePage scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        return this;
    }


    @Step("Scroll to carousel with club cards")
    public HomePage scrollToCarouselCardComponentWebElement() {
        Actions action = new Actions(driver);
        action.moveToElement(carouselCardComponentWebElement).perform();
        action.sendKeys(Keys.PAGE_DOWN).perform();
        return this;
    }

    public void waitUntilHomePageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String firstPageURL = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
        wait.until((ExpectedCondition<Boolean>) driver -> firstPageURL.equals(driver.getCurrentUrl()));
    }

    public void waitUntilHomePageIsVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(carouselCardComponentWebElement));
    }

    public CarouselCardComponent clickClubCarouselRightArrow(){
        carouselCardComponent.clickRightArrowButton();
        waitUntilHomePageIsVisible();
        return getCarouselCardComponent();
    }

}
