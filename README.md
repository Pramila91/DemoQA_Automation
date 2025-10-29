# 🧪 DemoQA Automation Framework (Selenium + TestNG + Log4j2)

## 🚀 Overview
This project is a **Java Selenium automation framework** built using **TestNG**, following the **Page Object Model (POM)** design pattern.  
It features **data-driven testing**, **Log4j2 logging**, and **screenshot capture on both success and failure** — all integrated with a TestNG listener.

> 🎯 Designed for DemoQA practice site automation with clean, reusable, and modular structure.

---

## 🧱 Tech Stack

| Component       | Tool / Framework |
|-----------------|------------------|
| Language        | Java 17+ |
| Test Runner     | TestNG |
| Build Tool      | Maven |
| Logging         | Log4j2 |
| Reporting       | Allure (optional) |
| Design Pattern  | Page Object Model (POM) |
| Screenshot Capture | Selenium AShot / FileHandler |

---


## 🧪 Test Example

```java
@Test(dataProvider = "textBoxData", description = "Submit text box form and verify outputs")
@Story("Submit form and verify result fields")
@Severity(SeverityLevel.CRITICAL)
public void testTextBoxFormSubmission(String name, String mail, String currAddr, String permAddr) {
    logger.info("Starting test for Name: {}", name);
    TextBoxPage textBoxPage = new TextBoxPage(driver);

    textBoxPage.fillForm(name, mail, currAddr, permAddr);
    textBoxPage.clickSubmit();

    Assert.assertEquals(textBoxPage.getOutputName(), "Name:" + name);
    logger.info("✅ Test passed for: {}", name);
}


