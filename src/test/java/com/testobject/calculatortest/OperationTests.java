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

        app.calculatorScreen().tapDigit("2");
        app.calculatorScreen().tapSymbol("+");
        app.calculatorScreen().tapDigit("2");
        app.calculatorScreen().tapSymbol("=");

        assertTrue(app.calculatorScreen().isResultCorrect("4"));

    }

    /* An invalid operation, it navigates to the advanced panel, selects factorial, then minus,
     * then the equal button. The expected result is an error message in the result field. */
    @Test
    public void factorialMinusOperation() {

        app.calculatorScreen().navigateToAdvancedPanel();
        app.advancedPanelScreen().tapSymbol("!");
        app.calculatorScreen().tapSymbol("−");
        app.calculatorScreen().tapSymbol("=");

        assertFalse(app.calculatorScreen().isOperationValid());

    }

}
