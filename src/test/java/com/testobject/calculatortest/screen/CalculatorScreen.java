package com.testobject.calculatortest.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorScreen extends AbstractScreen {

    @AndroidFindBy(id = "net.ludeke.calculator:id/plus")
    private MobileElement buttonPlus;

    @AndroidFindBy(id = "net.ludeke.calculator:id/equal")
    private MobileElement buttonEquals;

    @AndroidFindBy(id = "net.ludeke.calculator:id/factorial")
    private MobileElement buttonFactorial;

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    private MobileElement resultField;

    @AndroidFindBy(id = "net.ludeke.calculator:id/overflow_menu")
    private MobileElement menuButton;

    @AndroidFindBy(name = "Advanced panel")
    private MobileElement advancedPanelButton;

    public CalculatorScreen(AppiumDriver driver) {
        super(driver);
    }

    public void tapDigit(String digit) {

        try {
            findElementWithTimeout(By.name(digit), 10).click();
        } catch (NoSuchElementException e) {
            System.out.println("Button "+digit+" not found!");
        }

    }

    public void navigateToAdvancedPanel() {

        menuButton.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(advancedPanelButton));
        advancedPanelButton.click();

    }

    public boolean isResultCorrect(String result) {

        try {

            /* Check if within given time the correct result appears in the designated field. */
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, result));
            return true;

        } catch (Exception e) {

            return false;

        }

    }

    public boolean isOperationValid() {

        try {

            /* Check if within given time the correct result appears in the designated field. */
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(resultField, "Error"));
            return false;

        } catch (Exception e) {

            return true;

        }

    }

}