package com.seltzer;

import com.seltzer.config.AppProperties;
import com.seltzer.selenium.BrowserDriver;
import com.seltzer.selenium.View;
import com.seltzer.selenium.views.LoginView;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.reflections.Reflections;
import sun.rmi.runtime.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class Seltzer implements Runnable {

	private static Logger log = Logger.getLogger(Seltzer.class);

	public static Seltzer seltzer;

	private Thread thread;
	private boolean isRunning;

	private Seltzer() { }

	public static Seltzer getInstance() {
		if (seltzer == null) {
            seltzer = new Seltzer();
		}
		return seltzer;
	}

    /**
     * Start seltzer
     */
	public void start() {
		if (!isRunning) {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}

    /**
     * Stop seltzer
     */
	public void stop() {
		if (isRunning) {
			isRunning = false;
			thread.interrupt();
		}
	}

    /**
     * Return whether seltzer is running
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     *
     */
	@Override
	public void run() {

//        initializeViews();

        LoginView.init();

        String startingUrl = AppProperties.INSTANCE.getRequiredString("starting-url");
        BrowserDriver.loadPage(startingUrl);
        log.info(BrowserDriver.getSource());

        LoginView.isDisplayedCheck();
        LoginView.login("somename", "somepassword");


	}

    private void initializeViews() {
        try {
            Reflections reflections = new Reflections("com.seltzer");
            Set<Class<? extends View>> classes = reflections.getSubTypesOf(View.class);
            for (Class each : classes) {
                Method method = each.getMethod("init");
                method.invoke(new Object(), null);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
