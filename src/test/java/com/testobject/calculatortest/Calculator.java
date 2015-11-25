package com.testobject.calculatortest;

import com.testobject.calculatortest.screen.CalculatorScreen;
import io.appium.java_client.AppiumDriver;

public class Calculator {

    private final AppiumDriver driver;

    public Calculator(AppiumDriver driver) {
        this.driver = driver;
    }

    public CalculatorScreen calculatorScreen() { return new CalculatorScreen(driver); }

}