package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.TextBoxPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("DemoQA Tests")
@Feature("Text Box Module")
@Story("Submit form and verify output values")
@Severity(SeverityLevel.CRITICAL)
public class TextBoxDataProviderWithLoggerTest extends BaseTest {

    // Create a Logger instance
    private static final Logger logger = LogManager.getLogger(TextBoxDataProviderTest.class);

    @DataProvider(name = "textBoxData")
    public Object[][] textBoxProviderData() {
        return new Object[][] {
                {"Pramila", "test@test.com", "address 101 street 9 , ggn ,HAryana Delhi", "45 Park street, Mumbai"},
                {"Ravi", "ravi@mail.com", "MG Road, Pune", "DLF Phase 2, Gurgaon"}
        };
    }

    @Test(dataProvider = "textBoxData", description = "Submit text box form and verify outputs")
    @Story("Submit form and verify result fields")
    @Severity(SeverityLevel.CRITICAL)
    public void testTextBoxFormSubmission(String name, String mail, String currAddr, String permAddr) {

        // Log the start of the test with input data
        logger.info("Starting test for Name: {}, Email: {}, Current Address: {}, Permanent Address: {}",
                name, mail, currAddr, permAddr);

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        // Step 1: Verify page title
        stepVerifyTitle();

        // Step 2: Fill the form
        stepFillForm(textBoxPage, name, mail, currAddr, permAddr);

        // Step 3: Submit the form
        stepSubmitForm(textBoxPage);

        // Step 4: Get the output values and log them
        String outName = textBoxPage.getOutputName().trim();
        String outEmail = textBoxPage.getOutputEmail().trim();
        String outCurr = textBoxPage.getOutputCurrentAddress().trim();
        String outPerm = textBoxPage.getOutputPermanentAddress().trim();

        logger.info("Output Name: {}", outName);
        logger.info("Output Email: {}", outEmail);
        logger.info("Output Current Address: {}", outCurr);
        logger.info("Output Permanent Address: {}", outPerm);

        logger.info("Form submitted, verifying output...");
        // Assertions with logging
        try {
            Assert.assertEquals(outName, "Name:" + name, "Name output mismatch");
            logger.info("Name assertion passed.");

            Assert.assertEquals(outEmail, "Email:" + mail, "Email output mismatch");
            logger.info("Email assertion passed.");

            Assert.assertEquals(outCurr, "Current Address :" + currAddr, "Current Address mismatch");
            logger.info("Current Address assertion passed.");

            Assert.assertEquals(outPerm, "Permananet Address :" + permAddr, "Permanent Address mismatch");
            logger.info("Permanent Address assertion passed.");

            // Log success
            logger.info("Test Passed Successfully!");
            logger.info("✅ Test passed for: " + name);


        } catch (AssertionError e) {
            // Log failure
            logger.error("Test failed due to assertion error: ", e);
            throw e;  // Re-throw to mark the test as failed
        }
    }

    @Step("Verify page title is DEMOQA")
    private void stepVerifyTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "DEMOQA", "Page title mismatch");
        logger.info("Verified page title: {}", actualTitle);
    }

    @Step("Fill form with Name:{1}, Email:{2}")
    private void stepFillForm(TextBoxPage page, String name, String mail, String currAddr, String permAddr) {
        page.fillForm(name, mail, currAddr, permAddr);
        logger.info("Filled form with Name: {}, Email: {}, Current Address: {}, Permanent Address: {}", name, mail, currAddr, permAddr);
    }

    @Step("Click Submit")
    private void stepSubmitForm(TextBoxPage page) {
        page.clickSubmit();
        logger.info("Clicked Submit button.");
    }
}
