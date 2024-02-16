package com.academy.ui.pages;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.pages.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public abstract class BasePage extends Base {
    public HeaderComponent header;
    protected String currentTabHandle;
    protected ArrayList<String> tabHandles;
    public FooterComponent footer;
    public BasePage(WebDriver driver) {
        super(driver);
        this.header = getHeader();
        this.footer = getFooter();
    }

    public HeaderComponent getHeader() {
        if (header == null) {
            WebElement node = driver.findElement(By.xpath(".//header"));
            header = new HeaderComponent(driver, node);
        }
        return header;
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public String getCurrentTabHandle(){
        return currentTabHandle = driver.getWindowHandle();
    }
    public ArrayList<String> getTabHandles(){
        return tabHandles = new ArrayList<String> (driver.getWindowHandles());
    }
    public boolean checkThatAPageIsOpenedInANewTab(String previousHandle, String newHandle){
        return (Objects.equals(previousHandle, newHandle)) && (getTabHandles().size() == 2);
    }
    public void switchToANewTabByItsIndex(int index) {
        if (index >= 0 && index < getTabHandles().size()) {
            driver.switchTo().window(getTabHandles().get(index));
        }
        throw new Error("The index must be in the range from 0 to " + (getTabHandles().size() - 1) + ", inclusive");
    }
    public FooterComponent getFooter(){
        if (footer == null) {
            WebElement node = driver.findElement(By.xpath(".//footer"));
            footer = new FooterComponent(driver, node);
        }
        return footer;
    }
}
