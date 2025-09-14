package runners;

//package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",         // Path to your .feature files
        glue = {"stepdefinitions"},                       // Package containing step definitions
        plugin = {
                "pretty",                                     // Print Gherkin steps in console
                "html:target/cucumber-report.html",           // HTML report output
                "json:target/cucumber-report.json"            // Optional: JSON output for CI/reporting
        },
        monochrome = true                                 // Cleaner console output (no ANSI chars)
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

