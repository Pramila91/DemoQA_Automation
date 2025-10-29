package com.demoqa.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // ✅ Run in headless mode (for CI)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create log and screenshot directories if not exist
        new File("logs").mkdirs();
        new File("screenshots").mkdirs();
        //driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));

        // Remove ads and scroll a bit
        removeAds();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
    }

    public void removeAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelectorAll('iframe, #fixedban, .advertisement').forEach(e => e.remove());");
        } catch (Exception e) {
            System.out.println("No ads or banners found.");
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
           // driver.quit();
        }
    }
}
