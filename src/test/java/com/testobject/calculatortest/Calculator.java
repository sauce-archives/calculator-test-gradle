package com.testobject.calculatortest;

import com.testobject.calculatortest.screen.AdvancedPanelScreen;
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

    public CalculatorScreen calculatorScreen() { return new CalculatorScreen(driver, device); }

    public AdvancedPanelScreen advancedPanelScreen() { return new AdvancedPanelScreen(driver, device); }

}