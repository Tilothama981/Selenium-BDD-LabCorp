//package org.example;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//
//public class Login {
//
//        public static void main(String[] args) {
//            // Set the path to chromedriver (skip this if it's already in your system PATH)
//            //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//
//            // Launch the Chrome browser
//            WebDriver driver = new ChromeDriver();
//
//            // Maximize the window
//            driver.manage().window().maximize();
//
//            // Open the login page
//            driver.get("https://madeinvsa.com/home");
//
//            // Enter username
//            WebElement usernameField = driver.findElement(By.className("nav-link"));
//            usernameField.sendKeys("student");
//
//            // Enter password
//            WebElement passwordField = driver.findElement(By.id("password"));
//            passwordField.sendKeys("Password123");
//
//            // Click Login
//            WebElement loginButton = driver.findElement(By.className("nav-link"));
//            loginButton.click();
//
//            // Optional: validate login
//            String currentUrl = driver.getCurrentUrl();
//            if (currentUrl.contains("logged-in-successfully")) {
//                System.out.println("Login successful!");
//            } else {
//                System.out.println("Login failed.");
//            }
//
//            // Close the browser
//            driver.quit();
//        }
//    }
