package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                "html:target/default-cucumber-reports",
                "rerun:target/rerun.txt",
                "pretty"
        },
//        tags = {"@Negative"},
        features = "src/test/resources",
        glue = {"StepDefs"},
        monochrome = false,
        dryRun = false

)
public class CukesRunner {
}
