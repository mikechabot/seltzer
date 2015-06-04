package com.seltzer.container;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginContainer {

	@FindBy(how = How.CLASS_NAME, using = "navbar-default")
	public WebElement loginFormLegend;
	
	@FindBy(how = How.CLASS_NAME, using = "js-username-field")
	public WebElement usernameInput;
	
	@FindBy(how = How.CLASS_NAME, using = "js-password-field")
	public WebElement passwordInput;
	
	@FindBy(how = How.CLASS_NAME, using = "submit")
	public WebElement submitButton;
	
}
