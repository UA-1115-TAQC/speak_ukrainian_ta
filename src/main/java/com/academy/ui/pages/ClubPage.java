package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClubPage extends BasePage {

    protected WebElement signUpForTheGroupButton;

    protected WebElement writeToTheManagerButton;

    public ClubPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSignUpForTheGroupButton() {
        if (signUpForTheGroupButton == null) {
            signUpForTheGroupButton = driver.findElement(By.xpath("//span[text()='Записатись на гурток'])"));
        }
        return signUpForTheGroupButton;
    }

    public WebElement getWriteToTheManagerButton() {
        if (writeToTheManagerButton == null) {
            writeToTheManagerButton = driver.findElement(By.xpath("//span[text()='Написати менеджеру']"));
        }
        return writeToTheManagerButton;
    }

    public void clickOnSignUpForTheGroupButton(){
        this.signUpForTheGroupButton.click();
    }

    public void clickOnWriteToTheManagerButton(){
        this.writeToTheManagerButton.click();
    }
}


