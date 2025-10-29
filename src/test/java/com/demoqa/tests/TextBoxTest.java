package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxTest extends BaseTest {

    @Test
    public void testTextBoxFormSubmission() {
        TextBoxPage textBoxPage = new TextBoxPage(driver);

        // ✅ Verify Page Title
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "DEMOQA", "❌ Page title mismatch!");

        // ✅ Input Data
        String name = "Pramila";
        String mail = "test@test.com";
        String currAddr = "ss street , 10234, ggn ,HAryana Delhi";
        String permAddr = "45 Park street, Mumbai";

        // Fill and Submit
        textBoxPage.fillForm(name, mail, currAddr, permAddr);
        textBoxPage.clickSubmit();

        // ✅ Assertions for each field
        Assert.assertEquals(textBoxPage.getOutputName(), "Name:" + name, "❌ Name output mismatch!");
        Assert.assertEquals(textBoxPage.getOutputEmail(), "Email:" + mail, "❌ Email output mismatch!");
        Assert.assertEquals(textBoxPage.getOutputCurrentAddress(), "Current Address :" + currAddr, "❌ Current Address mismatch!");
        Assert.assertEquals(textBoxPage.getOutputPermanentAddress(), "Permananet Address :" + permAddr, "❌ Permanent Address mismatch!");

        System.out.println("✅ All field verifications passed successfully!");
    }
}
