package com.seltzer.selenium.views;

import com.seltzer.selenium.pages.LoginPage;
import com.seltzer.selenium.View;
import org.apache.log4j.Logger;

public class LoginView extends View {

    private static Logger log = Logger.getLogger(LoginView.class);
    private static LoginPage loginPage;

    public static void init() {
        loginPage = (LoginPage) getContainer(LoginPage.class);
    }

    public static boolean isDisplayed() {
        log.info("Asserting login page is visible");
        return loginPage.body.isDisplayed();
    }

    public static void enterUsername(String username) {
        log.info("Entering username: " + username);
        loginPage.usernameInput.sendKeys(username);
    }

    public static void enterPassword(String password) {
        log.info("Entering password: " + password);
        loginPage.passwordInput.sendKeys(password);
    }

    public static void login() {
        log.info("Logging in...");
        loginPage.submitButton.click();
    }

}
