package com.academy.ui.runners;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.utils.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public abstract class BaseTestRunner {
    protected WebDriver driver;
    protected static ConfigProperties configProperties;
    private final long implicitlyWaitDuration = 10;

    protected HomePage homePage;

    @BeforeSuite(alwaysRun = true, description = "Precondition of setup WebDriver")
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        configProperties = new ConfigProperties();
    }

    @BeforeMethod(description = "Web browser configuration")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitDuration));
        driver.get(configProperties.getBaseUrl());
        homePage = new HomePage(driver);
    }

    @AfterMethod(description = "Post Condition of closing every associated window")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
