package com.seltzer.selenium;

import org.openqa.selenium.support.PageFactory;

public abstract class View {

	public static Object getContainer(Class<?> clazz) {
		return PageFactory.initElements(BrowserDriver.getCurrentDriver(), clazz);
	}

}
