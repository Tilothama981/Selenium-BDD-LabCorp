package stepdefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LabcorpSteps {

    static WebDriver driver;
    static WebDriverWait wait;

    // === Cookie Handling ===
    public void handleCookieBanner() {
        try {
            WebElement cookieBtn = driver.findElement(By.xpath(
                    "//button[@id='onetrust-accept-btn-handler' " +
                            "or @data-automation-id='legalNoticeAcceptButton' " +
                            "or contains(text(),'Accept') or contains(text(),'I Agree')]"
            ));
            if (cookieBtn.isDisplayed() && cookieBtn.isEnabled()) {
                cookieBtn.click();
            }
        } catch (Exception e) {
            // No cookie banner displayed
        }
    }

    public WebElement findElement(By locator) {
        handleCookieBanner();
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty()) return elements.get(0);
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        handleCookieBanner();
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty()) return elements;
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void click(By locator) {
        WebElement el = findElement(locator);
        el.click();
    }

    public void sendKeys(By locator, String text) {
        WebElement el = findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    // === Steps ===

    @Given("I launch the Labcorp website")
    public void i_launch_the_labcorp_website() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.labcorp.com/");
        handleCookieBanner();
    }

    @When("I navigate to the Careers page")
    public void i_navigate_to_the_careers_page() {
        click(By.linkText("Careers"));
    }

    @When("I search for {string}")
    public void i_search_for(String job) {
        sendKeys(By.id("typehead"), job);
        click(By.id("ph-search-backdrop"));
    }

    @When("I select the job posting {string}")
    public void i_select_the_job_posting(String jobTitle) {
        List<WebElement> jobs = findElements(By.cssSelector("span[data-ph-id^='ph-page-element']"));
        for (WebElement job : jobs) {
            if (job.getText().contains(jobTitle)) {
                job.click();
                break;
            }
        }
    }

    @Then("I verify the job title, location, and job id")
    public void i_verify_job_details() {
        System.out.println("Job Title: " + getText(By.className("job-title")));
        System.out.println("Location: " + getText(By.xpath("//span[contains(@class,'job-location')]")));
        System.out.println("Job ID: " + getText(By.className("jobId")));
    }

    @Then("I verify minimum requirements")
    public void i_verify_minimum_requirements() {
        String expectedReq = "Must possess at least a bachelor’s degree or its equivalent in Software Engineering, Computer Science, Mechatronic Engineering, or a related field and at least five years of progressive experience in a Senior QA Analyst, Software Development Engineer In test or related role.";
        String actualReq = getText(By.xpath("//li/p[contains(text(),'Must possess at least a bachelor’s degree')]"));
        Assert.assertEquals(actualReq, expectedReq);
    }

    @Then("I verify the third paragraph requirement")
    public void i_verify_third_paragraph() {
        String expectedText = "Collaborate with product team to break down stories, understand requirements, and define use cases.";
        String actualText = getText(By.xpath("//p[contains(text(),'Collaborate with product team')]"));
        Assert.assertEquals(actualText, expectedText);
    }

    @Then("I verify Selenium is listed as a tool")
    public void i_verify_selenium_tool() {
        String toolText = getText(By.xpath("(//li[contains(text(),'Selenium')] | //p[contains(text(),'Selenium')])[1]"));
        Assert.assertTrue(toolText.contains("Selenium"));
    }

    @When("I click on Apply and switch to application tab")
    public void i_click_apply_and_switch_tab() {
        List<WebElement> applyButtons = findElements(By.xpath("//a[@data-ph-at-id='apply-link']"));
        applyButtons.get(0).click();

        // Switch to new tab
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        handleCookieBanner();
    }

    @Then("I verify the application page job title {string}")
    public void i_verify_application_page_job_title(String expectedTitle) {
        String actualTitle = getText(By.xpath("//h3[normalize-space()='" + expectedTitle + "']"));
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Then("I navigate back to Careers Home")
    public void i_navigate_back_to_careers_home() {
        click(By.cssSelector("button[data-automation-id='navigationItem-Careers Home']"));
        handleCookieBanner();
    }
}
