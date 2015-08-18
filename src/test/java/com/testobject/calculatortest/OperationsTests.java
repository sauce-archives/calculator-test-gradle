package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import com.testobject.calculatortest.util.Device;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OperationsTests extends AbstractTest {

    public OperationsTests() {}

    protected OperationsTests(Device device, AppiumDriverBuilder driverBuilder) {
        super(device, driverBuilder);
    }

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

        app.calculatorScreen().addTwoAndTwo();
        assertTrue(app.calculatorScreen().isResultFour());

    }

    /* An invalid operation, it navigates to the advanced panel, selects factorial, then minus,
     * then the equal button. The expected result is an error message in the result field. */
    @Test
    public void factorialMinusOperation() {

        app.calculatorScreen().doFactorialMinusOperation();
        assertFalse(app.calculatorScreen().isOperationValid());

    }

}
