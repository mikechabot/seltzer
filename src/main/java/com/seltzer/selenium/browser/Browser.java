package com.seltzer.selenium.browser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

    private static final int WAIT_IN_SEC = 10;

    public static WebElement waitForElement(WebElement elementToWaitFor){
        return waitForElement(elementToWaitFor, WAIT_IN_SEC);
    }

    public static WebElement waitForElement(WebElement elementToWaitFor, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(BrowserDriver.getCurrentDriver(), waitTimeInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    public static void goTo(String page) {
        BrowserDriver.getCurrentDriver().get(page);
    }

}
