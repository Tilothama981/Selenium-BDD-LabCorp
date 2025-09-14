
package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class AmazonMobile17k {
    public static void main(String[] args) {
        // ---- Mobile Emulation: Pixel 2 ----
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        // (Optional) reduce bot detection a bit:
        options.addArguments("--disable-blink-features=AutomationControlled");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // 1) Open Amazon India (mobile site) with price filter ~₹17,000
            // p_36 is in paise: 16,50,000–17,50,000
            String url = "https://amazon.com";
            driver.get(url);

            // 2) Wait for search results
            By resultCard = By.cssSelector("div[data-component-type='s-search-result']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultCard));

            // 3) Grab first suitable result (skip sponsored if needed)
            List<WebElement> results = driver.findElements(resultCard);
            WebElement first = null;
            for (WebElement r : results) {
                // Skip “More results” tiles or non-product cards
                List<WebElement> titleEls = r.findElements(By.xpath(".//h2//span"));
                if (titleEls.isEmpty()) continue;

                // Skip sponsored if labeled (best-effort; may vary)
                boolean sponsored = !r.findElements(By.xpath(".//*[contains(translate(.,'SPONSORED','sponsored'),'sponsored')]")).isEmpty();
                if (!sponsored) {
                    first = r;
                    break;
                }
            }
            if (first == null) first = results.get(0); // fallback

            // 4) Extract title and price from list page
            String listTitle = safeText(first, By.xpath(".//h2//span"));
            String listPriceWhole = safeText(first, By.cssSelector(".a-price .a-price-whole"));
            String listPriceFrac  = safeText(first, By.cssSelector(".a-price .a-price-fraction"));
            String listPrice = joinPrice(listPriceWhole, listPriceFrac);

            String listRating = safeAttr(first, By.xpath(".//i[contains(@class,'a-icon-star')]//span"), "innerText");
            if (listRating == null || listRating.isBlank())
                listRating = safeAttr(first, By.xpath(".//span[@aria-label and contains(@aria-label,'out of 5 stars')]"), "aria-label");

            System.out.println("== Top Result (List Page) ==");
            System.out.println("Title : " + listTitle);
            System.out.println("Price : " + (listPrice.isBlank() ? "N/A" : "₹" + listPrice));
            System.out.println("Rating: " + (listRating == null ? "N/A" : listRating));
            System.out.println();

            // 5) Open the product
            WebElement link = first.findElement(By.xpath(".//h2//a"));
            link.click();

            // 6) On PDP: fetch title, price, rating, and “About this item” bullets
            // Title
            String pdpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@id='productTitle' or @id='title' or contains(@class,'product-title-word-break')]")
            )).getText().trim();

            // Price
            String pdpPrice = "";
            List<By> priceLocators = Arrays.asList(
                    By.id("priceblock_ourprice"),
                    By.id("priceblock_dealprice"),
                    By.cssSelector("span.a-price > span.a-offscreen"),
                    By.xpath("//span[contains(@class,'a-price')]/span[@class='a-offscreen']")
            );
            for (By p : priceLocators) {
                List<WebElement> els = driver.findElements(p);
                if (!els.isEmpty()) {
                    pdpPrice = els.get(0).getText().trim();
                    if (!pdpPrice.isBlank()) break;
                }
            }

            // Rating
            String pdpRating = "";
            List<WebElement> ratingEls = driver.findElements(By.xpath("//span[@data-hook='rating-out-of-text' or @id='acrPopover' or @id='acrCustomerReviewText' or @aria-label[contains(.,'out of 5 stars')]]"));
            if (!ratingEls.isEmpty()) {
                pdpRating = ratingEls.get(0).getText().trim();
            }

            // About this item bullets
            List<String> bullets = new ArrayList<>();
            List<WebElement> bulletEls = driver.findElements(By.cssSelector("#feature-bullets ul li span"));
            if (bulletEls.isEmpty()) {
                // mobile fallback
                bulletEls = driver.findElements(By.xpath("//div[@id='feature-bullets']//li//span"));
            }
            for (WebElement b : bulletEls) {
                String t = b.getText().trim();
                if (!t.isBlank()) bullets.add(t);
                if (bullets.size() >= 6) break; // top 6 points
            }

            System.out.println("== Product Page Details ==");
            System.out.println("Title : " + pdpTitle);
            System.out.println("Price : " + (pdpPrice.isBlank() ? "N/A" : pdpPrice));
            System.out.println("Rating: " + (pdpRating.isBlank() ? "N/A" : pdpRating));
            System.out.println("Top Features:");
            if (bullets.isEmpty()) {
                System.out.println("  (No bullets found)");
            } else {
                for (String line : bullets) {
                    System.out.println("  • " + line);
                }
            }

        } finally {
            //driver.quit();
        }
    }

    private static String safeText(WebElement root, By locator) {
        List<WebElement> els = root.findElements(locator);
        return els.isEmpty() ? "" : els.get(0).getText().trim();
    }

    private static String safeAttr(WebElement root, By locator, String attr) {
        List<WebElement> els = root.findElements(locator);
        if (els.isEmpty()) return null;
        if ("innerText".equals(attr)) return els.get(0).getText();
        return els.get(0).getAttribute(attr);
    }

    private static String joinPrice(String whole, String frac) {
        whole = whole == null ? "" : whole.replaceAll("[^0-9]", "");
        frac  = frac == null ? "" : frac.replaceAll("[^0-9]", "");
        if (whole.isBlank()) return "";
        if (frac.isBlank()) return whole;
        return whole + "." + (frac.length() == 1 ? "0" + frac : frac);
    }
}
