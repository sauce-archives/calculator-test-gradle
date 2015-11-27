package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import com.testobject.calculatortest.util.Device;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OperationTests extends AbstractTest {

    public OperationTests() {}

    protected OperationTests(Device device, AppiumDriverBuilder driverBuilder) {
        super(device, driverBuilder);
    }

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

        app.calculatorScreen().addTwoAndTwo();
        assertTrue(app.calculatorScreen().isResultCorrect("4"));

    }

}
