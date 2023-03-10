package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = "steps",
		features = "src/test/resources/features/",
		dryRun = false,
		tags = "@test1",
		monochrome = true,
		plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
				"rerun:target/failed.txt", "json:target/cucumber-reports/CucumberTestReport.json"}
		
		
)
public class Smoke {
}
