package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.testobject.appium.common.TestObjectCapabilities;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

import java.net.MalformedURLException;

public abstract class AbstractTest {

    /* Grab the test name. */
    @Rule
    public TestName testName = new TestName();

    /* Set the test result watcher to send test results to TestObject. */
    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

    private AppiumDriver driver;
    protected Calculator app;

    /* Establish a connection to TestObject, or to a local device test is local. */
    @Before
    public void connect() throws MalformedURLException {

        this.driver = AppiumDriverBuilder.forAndroid()
                    .withApiKey(resultWatcher.getApiKey())
                    .withTestReportId(resultWatcher.getTestReportId())
                    .withEndpoint(resultWatcher.getTestObjectOrLocalAppiumEndpointURL())
                    .build();

        resultWatcher.setAppiumDriver(driver);
        app = new Calculator(driver);

    }

    /* After the session has ended, print out a link to the TestObject test report, if we are testing online. */
    @After
    public void tearDown() {
        if (testIsInCloud()) {
            System.out.println("Test " + testName.getMethodName() + " is over. View test report for it: " + driver.getCapabilities().getCapability("testobject_test_report_url"));
        }
    }

    /* Determines whether we are testing on the cloud or locally by checking if the Api key is set. s*/
    public boolean testIsInCloud() {
        return resultWatcher.getApiKey() != null;
    }

}