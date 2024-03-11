package com.academy.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Objects;

public class BasePageWithoutHeaderAndFooter extends Base {
    public BasePageWithoutHeaderAndFooter(WebDriver driver){
        super(driver);
    }
    protected String currentTabHandle;
    protected ArrayList<String> tabHandles;
    @Step("Get a current tab handle")
    public String getCurrentTabHandle(){
        return currentTabHandle = driver.getWindowHandle();
    }
    @Step("Get all tab handles")
    public ArrayList<String> getTabHandles(){
        return tabHandles = new ArrayList<String> (driver.getWindowHandles());
    }
    @Step("Check that a page is opened in a new tab")
    public boolean checkThatAPageIsOpenedInANewTab(String previousHandle, String newHandle){
        return (Objects.equals(previousHandle, newHandle)) && (getTabHandles().size() == 2);
    }
    @Step("Switch to a new tab by its index")
    public void switchToANewTabByItsIndex(int index) {
        if (index >= 0 && index < getTabHandles().size()) {
            driver.switchTo().window(getTabHandles().get(index));
        } else {
            throw new Error("The index must be in the range from 0 to " + (getTabHandles().size() - 1) + ", inclusive");
        }
    }
}
