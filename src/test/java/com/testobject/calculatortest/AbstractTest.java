package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

public abstract class AbstractTest {

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

    private final AppiumDriverBuilder driverBuilder;
    private AppiumDriver driver;

    protected Calculator app;

    public AbstractTest() {
        this.driverBuilder = AppiumDriverBuilder.forAndroid();
    }

    @Before
    public void connect() {

        driverBuilder.withApiKey(resultWatcher.getApiKey());

        try {
            driverBuilder.withTestReportId(resultWatcher.getTestReportId());
        } catch (NullPointerException e) {

        }

        driver = driverBuilder.build();

        resultWatcher.setAppiumDriver(driver);

        app = new Calculator(driver);

        System.out.println("Test " + testName.getMethodName() + " is starting. View it live: " + driver.getCapabilities().getCapability("testobject_test_live_view_url"));

    }
    @After
    public void tearDown() {
        System.out.println("Test " + testName.getMethodName() + " is over. View test report for it: " + driver.getCapabilities().getCapability("testobject_test_report_url"));
    }

}