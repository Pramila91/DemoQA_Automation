package com.demoqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = "screenshots";

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Ensure driver is not null
        if (driver == null) {
            logger.warn("⚠️ WebDriver is null, skipping screenshot.");
            return null;
        }

        // Create directory if it doesn’t exist
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (dirCreated) {
                logger.info("📁 Created screenshot directory: {}", directory.getAbsolutePath());
            } else {
                logger.error("❌ Failed to create screenshot directory: {}", directory.getAbsolutePath());
            }
        }

        // Generate timestamp and file path
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOT_DIR + File.separator + screenshotName + "_" + timestamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileHandler.copy(srcFile, destFile);

            logger.info("🖼 Screenshot captured: {}", destFile.getAbsolutePath());
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            logger.error("❌ Failed to capture screenshot: {}", e.getMessage(), e);
            return null;
        }
    }
}
