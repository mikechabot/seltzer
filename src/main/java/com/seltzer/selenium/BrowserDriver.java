package com.seltzer.selenium;

import com.seltzer.config.AppProperties;
import com.seltzer.util.Sleep;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class BrowserDriver {

	private static Logger log = Logger.getLogger(BrowserDriver.class);
	private static WebDriver driver;

	public static WebDriver getCurrentDriver() {
		if (driver == null) {
			log.info("Getting new FirefoxDriver...");
            String pathToFirefox = AppProperties.INSTANCE.getRequiredString("path-to-firefox");
            FirefoxBinary firefoxBinary = new FirefoxBinary(new File(pathToFirefox));
			driver = new FirefoxDriver(firefoxBinary, new FirefoxProfile());
		}
		return driver;
	}
	
	public static void close() {
		try {
			getCurrentDriver().manage().deleteAllCookies();
			getCurrentDriver().close();
			getCurrentDriver().quit();
			driver = null;
		} catch (UnreachableBrowserException e) {
			log.error("Cannot close browser: unreachable browser", e);
		}
	}

	public static void loadPage(String url) {
		log.info("Directing browser to: " + url);
		getCurrentDriver().get(url);
	}

    public static String getSource() {
        return driver.getPageSource();
    }

	/**
	 * Wait for an element to load
	 * @param elementToWaitFor
	 * @return
	 */
	public static WebElement waitForElement(WebElement elementToWaitFor){
		return waitForElement(elementToWaitFor, null);
	}
	
	/**
	 * Wait for an element to load
	 * @param elementToWaitFor
	 * @param waitTimeInSeconds
	 * @return
	 */
	public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
	    if (waitTimeInSeconds == null) {
	    	waitTimeInSeconds = 10;
	    }
	    WebDriverWait wait = new WebDriverWait(getCurrentDriver(), waitTimeInSeconds);
	    return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
	}
	
	/**
	 * Assert that a particular element contains a given text string
	 * @param element
	 * @param textToLookFor
	 * @return
	 */
	public static WebElement assertElementContainsText(WebElement element, String textToLookFor) {
		if (element == null) {
			log.error("Unable to assert: element is null");
			fail();
		} else {
			assertTrue(element.getText().contains(textToLookFor));
		}
		return element;
	}

	/**
	 * Assert that a given list of WebElements contains a list of text strings. 
	 * Useful for determining that a set of options matches a user's expectations
	 * @param elements
	 * @param checkForTheseOptions
	 */
	public static void assertElementsContainText(List<WebElement> elements, List<String> checkForTheseOptions) {
		if (elements == null || elements.isEmpty()) {
			log.error("Unable to assert: elements is null/empty");
			fail();
		} else if (checkForTheseOptions == null || checkForTheseOptions.isEmpty()) {
			log.error("Unable to assert: options list is null/empty");
			fail();
		} else {
			List<String> temp = new ArrayList<String>(0);
			for (WebElement element : elements) {
				temp.add(element.getText());
			}
			/* Assert success of failure */
			if (!temp.equals(checkForTheseOptions)) {
				log.error("Unable to find: " + checkForTheseOptions.toString());
				log.error("In this list: " + temp.toString());
				fail();
			}
		}
	}
	
	/**
	 * Retrieve an element from a list given a text string.
	 * Useful for identifying which link to click in a list of <a> tags
	 * @param elements
	 * @param textToLookFor
	 * @return
	 */
	public static WebElement getElementFromListByText(List<WebElement> elements, String textToLookFor) {
		WebElement element = null;
		for (WebElement each : elements) {
			if (each.getText().equals(textToLookFor)) {
				element = each;
			}
		}
		/* Assert success or failure */
		if (element == null) {
			log.error("Unable to find '" + textToLookFor + "'");
			fail();
			return null;
		}
		return element;
	}

	/**
	 * Click on an element, then sleep for a couple of seconds
	 * @param element
	 */
	public static void click(WebElement element) {
		if (element != null) {
			element.click();
			Sleep.forSec(2);
		}
	}

	public static void assertElementSize(List<WebElement> elements,	int expectedSize) {
		log.info("Asserting there are " + expectedSize + " elements in the supplied list");
		if (elements != null && !elements.isEmpty()) {
			if (elements.size() != expectedSize) {
				log.error("Found " + elements.size() + " elements instead of the expected " + expectedSize);
				fail();
			}
		} else {
			log.error("Unable to assert: elements is empty");
			fail();
		}
	}
}