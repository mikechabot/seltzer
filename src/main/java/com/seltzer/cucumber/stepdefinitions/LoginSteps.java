package com.seltzer.cucumber.stepdefinitions;

import com.seltzer.selenium.browser.Browser;
import com.seltzer.selenium.views.HomeView;
import com.seltzer.selenium.views.LoginView;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.TestCase.assertTrue;

public class LoginSteps {

    private final String TWITTER_URL = "https://www.twitter.com";

    @Given("^the bot navigates to the Twitter login page$")
    public void the_bot_navigates_to_Twitter() throws Throwable {
        Browser.goTo(TWITTER_URL);
        assertTrue(LoginView.isDisplayed());
    }

    @When("^it enters \"(.*?)\" into the username field$")
    public void it_enters_into_the_username_field(String username) throws Throwable {
        LoginView.enterUsername(username);
    }

    @When("^\"(.*?)\" into the password field$")
    public void into_the_password_field(String password) throws Throwable {
        LoginView.enterPassword(password);
    }

    @When("^it clicks the submit button$")
    public void it_clicks_the_submit_button() throws Throwable {
        LoginView.login();
    }

    @Then("^it should login successfully$")
    public void it_should_login_successfully() throws Throwable {
        assertTrue(HomeView.isDisplayed());
    }

}
