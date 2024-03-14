package com.academy.ui.components;

import com.academy.ui.pages.ClubsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClubsPaginationComponent extends SwitchPaginationComponent{
    private final String FIRST_CLUB_XPATH="//div[contains(@class,'content-clubs-list')]" +
            "//div[contains(@class, 'ant-card-body')]" +
            "//div[@class='title']" +
            "//div[@class='name']";

    public ClubsPaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }

    @Step("Click on the left arrow of the pagination on the Clubs page")
    @Override
    public ClubsPage clickPrevious() {
        clickItem(previousItem);
        return new ClubsPage(driver);
    }

    @Step("Click on the right arrow of the pagination on the Clubs page")
    @Override
    public ClubsPage clickNext() {
        clickItem(nextItem);
        return new ClubsPage(driver);
    }

    @Step("Click on the page number {pageNum} of the pagination on the Clubs page")
    @Override
    public ClubsPage clickPagItemByNum(String pageNum) {
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            clickItem(pagItem);
        }
        return new ClubsPage(driver);
    }

    @Step("Click on the item of the pagination on the Clubs page")
    private void clickItem(WebElement item) {
        WebElement firstClubName = driver.findElement(By.xpath(FIRST_CLUB_XPATH));
        String text = firstClubName.getText();
        item.click();
        waitTillClubsChange(text);
    }

    @Step("Wait till the club cards change after switching the pagination on the Clubs page")
    private void waitTillClubsChange(String oldText){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) driver -> {
                WebElement newFirstClubName =  driver.findElement(By.xpath(FIRST_CLUB_XPATH));
                String newText = newFirstClubName.getText();
                return !newText.equals(oldText);
        });
    }

}
