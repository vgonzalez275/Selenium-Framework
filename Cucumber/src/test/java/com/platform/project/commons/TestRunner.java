package com.platform.project.commons;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
        glue = {"com.platform.project.steps"},
        monochrome = true,
        plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html","json:target/cucumber-reports/cucumber.json"},
        tags = {"@tag"}
		)


public class TestRunner {
	
}
