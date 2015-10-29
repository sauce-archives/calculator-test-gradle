package com.testobject.calculatortest.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractScreen {

    protected final AppiumDriver driver;

    public AbstractScreen(AppiumDriver driver) {
        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public MobileElement findElement(By by) {
        return (MobileElement)driver.findElement(by);
    }

    public MobileElement findElementWithTimeout(By by, int timeOutInSeconds) {
        return (MobileElement)(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void tapSymbol(String symbol) {

        try {
            findElement(By.name(symbol)).click();
        } catch (NoSuchElementException e) {
            System.out.println("Button "+symbol+" not found!");
        }

    }

    protected void takeScreenShot(){
        driver.getScreenshotAs(OutputType.BASE64);
    }

}