package com.testobject.calculatortest.screen;

import com.testobject.calculatortest.util.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class AbstractScreen {

    protected final AppiumDriver driver;
    protected final Device device;

    public AbstractScreen(AppiumDriver driver, Device device) {
        this.device = device;
        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void hideKeyboard() {
        if(device.isTablet){
            if(driver instanceof IOSDriver){
                ((IOSDriver) driver).hideKeyboard("Hide keyboard");
            }
        }
    }

    protected void takeScreenShot(){
        driver.getScreenshotAs(OutputType.BASE64);
    }

    protected String takePageSource(){
        return driver.getPageSource();
    }

    protected void swipeRight() {

        TouchAction swipeAction = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int endx = (int) (size.width * 0.8);
        int startx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        swipeAction.press(startx, starty).moveTo(endx, starty).release().perform();

    }

    protected void swipeLeft() {

        TouchAction swipeAction = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int endx = (int) (size.width * 0.8);
        int startx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        swipeAction.press(endx, starty).moveTo(startx, starty).release().perform();

    }

    protected void doubleTapElement(MobileElement element) {
        new TouchAction(driver).press(element).perform().release().press(0,0).perform();
    }

    protected void waitUntilLoaded() {
        if (driver instanceof IOSDriver) {
            new WebDriverWait(driver, 30).until(invisibilityOfElementLocated(By.className("UIAActivityIndicator")));
            return;
        }

        throw new IllegalStateException("unsupported appium driver: " + driver);
    }

    public MobileElement findElement(By by) {
        return (MobileElement)driver.findElement(by);
    }

    public MobileElement findElementWithTimeout(By by, int timeOutInSeconds) {
        return (MobileElement)(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebDriverWait wait(long timeout, TimeUnit unit){
        return new WebDriverWait(driver, unit.toSeconds(timeout));
    }

}