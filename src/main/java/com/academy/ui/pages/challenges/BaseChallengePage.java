package com.academy.ui.pages.challenges;

import com.academy.ui.pages.BasePageWithAdvancedSearch;
import com.academy.ui.pages.facebookpages.LanguageSphereFacebookPage;
import com.academy.ui.pages.payment.PaymentPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class BaseChallengePage extends BasePageWithAdvancedSearch {
   protected final  String  CHALLENGE_IMAGE_PATH = "//div[contains(@class,\"banner\")]";
   protected final String HELP_BUTTON_PATH="//div[contains(@class,\"help-button\")]";
   protected final String SOCIAL_MEDIA_PATH="//div[contains(@class,\"social-info\")]";
   public BaseChallengePage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath =  CHALLENGE_IMAGE_PATH)
    protected WebElement challengeImage;
    @FindBy(xpath = CHALLENGE_IMAGE_PATH +"/span[contains(@class,\"title\")]")
   protected WebElement challengeImageText;
    @FindBy(xpath = HELP_BUTTON_PATH+"//button")
   protected WebElement helpProjectButton;
    @FindBy(xpath = HELP_BUTTON_PATH+"/a")
    protected WebElement helpProjectLink;
    @FindBy(xpath = SOCIAL_MEDIA_PATH+"//span[contains(@class,\"text\")]")
   protected WebElement contactsText;
    @FindBy(xpath = SOCIAL_MEDIA_PATH+"//div[contains(@class,\"links\")]/a")
   protected List<WebElement> contactsSocialMediaIcons;
    @FindBy(xpath = "//div[contains(@class,\"challenge-description\")]")
   protected WebElement challengeDescriptionContainer;
   @FindBy(xpath = "//div[contains(@class,\"button-box\")]//button")
   protected WebElement signUpForAChallengeButton;
@   FindBy(xpath = "//div[contains(@class,\"ant-tooltip-inner\")]")
   protected WebElement signUpForAChallengeButtonTooltip;
    @Step("Get social media icon by index: {index}")
    @Description("Get the social media icon by its index in the list.")
    public WebElement getSocialMediaIconByIndex(int index){
        if(index >=0 && index < getContactsSocialMediaIcons().size()){
            return getContactsSocialMediaIcons().get(index);
        }
        throw new Error("The index must be between 0 and " + (getContactsSocialMediaIcons().size()-1)+", inclusive.");
    }
    @Step("Click social media icon by index: {index}")
    @Description("Click the social media icon by its index in the list, switch to a new tab.")
    public void clickSocialMediaIconByIndex(int index){
        int previousTabAmount = getTabHandles().size();
        getSocialMediaIconByIndex(index).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfWindowsToBe( previousTabAmount +1));
        switchToANewTabByItsIndex(previousTabAmount);
    }
    @Step("Click 'Help Project' button and switch to a new tab")
    @Description("Click the 'Help Project' button, switch to a new tab, and return the PaymentPage.")
    public PaymentPage clickHelpProjectButton(){
        int previousTabAmount = getTabHandles().size();
        getHelpProjectButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfWindowsToBe( previousTabAmount +1));
        switchToANewTabByItsIndex(previousTabAmount);
        PaymentPage paymentPage = new PaymentPage(driver);
        wait.until(ExpectedConditions.visibilityOf(paymentPage.getLargeLogoImage()));
        return paymentPage;
    }
    @Step("Click 'Sign Up For A Challenge' button")
    public void clickSignUpForAChallengeButton(){
        this.getSignUpForAChallengeButton().click();
    }
}
