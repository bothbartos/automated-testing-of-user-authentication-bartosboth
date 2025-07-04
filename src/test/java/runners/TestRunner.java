package runners;


import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "html:target/cucumber-reports/cucumber.html," +
                "json:target/cucumber-reports/cucumber.json")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,
        value = "stepDefinitions,hooks")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,
        value = "not @ignore")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME,
        value = "false")
@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME,
        value = "false")
@ConfigurationParameter(key = "cucumber.execution.parallel.enabled",
        value = "true")
@ConfigurationParameter(key = "cucumber.execution.parallel.config.strategy",
        value = "fixed")
@ConfigurationParameter(key = "cucumber.execution.parallel.config.fixed.parallelism",
        value = "3")
public class TestRunner {}
