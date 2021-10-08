package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/resources/", glue= {"stepDefinition"}, tags = {"@DeletePlace"})
public class Runner {

}
