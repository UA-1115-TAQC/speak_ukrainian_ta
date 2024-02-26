package com.academy.ui.pages.payment;

import com.academy.ui.pages.BasePageWithoutHeaderAndFooter;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class PaymentPage extends BasePageWithoutHeaderAndFooter {
    public PaymentPage(WebDriver driver){
        super(driver);
    }
    protected final String INITIATIVE_HEADER_PATH ="//div[contains(@class,\"header\")]";
    protected final String INITIATIVE_DESCRIPTION_PATH ="//div[contains(@class,\"description\")]";
    @FindBy(xpath = INITIATIVE_HEADER_PATH +"//div[contains(@class,\"large\")]")
    protected WebElement largeLogoImage;
    @FindBy(xpath = INITIATIVE_HEADER_PATH + "//div[contains(@class,\"title\")]")
    protected WebElement initiativeTitle;
    @FindBy(xpath = INITIATIVE_DESCRIPTION_PATH+"//p[not (contains(text(),\"http\"))]")
    protected WebElement initiativeDescription;
    @FindBy(xpath = INITIATIVE_DESCRIPTION_PATH+"//p[contains(text(),\"http\")]")
    protected WebElement initiativeVideoLinkText;
}
