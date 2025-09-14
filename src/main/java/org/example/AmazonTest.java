//package org.example;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.util.List;
//
//public class AmazonTest {
//    WebDriver driver;
//
//    @BeforeTest
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
//
//    @Test(priority = 1)
//    public void openAmazonPage() {
//        driver.get("https://www.amazon.in/");
//        String title = driver.getTitle();
//        System.out.println("Page Title: " + title);
//        assert title.contains("Amazon") : "Amazon page did not open!";
//    }
//
//    @Test(priority = 2)
//    public void getAllLinks() {
//        List<WebElement> links = driver.findElements(By.tagName("a"));
//        System.out.println("Total number of links: " + links.size());
//
//        for (WebElement link : links) {
//            String linkText = link.getText();
//            String linkHref = link.getAttribute("href");
//            System.out.println(linkText + " -> " + linkHref);
//        }
//    }
//
//    @AfterTest
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
