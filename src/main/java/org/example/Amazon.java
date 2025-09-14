//package org.example;
//package seleniumxpath;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//public class AmazonSearch extends BaseTest {
//    JavascriptExecutor js;
//
//    @FindBy(xpath = "//div[@class=\"nav-search-field \"]/child::input")
//    WebElement serach;
//
//    @FindBy(xpath = "//span[@id=\"nav-search-submit-text\"]/child::input")
//    WebElement searchButton;
//
//    @FindBy(xpath = "//span[@class=\"a-button-inner\"]/button")
//    WebElement landingButton;
//
//    @BeforeMethod
//    @Parameters({ "browser", "url" })
//    public void starts(String browser, String url) {
//
//        setUp(browser, url);
//        PageFactory.initElements(DriverManager.getDriver(), this);
//        js = (JavascriptExecutor) DriverManager.getDriver();
//
//    }
//
//    @Test
//    public void clickOnMobile() {
//        String xpath = "//div[@class='a-section']/ancestor::div[@id='a-page']/descendant::div[@class='a-section'][7]/descendant::div[@data-cy='title-recipe']";
//        // landingButton.click();
//        search.sendKeys("iPHone");
//        searchButton.click();
//        for (int i = 0; i < 2; i++) {
//            js.executeScript("window.scrollBy(0,500)");
//
//        }
//
//        WebElement mobile = DriverManager.getDriver().findElement(By.xpath(xpath));
//        mobile.click();
//
//        String pageTitle = DriverManager.getDriver().getTitle();
//        System.out.println(pageTitle);
//
//    }
//
//}
