package com.testobject.calculatortest;

import io.appium.java_client.AppiumDriver;
import com.testobject.calculatortest.screen.CalculatorScreen;
import com.testobject.calculatortest.util.Device;

public class Calculator {

    private final Device device;
    private final AppiumDriver driver;

    public Calculator(Device device, AppiumDriver driver) {
        this.device = device;
        this.driver = driver;
    }

    public CalculatorScreen loginScreen() {
        return new CalculatorScreen(driver, device);
    }

    public CalculatorScreen calculatorScreen() { return new CalculatorScreen(driver, device); }

}