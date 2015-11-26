package com.testobject.calculatortest.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorScreen extends AbstractScreen {

    @AndroidFindBy(id = "net.ludeke.calculator:id/digit2")
    private MobileElement buttonTwo;

    @AndroidFindBy(id = "net.ludeke.calculator:id/plus")
    private MobileElement buttonPlus;

    @AndroidFindBy(id = "net.ludeke.calculator:id/equal")
    private MobileElement buttonEquals;

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    private MobileElement resultField;

    public CalculatorScreen(AppiumDriver driver) {
        super(driver);
    }

    public void addTwoAndTwo() {

        buttonTwo.click();
        buttonPlus.click();
        buttonTwo.click();
        buttonEquals.click();

    }

    public boolean isResultCorrect(String result) {

        try {

            /* Check if within given time the correct result appears in the designated field. */
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, result));
            return true;

        } catch (TimeoutException e) {

            return false;

        }

    }

}