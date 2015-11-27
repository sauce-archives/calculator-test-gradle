package com.testobject.calculatortest;

import com.testobject.calculatortest.screen.CalculatorScreen;
import com.testobject.calculatortest.util.Device;
import io.appium.java_client.AppiumDriver;

public class Calculator {

    private final Device device;
    private final AppiumDriver driver;

    public Calculator(Device device, AppiumDriver driver) {
        this.device = device;
        this.driver = driver;
    }

    public CalculatorScreen calculatorScreen() { return new CalculatorScreen(driver, device); }

}