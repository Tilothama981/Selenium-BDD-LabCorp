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
import java.util.Set;

public class Labcorp3 {

    static WebDriver driver;
    static WebDriverWait wait;

    // === Handle Cookie Banner ===
    public static void handleCookieBanner() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[@id='onetrust-accept-btn-handler' " +
                            "or @data-automation-id='legalNoticeAcceptButton' " +
                            "or contains(text(),'Accept') or contains(text(),'I Agree')]")
            ));
            if (cookieBtn.isDisplayed() && cookieBtn.isEnabled()) {
                cookieBtn.click();
                System.out.println("🍪 Cookie banner closed.");
            }
        } catch (Exception e) {
            System.out.println("ℹ️ No cookie banner displayed.");
        }
    }

    // === Wrapper methods to auto-handle cookies ===
    public static WebElement findElement(By locator) {
        handleCookieBanner();
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement findClickable(By locator) {
        handleCookieBanner();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static List<WebElement> findElements(By locator) {
        handleCookieBanner();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static String getText(By locator) {
        return findElement(locator).getText();
    }

    public static void click(By locator) {
        findClickable(locator).click();
    }

    public static void sendKeys(By locator, String text) {
        WebElement el = findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    // === Switch to new tab ===
    public static void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                System.out.println("🔄 Switched to new tab: " + driver.getTitle());
                break;
            }
        }
    }

    // === Main Test Flow ===
    public static void main(String[] args) {

        // 1. Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 2. Open LabCorp Homepage
        driver.get("https://www.labcorp.com/");

        // 3. Click Careers
        click(By.linkText("Careers"));

        // 4. Enter Job Title
        sendKeys(By.id("typehead"), "Sr QA Analyst");
        click(By.id("ph-search-backdrop"));

        // 5. Select Job Title
        String jobText = getText(By.cssSelector("span[data-ph-id='ph-page-element-page11-Zk12Zp']"));
        System.out.println("🔍 Job Title Found: " + jobText);
        Assert.assertTrue(jobText.contains("Senior QA Analyst"));
        click(By.cssSelector("span[data-ph-id='ph-page-element-page11-Zk12Zp']"));

        String jobTitle1 = getText(By.className("job-title"));
        System.out.println("Job Title: " + jobTitle1);

        String jobLocation = getText(By.xpath("//span[contains(@class,'job-location')]"));
        System.out.println("Job Location: " + jobLocation);

        String jobId = getText(By.className("jobId"));
        System.out.println("Job ID: " + jobId);

        // 6. Verify Minimum Requirements Header
        String minReq = getText(By.xpath("//span[text()='Minimum Requirements:']"));
        System.out.println("📄 Section Header: " + minReq);

        // 7. First Bullet Requirement
        String expectedReq = "Must possess at least a bachelor’s degree or its equivalent in Software Engineering, Computer Science, Mechatronic Engineering, or a related field and at least five years of progressive experience in a Senior QA Analyst, Software Development Engineer In test or related role.";
        String actualReq = getText(By.xpath("//li/p[contains(text(),'Must possess at least a bachelor’s degree')]"));
        Assert.assertEquals(actualReq, expectedReq);
        System.out.println("✅ First requirement verified");

        // 8. Validate Third Paragraph
        String expectedText = "Collaborate with product team to break down stories, understand requirements, and define use cases.";
        String actualText = getText(By.xpath("//p[contains(text(),'Collaborate with product team')]"));
        Assert.assertEquals(actualText, expectedText);
        System.out.println("✅ Verified first sentence of third paragraph: " + actualText);

        // 9. Confirm Selenium Tool
        try {
            String toolText = getText(By.xpath("(//li[contains(text(),'Selenium')] | //p[contains(text(),'Selenium')])[1]"));
            Assert.assertTrue(toolText.contains("Selenium"),
                    "❌ First suggested tool does not mention Selenium. Actual: " + toolText);
            System.out.println("✅ Verified first suggested automation tool contains Selenium: " + toolText);
        } catch (Exception e) {
            System.out.println("❌ Failed to verify first suggested automation tool with Selenium.");
        }

        // 10. Click Apply and switch to Workday tab
        try {
            List<WebElement> applyButtons = findElements(By.xpath("//a[@data-ph-at-id='apply-link']"));
            applyButtons.get(0).click();
            System.out.println("✅ Click Apply Button");

            // Switch focus to Workday application tab
            switchToNewTab();

            WebElement jobTitleElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h3[normalize-space()='Senior QA Analyst']")
                    ));

            System.out.println("✅ Apply Page Job Title is Matching: " + jobTitleElement.getText());
            driver.findElement(By.cssSelector("button[data-automation-id='navigationItem-Careers Home']")).click();
            System.out.println("✅Navigated back  to Carrier Home page: ");
        } catch (Exception e) {
            System.out.println("❌ Error occurred during verification or interaction on the application page.");
            e.printStackTrace();
        }

       // driver.quit(); // Close browser after execution
    }
}
