package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "json:target/cucumber.json",
                "html:target/cucumberReport.html",

            },
        features = "src/test/resources",
        glue = {"StepDefs"},
        dryRun = false
        )
public class CukesRunner {
}
