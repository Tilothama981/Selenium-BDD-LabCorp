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

public class Labcorp {

    public static void main(String[] args) {

        // === 1. Setup ChromeDriver ===
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // === 2. Open LabCorp Homepage ===
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

        // === 4. Click Careers (By.linkText) ===
        // Click Careers link by link text
        driver.findElement(By.linkText("Careers")).click();

        //WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        //careersLink.click();

        // === 5. Enter Job Title in Search Box (By.xpath) ===
        WebElement searchBox = driver.findElement(By.id("typehead"));
        searchBox.sendKeys("Sr QA Analyst");


        // Locate search button by id and click
        WebElement searchButton = driver.findElement(By.id("ph-search-backdrop"));
        searchButton.click();
        // === 6. Select Job Title (By.cssSelector) ===
        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span[data-ph-id='ph-page-element-page11-Zk12Zp']")));
        String jobText = jobTitle.getText();
        System.out.println("🔍 Job Title Found: " + jobText);
        Assert.assertTrue(jobText.contains("Senior QA Analyst"));
        jobTitle.click();

        WebElement jobTitleElement = driver.findElement(By.className("job-title"));
        String jobTitle1 = jobTitleElement.getText();
        System.out.println("Job Title: " + jobTitle1);
        WebElement jobLocationElement = driver.findElement(By.xpath("//span[contains(@class,'job-location')]"));
        String jobLocation = jobLocationElement.getText();
        System.out.println("Job Location: " + jobLocation);
        // Locate by class name
        WebElement jobIdElement = driver.findElement(By.className("jobId"));
        String jobId = jobIdElement.getText();
        System.out.println("Job ID: " + jobId);

        // === 8. Verify Minimum Requirements Header ===
        WebElement minReq = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Minimum Requirements:']")));
        System.out.println("📄 Section Header: " + minReq.getText());

        // === 9. First Bullet Requirement ===
        WebElement firstReq = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li/p[contains(text(),'Must possess at least a bachelor’s degree')]")));
        String expectedReq = "Must possess at least a bachelor’s degree or its equivalent in Software Engineering, Computer Science, Mechatronic Engineering, or a related field and at least five years of progressive experience in a Senior QA Analyst, Software Development Engineer In test or related role.";
        Assert.assertEquals(firstReq.getText(), expectedReq);
        System.out.println("✅ First requirement verified");
// 1. Validate First Sentence of Third Paragraph ===

        WebElement thirdParagraph = driver.findElement(By.xpath("//p[contains(text(),'Collaborate with product team')]"));
        String actualText = thirdParagraph.getText();
        String expectedText = "Collaborate with product team to break down stories, understand requirements, and define use cases.";

        Assert.assertEquals(actualText, expectedText);
        System.out.println("✅ Verified first sentence of third paragraph: " + actualText);


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
        try {
            // Wait for apply buttons and click the first one
            List<WebElement> applyButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[@data-ph-at-id='apply-link']")
            ));
            applyButtons.get(0).click();
            System.out.println("✅ Click Apply Button");

            // Wait for URL to contain "myworkdayjobs.com"
            wait.until(ExpectedConditions.urlContains("myworkdayjobs.com"));
            try {
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement acceptBtn = wait1.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-automation-id='legalNoticeAcceptButton']")));
                acceptBtn.click();
                System.out.println("✅ Accept Cookies button clicked");
            } catch (Exception e) {
                System.out.println("⚠️ Accept Cookies button not found or already handled");
            }
            // 1. Verify current URL contains "myworkdayjobs.com"
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("myworkdayjobs.com"), "❌ Not on the expected application page");
            System.out.println("✅ Verified Workday application page URL: " + currentUrl);

            // 2. Verify header text "Start Your Application"
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(),'Start Your Application')]")
            ));
            Assert.assertEquals(header.getText().trim(), "Start Your Application");
            System.out.println("✅ Verified header text: " + header.getText());

            // 3. Verify job title displayed as "Senior QA Analyst"
            WebElement jobTitle2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Senior QA Analyst')]")
            ));
            Assert.assertEquals(jobTitle2.getText().trim(), "Senior QA Analyst");
            System.out.println("✅ Verified job title on application page: " + jobTitle2.getText());

            // 4. Verify "Apply Manually" button is visible
            WebElement applyManuallyBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Apply Manually']")
            ));
            Assert.assertTrue(applyManuallyBtn.isDisplayed(), "❌ 'Apply Manually' button is not visible");
            System.out.println("✅ 'Apply Manually' button is visible");

            // (Optional) Click the "Apply Manually" button
            applyManuallyBtn.click();
            System.out.println("🖱️ Clicked 'Apply Manually'");

        } catch (Exception e) {
            System.out.println("❌ Error occurred during verification or interaction on the application page.");

        }

// Add further verification on the next page if needed...



    }
}
