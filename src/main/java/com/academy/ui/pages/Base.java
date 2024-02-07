package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;

public abstract class Base {
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
