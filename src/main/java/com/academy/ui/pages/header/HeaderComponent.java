package com.academy.ui.pages.header;

import com.academy.ui.pages.BaseComponent;
import com.academy.ui.pages.popups.AddClubPopUpComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HeaderComponent extends BaseComponent {
    protected WebElement profileMenuButton;
    protected WebElement groupButton;

    protected WebElement addClubButton;
    public AddClubPopUpComponent addClubPopUp;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getProfileMenuButton() {
        if (profileMenuButton == null) {
            profileMenuButton = rootElement.findElement(By.xpath(".//div[contains(@class, 'user-profile')]"));
        }
        return profileMenuButton;
    }

    public WebElement getGroupButton() {
        return rootElement.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[2]/ul/li[1]"));
    }

    public void clickMenu() {
        this.getProfileMenuButton().click();
    }

    public WebElement getAddClubButton() {
        if (addClubButton == null) {
            addClubButton = rootElement.findElement(By.xpath("//button[contains(@class,'add-club-button')]"));
        }
        return addClubButton;
    }

    public void addClubButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'avatarIfLogin')]")));
        getAddClubButton().click();
        addClubPopUp = getAddClubPopUp();
    }

    public AddClubPopUpComponent getAddClubPopUp() {
        if (addClubPopUp == null) {
            addClubPopUp = new AddClubPopUpComponent(driver);
        }
        return addClubPopUp;
    }
}