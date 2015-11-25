package com.testobject.calculatortest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testobject.appium.common.TestObject;
import org.testobject.appium.junit.TestObjectAppiumSuite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestObject(testObjectApiKey = "7CDE94EFFE3E4EF4A773DB2728688C53", testObjectSuiteId = 780)
@RunWith(TestObjectAppiumSuite.class)
public class OperationTests extends AbstractTest {

    public OperationTests() {}

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

        app.calculatorScreen().tapDigit("2");
        app.calculatorScreen().tapSymbol("+");
        app.calculatorScreen().tapDigit("2");
        app.calculatorScreen().tapSymbol("=");

        assertTrue(app.calculatorScreen().isResultCorrect("4"));

    }

}
