package com.demoqa.listeners;

import com.demoqa.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("▶ Starting Test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ Test Passed: {}", result.getName());
        captureAndAttachScreenshot(result, "Success Screenshot");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ Test Failed: {}", result.getName(), result.getThrowable());
        captureAndAttachScreenshot(result, "Failure Screenshot");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("🚀 Test Suite Started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("🏁 Test Suite Finished: {}", context.getName());
    }

    private void captureAndAttachScreenshot(ITestResult result, String attachmentName) {
        Object currentClass = result.getInstance();
        WebDriver driver = null;

        try {
            // Access driver field from BaseTest safely
            var baseClass = result.getTestClass()
                    .getRealClass()
                    .getSuperclass()
                    .getDeclaredField("driver");

            baseClass.setAccessible(true); // <-- 🔑 This line fixes IllegalAccessException
            driver = (WebDriver) baseClass.get(currentClass);
        } catch (Exception e) {
            logger.error("Could not retrieve WebDriver from test class.", e);
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                logger.info("🖼 Screenshot saved at: {}", screenshotPath);
                try (InputStream is = new FileInputStream(screenshotPath)) {
                    Allure.addAttachment(attachmentName, is);
                } catch (IOException e) {
                    logger.error("Failed to attach screenshot to Allure report.", e);
                }
            }
        }
    }

}
