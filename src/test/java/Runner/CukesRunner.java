package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

//        tags = {"@Negative"},
        features = "src/test/resources",
        glue = {"StepDefs"},
        monochrome = false,
        plugin = "pretty",
        dryRun = false

)
public class CukesRunner {
}
