package org.example;

//ipackage com.example.labcorptest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LaunchBrowser {

    public static void main(String[] args) {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Open LabCorp website
        driver.get("https://www.labcorp.com/");

        // ✅ Handle cookie popup if it appears
        // Handle cookie banner
        try {
            // Accept All Cookies
            WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (acceptBtn.isDisplayed()) {
                acceptBtn.click();
                System.out.println("✅ Accepted cookies");
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not displayed or already handled");
        }


        // 2. Click Careers link
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        careersLink.click();
//Search job title or location
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search job title or location']")));
        searchBox.clear();
        String jobToSearch = "Senior QA Analyst "; // job title to type
        searchBox.sendKeys(jobToSearch);
        searchBox.sendKeys(Keys.ENTER);
        //verify Senior QA Analyst displaying
        // Locate the span element
        WebElement jobTitle = driver.findElement(By.xpath("//span[@data-ph-id='ph-page-element-page11-Zk12Zp']"));

// Get its text
        String titleText = jobTitle.getText();

// Print a message with the text
        System.out.println("Job Title found: " + titleText);


        // Locate the span element by its attribute and click
        driver.findElement(By.xpath("//span[@data-ph-id='ph-page-element-page11-Zk12Zp']")).click();

// 2️⃣ Confirm Job Location
        WebElement jobLocationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'job-location')]")
        ));
        String jobLocation = jobLocationElement.getText().trim();
        System.out.println("✅ Job Location: " + jobLocation);
//be text in the requirements
        WebElement minReqElement = driver.findElement(By.xpath("//span[text()='Minimum Requirements:']"));

        // Get the text
        String minReqText = minReqElement.getText();
        System.out.println("Found text: " + minReqText);
        WebElement reqElement = driver.findElement(By.xpath("//li/p[contains(text(),'Must possess at least a bachelor’s degree')]"));

        // Get its text
        String actualText = reqElement.getText();

        // Expected text
        String expectedText = "Must possess at least a bachelor’s degree or its equivalent in Software Engineering, Computer Science, Mechatronic Engineering, or a related field and at least five years of progressive experience in a Senior QA Analyst, Software Development Engineer In test or related role.";

        System.out.println("Requirement Text: " + actualText);

        // ============ 1. Confirm first sentence of third paragraph under Description/Introduction ============
        WebElement sentenceElement = driver.findElement(
                By.xpath("//li/p[text()='Work in an agile environment to ensure the timely delivery of high-quality software.']"));

        // Get the text
        String actualText11 = sentenceElement.getText();

        // Expected text
        String expectedText11 = "Work in an agile environment to ensure the timely delivery of high-quality software.";

        // Assertion
        Assert.assertEquals(actualText11, expectedText11, "❌ Text does not match the expected sentence!");

        System.out.println("✅ Verified sentence: " + actualText11);

        // ============ 2. Confirm second bullet point under Management Support ============
        WebElement secondBullet = driver.findElement(
                By.xpath("(//ul[@id='requirements']/li/p)[2]")   );


        // Get the text
        String actualText14= secondBullet.getText();

        // Expected text
        String expectedText14 = "Must possess at least 5 years of hands-on functional testing experience for Web, API and/ Mobile.";

        // Assertion
        Assert.assertEquals(actualText14, expectedText14, "❌ Second bullet text does not match!");

        System.out.println("✅ Verified second bullet: " + actualText14);
        // ============ 3. Confirm third requirement ============
        WebElement thirdRequirement = driver.findElement(
                By.xpath("(//ul[@id='requirements']/li/p)[3]") // adjust XPath as per actual HTML
        );

        // Get the text
        String actualText3p = thirdRequirement.getText();

        // Expected text
        String expectedText3p = "Must possess at least 5 years of experience working on an agile team.";

        // Assertion
        Assert.assertEquals(actualText3p, expectedText3p, "❌ Third requirement text does not match!");

        System.out.println("✅ Verified third requirement: " + actualText3p);


        System.out.println("🎉 All verifications passed successfully!");

        // 6. Click Apply Now
        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Apply') or contains(@class,'apply')]")));
        applyButton.click();

        // Confirm details on Apply page
        WebElement applyTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        System.out.println("✅ Apply Page Title: " + applyTitle.getText());

        // 7. Return to Job Search
        driver.navigate().back();

        // Close browser
        driver.quit();
    }
}
