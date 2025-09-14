package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MoblieNumberValidation {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1. Open Engage
        driver.get("https://engage-uat.msrcosmos.com/#/");

        // 2. Login
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("tilothama109@yopmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Cosmos12#");
        driver.findElement(By.cssSelector("button[type='submit'].btn-primary")).click();

        // 3. Handle popup
        Thread.sleep(3000); // better: use WebDriverWait
        WebElement popup = driver.findElement(By.cssSelector(".swal2-popup"));
        if (popup.isDisplayed()) {
            driver.findElement(By.xpath("//button[text()='Continue']")).click();
        }

        // 4. Verify login toast (optional)
        String toastMsg = driver.findElement(By.cssSelector("div.toast-message")).getText();
        System.out.println("Toast Message: " + toastMsg);

        Thread.sleep(3000);

        // 5. Select Precision org
        driver.findElement(By.xpath("//h3[text()='Precision']")).click();

        Thread.sleep(3000);

        // 6. Navigate to Manage Users
        driver.findElement(By.xpath("//span[@class='title' and text()='Manage Users']")).click();

        Thread.sleep(3000);

        // 7. Validate Phone Numbers
        List<WebElement> phoneCells = driver.findElements(By.cssSelector("table td:nth-child(3)"));

        int validCount = 0;
        int invalidCount = 0;

        // Pattern: +91 (India) or +1 (USA)
        Pattern mobilePattern = Pattern.compile("^(\\+91\\s[6-9]\\d{9}|\\+1\\s\\d{10})$");

        for (WebElement cell : phoneCells) {
            String mobile = cell.getText().trim();
            if (mobilePattern.matcher(mobile).matches()) {
                System.out.println("✅ Valid: " + mobile);
                validCount++;
            } else {
                System.out.println("❌ Invalid: " + mobile);
                invalidCount++;
            }
        }

        System.out.println("📊 Total Valid Numbers   = " + validCount);
        System.out.println("📊 Total Invalid Numbers = " + invalidCount);

        if (invalidCount > 0) {
            throw new AssertionError("Found " + invalidCount + " invalid phone numbers!");
        }

        // Close browser
        //driver.quit();
    }
}




