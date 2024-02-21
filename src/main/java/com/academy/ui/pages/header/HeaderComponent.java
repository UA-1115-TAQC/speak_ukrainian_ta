package com.academy.ui.pages.header;


import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.AllNewsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent extends BaseComponent {
    protected WebElement profileMenuButton;
    protected WebElement groupButton;
    @FindBy(xpath = "//a[@href='/news']")
    protected WebElement newsButton;

    @FindBy(xpath = "//span[contains(@class,'avatarIfLogin')]")
    private WebElement isLoggedIn;

    @FindBy(xpath = "//button[contains(@class,'add-club-button')]")
    protected WebElement addClubButton;


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

    public AllNewsPage newsButtonClick() {
        newsButton.click();
        return new AllNewsPage(driver);
    }

    public AddClubPopUpComponent addClubButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(e -> isLoggedIn.isDisplayed());
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }


}
