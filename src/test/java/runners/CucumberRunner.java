package runners;



import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * //TODO:
 * Implement a Junit Test Runner class.
 * This class just needs annotations to understand that cucumber features would be run through it
 * and you can specify feature files to be picked up plus the steps package location.
 */

@RunWith(Cucumber.class)
@CucumberOptions(

		plugin= { "json:target/cucumber-json/cucumber.json","pretty","html:target/default-report"},

	    features = {"src/test/resources"},
		glue = {"steps"},

		tags = "@error",
		dryRun = false
		)

public class CucumberRunner {

}
