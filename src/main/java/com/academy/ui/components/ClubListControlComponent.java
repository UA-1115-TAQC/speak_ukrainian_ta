package com.academy.ui.components;

import com.academy.ui.pages.ClubsPage;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Getter
public class ClubListControlComponent extends BaseComponent{
    @FindBy(xpath = ".//span[contains(@class,'control-sort-label')]")
    protected WebElement sortLabel;

    @FindBy(xpath = ".//span[text()='за алфавітом']")
    protected WebElement sortByAlphabet;

    @FindBy(xpath = ".//span[text()='за рейтингом']")
    protected WebElement sortByRating;

    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-up')]")
    protected WebElement arrowUp;

    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-down')]")
    protected WebElement arrowDown;

    @FindBy(xpath = ".//label[contains(@class, 'ant-radio-button-wrapper')][1]")
    protected WebElement wrapperList;

    @FindBy(xpath = ".//label[contains(@class, 'ant-radio-button-wrapper')][2]")
    protected WebElement wrapperBlock;

    @Getter(AccessLevel.NONE)private int timeToWait = 5000000;

    public ClubListControlComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ClubsPage clickSortByAlphabet(){
        getSortByAlphabet().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeToWait));
        return new ClubsPage(driver);
    }

    public ClubsPage clickSortByRating(){
        getSortByRating().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeToWait));
        return new ClubsPage(driver);
    }

    public ClubsPage clickArrowUp(){
        getArrowUp().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeToWait));
        return new ClubsPage(driver);
    }

    public ClubsPage clickArrowDown(){
        getArrowDown().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeToWait));
        return new ClubsPage(driver);
    }

    public ClubsPage clickWrapperList(){
        getWrapperList().click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickWrapperBlock(){
        getWrapperBlock().click();
        return new ClubsPage(driver);
    }

}
