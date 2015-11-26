package com.testobject.calculatortest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testobject.appium.common.TestObject;
import org.testobject.appium.junit.TestObjectAppiumSuite;

import static org.junit.Assert.assertTrue;

@TestObject(testLocally = true, testObjectApiKey = "YOUR_API_KEY", testObjectSuiteId = 123)
@RunWith(TestObjectAppiumSuite.class)
public class OperationTests extends AbstractTest {

    public OperationTests() {}

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

        app.calculatorScreen().addTwoAndTwo();
        assertTrue(app.calculatorScreen().isResultCorrect("4"));

    }

}