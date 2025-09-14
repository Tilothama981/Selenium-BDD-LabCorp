package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Labcorp2 {

    public static void main(String[] args) {

        // === 1. Setup ChromeDriver ===
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        // === 2. Open LabCorp Homepage ===
        driver.get("https://www.labcorp.com/");

        // === 3. Handle Cookie Popup if it appears ===
//        try {
//            WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
//            if (acceptBtn.isDisplayed()) {
//                acceptBtn.click();
//                System.out.println("✅ Accepted cookies");
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("⚠️ Cookie banner not displayed or already handled");
//        }
        // Click "Accept All Cookies"
        WebElement acceptCookiesBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptCookiesBtn.click();

        // Continue with further actions
        System.out.println("Cookies accepted!");

        // === 4. Click Careers ===
        driver.findElement(By.linkText("Careers")).click();

        // === 5. Enter Job Title in Search Box ===
        WebElement searchBox = driver.findElement(By.id("typehead"));
        searchBox.sendKeys("Sr QA Analyst");

        // Click search
        WebElement searchButton = driver.findElement(By.id("ph-search-backdrop"));
        searchButton.click();

        // === 6. Select Job Title from Results ===
        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span[data-ph-id='ph-page-element-page11-Zk12Zp']")));
        String jobText = jobTitle.getText();
        System.out.println("🔍 Job Title Found: " + jobText);
        Assert.assertTrue(jobText.contains("Senior QA Analyst"));
        jobTitle.click();

        // === 7. Capture Job Details ===
        WebElement jobTitleElement = driver.findElement(By.className("job-title"));
        System.out.println("Job Title: " + jobTitleElement.getText());

        WebElement jobLocationElement = driver.findElement(By.xpath("//span[contains(@class,'job-location')]"));
        System.out.println("Job Location: " + jobLocationElement.getText());

        WebElement jobIdElement = driver.findElement(By.className("jobId"));
        System.out.println("Job ID: " + jobIdElement.getText());

        // === 8. Verify Minimum Requirements Header ===
        WebElement minReq = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Minimum Requirements:']")));
        System.out.println("📄 Section Header: " + minReq.getText());

        // === 9. First Bullet Requirement ===
        WebElement firstReq = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li/p[contains(text(),'Must possess at least a bachelor')]")));
        String reqText = firstReq.getText();
        Assert.assertTrue(reqText.toLowerCase().contains("bachelor"),
                "❌ Requirement does not mention bachelor degree. Actual: " + reqText);
        System.out.println("✅ First requirement verified: " + reqText);

        // === 10. Third Paragraph (collaboration) ===
        WebElement thirdParagraph = driver.findElement(By.xpath("//p[contains(text(),'Collaborate with product team')]"));
        String paraText = thirdParagraph.getText();
        Assert.assertTrue(paraText.toLowerCase().contains("collaborate with product team"),
                "❌ Collaboration text mismatch. Actual: " + paraText);
        System.out.println("✅ Verified collaboration paragraph: " + paraText);


           // === 12.Confirm first suggested automation tool to be familiar with contains “Selenium”
        try {
            // Locate the first <li> or <p> that contains 'Selenium'
            WebElement firstTool = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//li[contains(text(),'Selenium')] | //p[contains(text(),'Selenium')])[1]")
            ));

            String toolText = firstTool.getText();
            Assert.assertTrue(toolText.contains("Selenium"),
                    "❌ First suggested tool does not mention Selenium. Actual: " + toolText);

            System.out.println("✅ Verified first suggested automation tool contains Selenium: " + toolText);

        } catch (Exception e) {
            System.out.println("❌ Failed to verify first suggested automation tool with Selenium.");
        }



        // === 13. Apply Button (click first visible) ===
        try {
            List<WebElement> applyButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[@data-ph-at-id='apply-link']")));
            boolean clicked = false;
            for (WebElement btn : applyButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    clicked = true;
                    System.out.println("✅ Clicked Apply button");
                    break;
                }
            }
            Assert.assertTrue(clicked, "❌ No visible Apply button found.");
        } catch (Exception e) {
            System.out.println("❌ Error while clicking Apply button.");
        }

        // === 14. Switch to Workday Application Page ===
        wait.until(ExpectedConditions.urlContains("myworkdayjobs.com"));
        try {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptBtn = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[data-automation-id='legalNoticeAcceptButton']")));
            acceptBtn.click();
            System.out.println("✅ Accepted Workday cookies");
        } catch (Exception e) {
            System.out.println("⚠️ Workday cookie banner not displayed or already handled");
        }

        // === 15. Verify Workday Page ===
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("myworkdayjobs.com"),
                "❌ Not on expected Workday application page. URL: " + currentUrl);
        System.out.println("✅ Verified Workday page URL: " + currentUrl);

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Start Your Application')]")));
        Assert.assertTrue(header.getText().contains("Start Your Application"));
        System.out.println("✅ Verified header: " + header.getText());

        WebElement jobTitle2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Senior QA Analyst')]")));
        Assert.assertTrue(jobTitle2.getText().contains("Senior QA Analyst"));
        System.out.println("✅ Verified job title on Workday: " + jobTitle2.getText());

        WebElement applyManuallyBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Apply Manually']")));
        Assert.assertTrue(applyManuallyBtn.isDisplayed(),
                "❌ 'Apply Manually' button not visible");
        System.out.println("✅ 'Apply Manually' button is visible");

        // (Optional) Click the Apply Manually button
        applyManuallyBtn.click();
        System.out.println("🖱️ Clicked 'Apply Manually'");

//        } finally {
//            driver.quit();
//            System.out.println("🚪 Browser closed");
        //
    }

}
