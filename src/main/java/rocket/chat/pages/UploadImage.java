package rocket.chat.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadImage {
	

	WebDriver driver;
	BaseTest baseTest = new BaseTest();
	
	
	@FindBy(xpath="//figure")
	WebElement avatar;
	
	@FindBy(xpath="//div[contains(text(),'My Account')]")
	WebElement myAccount;
	
	@FindBy(xpath="//div[contains(text(),'Profile')]")
	WebElement profile;
	
	@FindBy(xpath="//button[@title='Upload']")
	WebElement uploadImage;
	
	@FindBy(xpath="//button[contains(text(),'Save changes')]")
	WebElement saveImage;
	
	@FindBy(xpath="//div[contains(text(),'Avatar changed successfully')]")
	WebElement successMsg;
	
	@FindBy(xpath = "//header/div[1]/button[1]")
	WebElement crossBtn;

	@FindBy(xpath = "//div[contains(text(),'Logout')]")
	WebElement btnLogout;
	
	
	public UploadImage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}
	
	 public String uploadImage(String image) throws InterruptedException {
	    	avatar.click();
	    	myAccount.click();
	    	profile.click();
	    	uploadImage.click();
	    	baseTest.upload(image);

	    	Thread.sleep(2000);
			
	    	saveImage.click();
	    	System.out.println("Save changes clicked");
	    	Thread.sleep(2000);
	    	System.out.println(successMsg.isDisplayed());
	    	String successChange= successMsg.getText();
	    	System.out.println(successChange);
	    	crossBtn.click();
	    	return successChange;
	    }

	 public void LogoutApplication() throws InterruptedException {
		 avatar.click();
	     btnLogout.click();
	     Thread.sleep(1000);
		}
}
