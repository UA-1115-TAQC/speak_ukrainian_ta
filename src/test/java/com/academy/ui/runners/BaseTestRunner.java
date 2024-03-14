package com.academy.ui.runners;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.utils.ConfigProperties;
import com.academy.ui.runners.utils.TestNgListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.time.Duration;


@Listeners(TestNgListener.class)
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
    public void setUp(ITestContext context) {
        ChromeOptions options = new ChromeOptions();
//        if you want to use head mode uncomment one line below
//        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitDuration));
        context.setAttribute("webDriver", driver);
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
