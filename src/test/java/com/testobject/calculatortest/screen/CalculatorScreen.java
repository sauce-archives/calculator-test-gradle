package com.testobject.calculatortest.screen;

import com.testobject.calculatortest.util.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorScreen extends AbstractScreen {

    MobileElement resultField;


    public CalculatorScreen(AppiumDriver driver, Device device) {
        super(driver, device);
    }

    public void addTwoAndTwo(){

        /* Get the elements. */
        MobileElement buttonTwo = findElement(By.id("net.ludeke.calculator:id/digit2"));
        MobileElement buttonPlus = findElement(By.id("net.ludeke.calculator:id/plus"));
        MobileElement buttonEquals = findElement(By.id("net.ludeke.calculator:id/equal"));

        /* Add two and two. */
        buttonTwo.click();
        buttonPlus.click();
        buttonTwo.click();
        buttonEquals.click();

    }

    public boolean isResultFour() {

        try {

            resultField = findElement(By.xpath("//android.widget.EditText[1]"));

            /* Check if within given time the correct result appears in the designated field. */
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, "4"));
            return true;

        } catch (Exception e) {

            return false;

        }

    }

    public void doFactorialMinusOperation() {

        /* In the main panel... */
        MobileElement menuButton = findElement(By.id("net.ludeke.calculator:id/overflow_menu"));
        menuButton.click();

        MobileElement advancedPanelButton = (MobileElement)(new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("Advanced panel")));
        advancedPanelButton.click();

        /* In the advanced panel... */
        MobileElement factorialButton = (MobileElement)(new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("net.ludeke.calculator:id/factorial")));
        factorialButton.click();

        /* In the main panel again. */
        MobileElement minusButton = (MobileElement)(new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("net.ludeke.calculator:id/minus")));
        minusButton.click();

        MobileElement equalsButton = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/equal")));
        equalsButton.click();

        resultField = (MobileElement)(driver.findElement(By.xpath("//android.widget.EditText[1]")));

    }

    public boolean isOperationValid() {

        try {

            /* Check if within given time the correct result appears in the designated field. */
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, "Error"));
            return true;

        } catch (Exception e) {

            return false;

        }

    }

}