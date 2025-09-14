package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LabCorpJobTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Step 1: Open LabCorp website
        driver.get("https://www.labcorp.com");
// ✅ Handle cookie popup if it appears
        // Handle cookie banner

            // Accept All Cookies
            WebElement acceptBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (acceptBtn.isDisplayed()) {
                acceptBtn.click();
                System.out.println("✅ Accepted cookies");
            }


        // Step 2: Click Careers link
        WebElement careersLink = driver.findElement(By.linkText("Careers"));
        careersLink.click();
        Thread.sleep(3000);


        // Step 3: Search for a job
        WebElement searchBox = driver.findElement(By.id("typehead"));
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();

        Thread.sleep(3000);

        // Step 4: Select the first job result
        WebElement firstJob = driver.findElement(By.cssSelector("a.job-title"));
        firstJob.click();
        Thread.sleep(3000);

        // Step 5: Assertions (simple if-else checks)

        // a. Job Title
        String jobTitle = driver.findElement(By.cssSelector("h1.job-title")).getText();
        if (jobTitle.contains("Senior QA Analyst")) {
            System.out.println("✅ Job Title verified: " + jobTitle);
        } else {
            System.out.println("❌ Job Title mismatch: " + jobTitle);
        }

        // b. Job Location
        String jobLocation = driver.findElement(By.cssSelector("span.job-location")).getText();
        if (!jobLocation.isEmpty()) {
            System.out.println("✅ Job Location verified: " + jobLocation);
        } else {
            System.out.println("❌ Job Location not found.");
        }

        // c. Job ID
        String jobId = driver.findElement(By.cssSelector("span.job-id")).getText();
        if (jobId.toLowerCase().contains("job id")) {
            System.out.println("✅ Job ID verified: " + jobId);
        } else {
            System.out.println("❌ Job ID not found: " + jobId);
        }

        driver.quit();
    }
}
