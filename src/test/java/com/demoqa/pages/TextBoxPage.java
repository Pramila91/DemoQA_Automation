package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TextBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By fullName = By.id("userName");
    private By email = By.id("userEmail");
    private By currentAddress = By.id("currentAddress");
    private By permanentAddress = By.id("permanentAddress");
    private By submitButton = By.id("submit");

    // Output locators
    private By outputName = By.id("name");
    private By outputEmail = By.id("email");
    private By outputCurrentAddress = By.xpath("//p[@id='currentAddress']");
    private By outputPermanentAddress = By.xpath("//p[@id='permanentAddress']");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Fill the text box form")
    public void fillForm(String name, String mail, String currAddr, String permAddr) {
        driver.findElement(fullName).clear();
        driver.findElement(fullName).sendKeys(name);

        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(mail);

        driver.findElement(currentAddress).clear();
        driver.findElement(currentAddress).sendKeys(currAddr);

        driver.findElement(permanentAddress).clear();
        driver.findElement(permanentAddress).sendKeys(permAddr);
    }


    @Step("Click submit button")
    public void clickSubmit() {
        WebElement submitBtn = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    // Getters for output
    public String getOutputName() {
        return driver.findElement(outputName).getText();
    }

    public String getOutputEmail() {
        return driver.findElement(outputEmail).getText();
    }

    public String getOutputCurrentAddress() {
        return driver.findElement(outputCurrentAddress).getText();
    }

    public String getOutputPermanentAddress() {
        return driver.findElement(outputPermanentAddress).getText();
    }
}
