package com.academy.ui.pages;

import com.academy.ui.components.AdvancedSearchComponent.AdvancedSearchClubHeaderComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClubsPage extends BasePage {
    private final String SEARCH_CLUB_HEADER_ROOT_XPATH =
            "//div[contains(@class, 'lower-header-box')]";
    private final String SWITCH_PAGINATION_ROOT_XPATH =
            "//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]";
    private final AdvancedSearchClubHeaderComponent advancedSearchClubHeader;
    private final SwitchPaginationComponent switchPagination;

    public ClubsPage(WebDriver driver) {
        super(driver);
        WebElement clubSearchHeaderRootElement = this.driver.findElement(By.xpath(SEARCH_CLUB_HEADER_ROOT_XPATH));
        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, clubSearchHeaderRootElement);

        WebElement switchPaginationRootElement = this.driver.findElement(By.xpath(SWITCH_PAGINATION_ROOT_XPATH));
        switchPagination = new SwitchPaginationComponent(this.driver, switchPaginationRootElement);
    }

    public AdvancedSearchClubHeaderComponent getAdvancedSearchClubHeader() {
        return advancedSearchClubHeader;
    }

    public SwitchPaginationComponent getSwitchPagination() {
        return switchPagination;
    }

}
