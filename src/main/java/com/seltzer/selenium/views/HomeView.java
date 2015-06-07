package com.seltzer.selenium.views;

import com.seltzer.selenium.View;
import com.seltzer.selenium.pages.HomePage;
import org.apache.log4j.Logger;

public class HomeView extends View {

    private static Logger log = Logger.getLogger(LoginView.class);
    private static HomePage homePage;

    public static void init() {
        homePage = (HomePage) getContainer(HomePage.class);
    }

    public static boolean isDisplayed() {
        return homePage.homeButton.isDisplayed();
    }

}
