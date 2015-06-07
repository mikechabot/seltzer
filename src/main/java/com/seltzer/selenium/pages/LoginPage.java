package com.seltzer.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	@FindBy(how = How.TAG_NAME, using = "body")
	public WebElement body;
	
	@FindBy(how = How.ID, using = "signin-email")
	public WebElement usernameInput;
	
	@FindBy(how = How.ID, using = "signin-password")
	public WebElement passwordInput;
	
	@FindBy(how = How.CLASS_NAME, using = "js-submit")
	public WebElement submitButton;
	
}
