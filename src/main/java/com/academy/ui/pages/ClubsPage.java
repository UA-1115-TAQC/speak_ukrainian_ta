package com.academy.ui.pages;

import com.academy.ui.components.AdvancedSearchComponent.AdvancedSearchClubHeaderComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClubsPage extends BasePage {
    private final String SEARCH_CLUB_HEADER_ROOT_XPATH = "//div[contains(@class, 'lower-header-box')]";
    private final String SWITCH_PAGINATION_ROOT_XPATH = "//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]";
    private final String CLUB_CARD_LIST_XPATH = "//div[contains(@class,'content-clubs-list')]/child::div";

    private final AdvancedSearchClubHeaderComponent advancedSearchClubHeader;
//    private final SwitchPaginationComponent switchPagination;
    private final List<ClubCardComponent> clubCards;

    public ClubsPage(WebDriver driver) {
        super(driver);
        WebElement clubSearchHeaderRootElement = this.driver.findElement(By.xpath(SEARCH_CLUB_HEADER_ROOT_XPATH));
        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, clubSearchHeaderRootElement);

//        WebElement switchPaginationRootElement = this.driver.findElement(By.xpath(SWITCH_PAGINATION_ROOT_XPATH));
//        switchPagination = new SwitchPaginationComponent(this.driver, switchPaginationRootElement);

        clubCards = createClubComponents();
    }

    private List<ClubCardComponent> createClubComponents() {
        List<ClubCardComponent> clubs = new ArrayList<>();
        List<WebElement> clubDivs = driver.findElements(By.xpath(CLUB_CARD_LIST_XPATH));
        for (WebElement element : clubDivs) {
            clubs.add(new ClubCardComponent(driver, element));
        }
        return clubs;
    }
    public ClubsPage waitUntilClubsPageIsLoaded(int seconds){
        if(seconds > 0 ) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.urlContains("clubs"));
            wait.until(ExpectedConditions.visibilityOf(getAdvancedSearchClubHeader().getShowOnMapButton()));
            return this;
        }
        throw new Error("The number of seconds must be greater than 0 and an integer number");
    }
}
