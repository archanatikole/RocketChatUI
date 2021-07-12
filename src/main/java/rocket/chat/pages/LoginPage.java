package rocket.chat.pages;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	WebDriver driver;
	BaseTest baseTest = new BaseTest();

	@FindBy(id = "emailOrUsername")
	WebElement txtUsername;
	@FindBy(id = "pass")
	WebElement txtPassword;
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	WebElement btnLogin;

	
	
	
	
	
	
	
	
	public LoginPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);

	}

	// Set user name in textbox
	public void setUserName(String strUserName) {

		txtUsername.sendKeys(strUserName);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {

		txtPassword.sendKeys(strPassword);

	}

	// Click on login button
	public void clickLogin() {

		btnLogin.click();

	}
	
	 public String getLoginTitle(){

	     return driver.getTitle();

	    }
	 
	
		
	
	/**

     * This POM method will be exposed in test case to login in the application

     * @param strUserName

     * @param strPasword

     * @return

     */

    public void loginToRocketChat(String strUserName,String strPasword){

        //Fill user name
        this.setUserName(strUserName);

        //Fill password
        this.setPassword(strPasword);

        //Click Login button
        this.clickLogin();         
       

    }
  

   
   
    
    
    
    

}
