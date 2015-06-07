package com.seltzer.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by Mike on 6/3/2015.
 */
public class HomePage {

    @FindBy(how = How.ID, using = "global-nav-home")
    public WebElement homeButton;

}
