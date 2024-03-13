package com.academy.ui.runners.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {
    private static String screenshotPath = "src/test/resources/screenshots/";

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File( screenshotPath + screenshotName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("Exception while taking screenshot ", e);
        }
    }
}
