package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import com.testobject.calculatortest.util.Device;
import io.appium.java_client.AppiumDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

public abstract class AbstractTest {

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher("https://app.testobject.com:443/api");

    private final Device device;
    private final AppiumDriverBuilder driverBuilder;
    private AppiumDriver driver;

    protected Calculator app;

    public AbstractTest() {
        this.device = Device.Motorola_Moto_e_2nd_gen;
        this.driverBuilder = AppiumDriverBuilder.forAndroid();
    }

    public AbstractTest(Device device, AppiumDriverBuilder driverBuilder) {
        this.device = device;
        this.driverBuilder = driverBuilder;
    }

    @Before
    public void connect() {

        driverBuilder.withSuiteName(this.getClass().getName());
        driverBuilder.withTestName(testName.getMethodName());

        driver = driverBuilder.build();

        System.out.println("View test report: " + driver.getCapabilities().getCapability("testobject_test_report_url"));
        System.out.println("View test live: " + driver.getCapabilities().getCapability("testobject_test_live_view_url"));

        resultWatcher.setAppiumDriver(driver);

        app = new Calculator(device, driver);

    }

}