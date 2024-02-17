package com.academy.ui.pages.header;


import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.AllNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class HeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class, 'user-profile')]")
    protected WebElement profileMenuButton;
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/div[2]/ul/li[1]")
    protected WebElement groupButton;
    protected WebElement newsButton;
    public AddClubPopUpComponent addClubPopUp;

    @FindBy(xpath = "//span[contains(@class,'avatarIfLogin')]")
    private WebElement isLoggedIn;

    @FindBy(xpath = "//button[contains(@class,'add-club-button')]")
    protected WebElement addClubButton;

    @FindBy(xpath = "//li[contains(@data-menu-id, 'login')]")
    protected WebElement loginDropDownMenu;
    public LoginPopupComponent loginPopupComponent;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickMenu() {
        profileMenuButton.click();
    }

    public WebElement getNewsButton() {
        if (newsButton == null) {
            newsButton = rootElement.findElement(By.xpath("//a[@href='/news']"));
        }
        return newsButton;
    }

    public AllNewsPage toAllNewsPage() {
        getNewsButton().click();
        return new AllNewsPage(driver);
    }

    public void addClubButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(e -> isLoggedIn.isDisplayed());
        addClubButton.click();
        addClubPopUp = new AddClubPopUpComponent(driver);
    }

    public void clickLoginPopUpMenu() {
        clickMenu();
        loginDropDownMenu.click();
        loginPopupComponent = new LoginPopupComponent(driver,
                rootElement.findElement(By.xpath("//descendant::div[contains(@class, 'modal-login')][1]")));
    }
}