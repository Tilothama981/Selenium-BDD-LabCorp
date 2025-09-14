package org.example;

//import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class Addreq1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        // 1. Open ATS login page
        driver.get("https://requirements-uat.msrcosmos.com/#/login");

        // 2. Login
        driver.findElement(By.cssSelector("input[formcontrolname='email']"))
                .sendKeys("tilothama109@yopmail.com");
        driver.findElement(By.cssSelector("input[formcontrolname='password']"))
                .sendKeys("Cosmos12#");
        driver.findElement(By.cssSelector("button[type='submit'].btn-primary")).click();

        // 3. Handle modal (Override button if appears)
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal")));
            modal.findElement(By.xpath(".//button[contains(text(),'Override')]")).click();
            wait.until(ExpectedConditions.invisibilityOf(modal));
            System.out.println("✅ Override modal handled");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ No override modal displayed");
        }

        // 4. Verify login success toast
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".swal2-popup.swal2-toast.swal2-icon-success.swal2-show")));
        System.out.println("✅ Login Toast: " + toast.getText());
        Thread.sleep(3000);
        // 5. Check if Choose Organization page is displayed
        boolean orgPage = false;
        try {
            wait.until(ExpectedConditions.urlContains("/home/choose-organization"));
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[text()='Choose any Organization']")));
         //   Assert.assertTrue("Heading not displayed!", heading.isDisplayed());
            System.out.println("✅ Verified Choose Organization page");

            // Click Precision org
            WebElement precisionOrg = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//h3[text()='Precision']")));
            precisionOrg.click();
            System.out.println("✅ Clicked Precision Organization");

            orgPage = true;
        } catch (TimeoutException e) {
            System.out.println("ℹ️ No organization selection page, continuing directly...");
        }

        // 6. Navigate to My Candidates
        wait.until(ExpectedConditions.urlContains("/home/requirements"));
        WebElement myCandidates = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("My Candidates")));
        myCandidates.click();
        System.out.println("✅ Navigated to My Candidates");

        // 7. Verify Candidates page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),'Candidates')]")));
        System.out.println("✅ Candidates page verified");

        // 8. Files to upload
        String[] fileNames = {
                "65718_1612207191Zachary-Dobson.pdf",
                "65743_1612279650Christine-Swords.pdf"
                // add more file names here
        };

        // 9. Loop through files and upload
        for (String fileName : fileNames) {
            // Click Add Candidate button
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mb-5 > .btn"))).click();

            // Click Checkbox
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".me-3 > .form-check-input"))).click();

            // Upload file (from local project "files" folder)
            String filePath = Paths.get("src/test/resources/files", fileName).toAbsolutePath().toString();
            WebElement fileInput = driver.findElement(By.id("inputGroupFile04"));
            fileInput.sendKeys(filePath);
            System.out.println("📂 Uploaded file: " + fileName);

            Thread.sleep(3000); // wait for processing

            // Enable and click Submit button
            WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled')", submitBtn);
            submitBtn.click();
            System.out.println("✅ Clicked Submit");

            Thread.sleep(5000); // wait for upload

            // Select Work Authorization
            WebElement workAuth = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#workAuthorization .ng-input")));
            workAuth.click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("ng-dropdown-panel .ng-option:nth-child(6)"))).click();
            System.out.println("✅ Selected Work Authorization");

            // Click Save button
            driver.findElement(By.cssSelector("#personal_Info .modal-footer .btn-primary")).click();
            System.out.println("✅ Candidate saved successfully");

            Thread.sleep(3000);
        }

        // 10. Close browser
        driver.quit();
    }
}
