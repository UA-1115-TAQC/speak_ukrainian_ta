package com.academy.ui.runners.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("webDriver");
        if (driver != null){
            saveScreenshot(driver);
        }
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        onTestFailure(result);
        ITestListener.super.onTestSkipped(result);
    }

    @Attachment(value = "Page screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}
