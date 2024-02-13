package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends BasePage{
    private final String SEARCH_CLUB_HEADER_ROOT_XPATH =
            "//div[contains(@class, 'lower-header-box')]";
    private final String CLUB_CARD_LIST_XPATH =
            "//div[contains(@class,'content-clubs-list')]";
    private final String SWITCH_PAGINATION_ROOT_XPATH =
            "//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]";
    private AdvancedSearchClubHeaderComponent advancedSearchClubHeader;

    public ClubsPage(WebDriver driver) {
        super(driver);
        WebElement clubSearchHeaderRootElement = this.driver.findElement(By.xpath(SEARCH_CLUB_HEADER_ROOT_XPATH));
        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, clubSearchHeaderRootElement);
    }

    public AdvancedSearchClubHeaderComponent getAdvancedSearchClubHeader(){
        return advancedSearchClubHeader;
    }

}
