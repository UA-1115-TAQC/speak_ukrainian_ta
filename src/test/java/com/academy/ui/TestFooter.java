package com.academy.ui;


import com.academy.ui.components.FooterComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFooter extends BaseTestRunner {


    @Test(description = "TUA-946")
    public void testInstagramIconClickOpensCorrespondingPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        FooterComponent footer = homePage.getFooter();
        footer.clickOnInstagramLink();

        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
            }
        }
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Єдині"), "Instagram page 'Єдині' didn't open in a new tab");
    }

}
