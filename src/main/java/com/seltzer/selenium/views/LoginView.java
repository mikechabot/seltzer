package com.seltzer.selenium.views;

import com.seltzer.container.LoginContainer;
import com.seltzer.selenium.BrowserDriver;
import com.seltzer.selenium.View;
import org.apache.log4j.Logger;

public class LoginView extends View {

    private static Logger log = Logger.getLogger(LoginView.class);
    private static LoginContainer loginContainer;

    public static void init() {
        loginContainer = (LoginContainer) getContainer(LoginContainer.class);
    }

    public static void isDisplayedCheck() {
        log.info("Checking login page is displayed");
        BrowserDriver.waitForElement(loginContainer.loginFormLegend);
        log.info(loginContainer.usernameInput.isDisplayed());
        log.info(loginContainer.passwordInput.isDisplayed());
    }

    public static void login(String username, String password) {
        init();
        log.info("Logging in with username: " + username + " password: " + password);
        loginContainer.usernameInput.sendKeys(username);
        loginContainer.passwordInput.sendKeys(password);
        loginContainer.submitButton.click();
        log.info("Login submitted");
    }

}
