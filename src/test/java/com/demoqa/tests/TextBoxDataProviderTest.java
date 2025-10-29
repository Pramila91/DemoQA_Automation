package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.TextBoxPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("DemoQA Tests")
@Feature("Text Box")
public class TextBoxDataProviderTest extends BaseTest {

    @DataProvider(name = "textBoxData")
    public Object[][] textBoxProviderData() {
        return new Object[][] {
                {"Pramila", "test@test.com", "address 101 street 9 , ggn ,HAryana Delhi", "45 Park street, Mumbai"},
                {"Ravi", "ravi@mail.com", "MG Road, Pune", "DLF Phase 2, Gurgaon"}
        };
                // add more rows here for more test cases
        };


    @Test(dataProvider = "textBoxData", description = "Submit text box form and verify outputs")
    @Story("Submit form and verify result fields")
    @Severity(SeverityLevel.CRITICAL)
    public void testTextBoxFormSubmission(String name, String mail, String currAddr, String permAddr) {

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        stepVerifyTitle();
        stepFillForm(textBoxPage, name, mail, currAddr, permAddr);
        stepSubmitForm(textBoxPage);

        // Assertions (Allure will capture these steps)
        String outName = textBoxPage.getOutputName();
        String outEmail = textBoxPage.getOutputEmail();
        String outCurr = textBoxPage.getOutputCurrentAddress();
        String outPerm = textBoxPage.getOutputPermanentAddress();

        Assert.assertEquals(outName, "Name:" + name, "Name output mismatch");
        Assert.assertEquals(outEmail, "Email:" + mail, "Email output mismatch");
        Assert.assertEquals(outCurr, "Current Address :" + currAddr, "Current Address mismatch");
        Assert.assertEquals(outPerm, "Permananet Address :" + permAddr, "Permanent Address mismatch");
    }

    @Step("Verify page title is DEMOQA")
    private void stepVerifyTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "DEMOQA", "Page title mismatch");
    }

    @Step("Fill form with Name:{1}, Email:{2}")
    private void stepFillForm(TextBoxPage page, String name, String mail, String currAddr, String permAddr) {
        page.fillForm(name, mail, currAddr, permAddr);
    }

    @Step("Click Submit")
    private void stepSubmitForm(TextBoxPage page) {
        page.clickSubmit();
    }
}