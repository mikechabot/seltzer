package com.seltzer.cucumber;

import com.seltzer.selenium.View;
import com.seltzer.selenium.browser.BrowserDriver;
import com.seltzer.selenium.views.LoginView;
import org.apache.log4j.Logger;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Hooks {

    private static Logger log = Logger.getLogger(Hooks.class);

    /**
     * Run this before every scenario
     */
    @Before
    public void beforeScenario() {
        log.info("Starting the browser...");
        BrowserDriver.getCurrentDriver();
        initializeViews();
    }

    /**
     * Run this after every scenario
     */
    @After
    public void afterScenario() {
        log.info("Closing the browser...");
        BrowserDriver.close();
    }

    /**
     * Initialize the view containers
     */
    private void initializeViews() {
        try {
            Reflections reflections = new Reflections("com.seltzer");
            Set<Class<? extends View>> classes = reflections.getSubTypesOf(View.class);
            if (classes == null || classes.isEmpty()) {
                 throw new IllegalArgumentException("Must initialize at least one view class");
            } else {
                for (Class each : classes) {
                    Method method = each.getMethod("init");
                    method.invoke(new Object(), null);
                }
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