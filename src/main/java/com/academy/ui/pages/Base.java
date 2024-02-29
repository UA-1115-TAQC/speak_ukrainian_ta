package com.academy.ui.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Base {
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void sleep(long millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {

        }
    }

    public boolean isElementPresent(WebElement element){
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
