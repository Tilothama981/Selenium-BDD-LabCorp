package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Labcorp1 {

    public static void main(String[] args) {

        // ===== Setup ChromeDriver =====
       //// WebDriverManager.chromedriver().setup();
       // ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ===== 1. Open LabCorp Website =====
        driver.get("https://www.labcorp.com/");

        // ===== 2. Handle Cookie Popup if it appears =====
        try {
            WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (acceptBtn.isDisplayed()) {
                acceptBtn.click();
                System.out.println("✅ Accepted cookies");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Cookie banner not displayed or already handled");
        }

        // ===== 3. Click Careers Link =====
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        careersLink.click();

        // ===== 4. Search for Job Title =====
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search job title or location']")));
        searchBox.clear();
        String jobToSearch = "Senior QA Analyst";
        searchBox.sendKeys(jobToSearch);
        searchBox.sendKeys(Keys.ENTER);

        // ===== 5. Verify Job Title =====
        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@data-ph-id='ph-page-element-page11-Zk12Zp']")));
        String titleText = jobTitle.getText();
        System.out.println("Job Title found: " + titleText);

        // Click the job title to open details
        jobTitle.click();

        // ===== 6. Verify Job Location =====
        WebElement jobLocationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'job-location')]")));
        String jobLocation = jobLocationElement.getText().trim();
        System.out.println("✅ Job Location: " + jobLocation);

        // ===== 7. Verify Minimum Requirements =====
        WebElement minReqElement = driver.findElement(By.xpath("//span[text()='Minimum Requirements:']"));
        System.out.println("Found text: " + minReqElement.getText());

        WebElement reqElement = driver.findElement(
                By.xpath("//li/p[contains(text(),'Must possess at least a bachelor’s degree')]"));
        String actualText = reqElement.getText();
        String expectedText = "Must possess at least a bachelor’s degree or its equivalent in Software Engineering, Computer Science, Mechatronic Engineering, or a related field and at least five years of progressive experience in a Senior QA Analyst, Software Development Engineer In test or related role.";
        System.out.println("Requirement Text: " + actualText);
        Assert.assertEquals(actualText, expectedText, "❌ Bachelor degree requirement mismatch!");

        // ===== 8. Verify First Sentence of Third Paragraph =====
        WebElement sentenceElement = driver.findElement(
                By.xpath("//li/p[text()='Work in an agile environment to ensure the timely delivery of high-quality software.']"));
        String actualText11 = sentenceElement.getText();
        String expectedText11 = "Work in an agile environment to ensure the timely delivery of high-quality software.";
        Assert.assertEquals(actualText11, expectedText11, "❌ First sentence of third paragraph mismatch!");
        System.out.println("✅ Verified sentence: " + actualText11);

        // ===== 9. Verify Second Bullet Under Management Support =====
        WebElement secondBullet = driver.findElement(By.xpath("(//ul[@id='requirements']/li/p)[2]"));
        String actualText14 = secondBullet.getText();
        String expectedText14 = "Must possess at least 5 years of hands-on functional testing experience for Web, API and/ Mobile.";
        Assert.assertEquals(actualText14, expectedText14, "❌ Second bullet text does not match!");
        System.out.println("✅ Verified second bullet: " + actualText14);

        // ===== 10. Verify Third Requirement =====
        WebElement thirdRequirement = driver.findElement(By.xpath("(//ul[@id='requirements']/li/p)[3]"));
        String actualText3p = thirdRequirement.getText();
        String expectedText3p = "Must possess at least 5 years of experience working on an agile team.";
        Assert.assertEquals(actualText3p, expectedText3p, "❌ Third requirement text does not match!");
        System.out.println("✅ Verified third requirement: " + actualText3p);

        // ===== 11. Click Apply Now =====
        WebElement applyPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        String applyTitleText = applyPageTitle.getText();
        System.out.println("✅ Apply Page Title: " + applyTitleText);

// Example assertion: check that page title contains "Senior QA Analyst"
        Assert.assertTrue(applyTitleText.contains("Senior QA Analyst"),
                "❌ Apply page title does not contain expected job title!");

// ===== Optional: close Apply tab and switch back =====
        driver.close();
        String originalWindow = new String();
        driver.switchTo().window(originalWindow);

        // ===== 12. Return to Job Search =====
        driver.navigate().back();

        // ===== 13. Close Browser =====
        driver.quit();
    }
}
