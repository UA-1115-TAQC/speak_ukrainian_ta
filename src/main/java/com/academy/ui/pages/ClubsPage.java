package com.academy.ui.pages;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.CenterCardComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import com.academy.ui.components.advancedSearchHeader.AdvancedSearchClubHeaderComponent;
import com.academy.ui.components.ClubListControlComponent;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClubsPage extends BasePage {
    @FindBy(xpath = "//aside[contains(@class,\"sider\")]")
    private WebElement searchSiderAsideNode;

    @FindBy(xpath = "//div[contains(@class, 'lower-header-box')]")
    private WebElement searchClubHeaderWebElement;

    @FindBy(xpath = "//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]")
    @Getter(AccessLevel.NONE)
    private WebElement switchPaginationWebElement;

    @FindBy(xpath = "//div[contains(@class, 'club-list-control')]")
    @Getter(AccessLevel.NONE)
    private WebElement listControlWebElement;

    @FindBy(xpath = "//div[contains(@class, '')]")
    @Getter(AccessLevel.NONE)
    private WebElement searchSiderWebElement;

    @FindBy(xpath = "//div[contains(@class,'content-clubs-list')]/child::div")
    @Getter(AccessLevel.NONE)
    private List<WebElement> clubCardsWebElement;

    @FindBy(xpath = "//div[contains(@class,'content-center-list')]/child::div")
    @Getter(AccessLevel.NONE)
    private List<WebElement> centerCardsWebElement;

    protected AdvancedSearchClubHeaderComponent advancedSearchClubHeader;
    protected SwitchPaginationComponent switchPagination;
    protected ClubListControlComponent listControl;
    protected AdvancedSearchSiderComponent searchSider;
    protected List<ClubCardComponent> clubCards;
    protected List<CenterCardComponent> centerCards;

    public ClubsPage(WebDriver driver) {
        super(driver);

        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, searchClubHeaderWebElement);
        switchPagination = new SwitchPaginationComponent(this.driver, switchPaginationWebElement);
        listControl = new ClubListControlComponent(this.driver, listControlWebElement);
        searchSider = new AdvancedSearchSiderComponent(this.driver, searchSiderWebElement);
        selectWhatCardsToShow();
    }

    private List<ClubCardComponent> createClubComponents() {
        List<ClubCardComponent> clubs = new ArrayList<>();
        sleep(500);
        if (!clubCardsWebElement.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(clubCardsWebElement));
            for (WebElement element : clubCardsWebElement) {
                clubs.add(new ClubCardComponent(driver, element));
            }
        }
        return clubs;
    }

    private List<CenterCardComponent> createCenterComponents() {
        List<CenterCardComponent> centers = new ArrayList<>();
        sleep(500);
        if (!centerCardsWebElement.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(centerCardsWebElement));
            for (WebElement element : centerCardsWebElement) {
                centers.add(new CenterCardComponent(driver, element));
            }
        }
        return centers;
    }
    @Step("Wait until the clubs page is loaded")
    public ClubsPage waitUntilClubsPageIsLoaded(int seconds) {
        if (seconds > 0) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.urlContains("clubs"));
            wait.until(ExpectedConditions.visibilityOf(getAdvancedSearchClubHeader().getShowOnMapButton()));
            return this;
        }
        throw new Error("The number of seconds must be greater than 0 and an integer number");
    }

    public ClubsPage setTextHeaderSearch(String input) {
        advancedSearchClubHeader.setTextSelectionSearchInputField(input);
        sleep(1000);
        clubCards = createClubComponents();
        return this;
    }


    public ClubsPage waitClubsPageWithSiderLoaded(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.urlContains("clubs"));
        wait.until(ExpectedConditions.visibilityOf(getSearchSider().getDirectionsCheckBox().get(0)));
        return this;
    }

    public boolean isClubsPageEmpty() {
        return clubCards.isEmpty();
    }

    private void selectWhatCardsToShow() {
        if (isElementPresent(searchSiderAsideNode)) {
            if (searchSider.getCheckedRadioButton().getAttribute("innerText").equals("Гурток")) {
                clubCards = createClubComponents();
            } else {
                centerCards = createCenterComponents();
            }
        } else {
            clubCards = createClubComponents();
        }
    }
}
