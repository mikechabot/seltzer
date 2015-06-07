package com.seltzer.selenium.browser;

import com.seltzer.config.AppProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;

public class BrowserDriver {

	private static Logger log = Logger.getLogger(BrowserDriver.class);

    private static WebDriver driver;

    public static WebDriver getCurrentDriver() {
        if (driver==null) {
            String pathToBinary = AppProperties.INSTANCE.getRequiredString("path-to-firefox");
            FirefoxBinary firefoxBinary = new FirefoxBinary(new File(pathToBinary));
            driver = new FirefoxDriver(firefoxBinary, new FirefoxProfile());
        }
        return driver;
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            driver = null;
            log.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            log.info("cannot close browser: unreachable browser");
        }
    }

}