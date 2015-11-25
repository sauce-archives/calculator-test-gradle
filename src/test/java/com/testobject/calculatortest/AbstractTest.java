package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
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

}