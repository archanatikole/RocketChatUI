package rocket.chat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessage {

	WebDriver driver;
	BaseTest baseTest = new BaseTest();

	@FindBy(xpath = "//div[contains(text(),'general')]")
	WebElement general;

	@FindBy(xpath = "//textarea[@name='msg']")
	WebElement messageBox;

	@FindBy(xpath = "//div[@class='js-input-message-shadow']")
	WebElement sendMsg;
	
	@FindBy(xpath="//li[@data-username='archanatikole701']")
	List<WebElement> listMessages;

	// Search text

	@FindBy(xpath = "//header/div[1]/div[3]/button[6]")
	WebElement searchMessage;

	@FindBy(xpath = "//input[@id='message-search']")
	WebElement searchBox;

	@FindBy(xpath = "//div[@class='rocket-default-search-results']")
	List<WebElement> searchResult;

	@FindBy(xpath = "//li[@data-context='search']")
	List<WebElement> searchedMessage;

	@FindBy(xpath = "//div[@class='rocket-default-search-results']/h2")
	WebElement noResult;

	@FindBy(xpath = "//li[@data-context='search']//time[@class='time']")
	WebElement timeOfMsg;

	@FindBy(xpath = "//button[@title='Close']")
	WebElement closeSearch;
	
	@FindBy(xpath="//div[@data-popover='popover']//ul[@class='rc-popover__list']")
	List<WebElement> listOptions;

	
	@FindBy(xpath="//div[@data-popover='popover']//ul[@class='rc-popover__list']//li[@data-id='star-message']")
	WebElement starMsg;
	
	@FindBy(xpath="//span[contains(text(),'Remove Star')]")
	WebElement unstarMsg;
	
	

	public SendMessage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}
	public void clickGeneral() {
		System.out.println(general.isDisplayed());
		general.click();
	}

	public void sendMessage(String message) {
		clickGeneral();
		messageBox.sendKeys(message, Keys.ENTER);
	
		// sendMsg.click();

	}
	public String getMessageStamp() {
		String sentMsgTimeStamp="";
		 for (WebElement webElement : listMessages) {
	            String timeStamp = webElement.getAttribute("data-timestamp");
	            sentMsgTimeStamp =timeStamp;
	        }
		 
		return sentMsgTimeStamp;
		 
	}

	public void opernSearchText() throws InterruptedException {
		clickGeneral();
		Thread.sleep(1000);
		messageBox.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/div[3]/button[6]")));
		searchMessage.click();
	}

	public boolean searchText(String text) throws InterruptedException {
		boolean messageDisplayed = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='message-search']")));
		if (searchBox.isDisplayed()) {
			
			searchBox.sendKeys(text, Keys.ENTER);
		} else {
			searchMessage.click();
			searchBox.sendKeys(text, Keys.ENTER);
		}

		if (searchedMessage.size() > 0) {
			messageDisplayed = true;
			
		} else {
			messageDisplayed = false;
		}

		return messageDisplayed;

	}

	public String getUser() {
		String user = searchedMessage.get(0).getAttribute("data-username");
		System.out.println(user);
		return user;
	}
	
	public String getMsgTime() {
		String msgTime = searchedMessage.get(0).getAttribute("data-timestamp");
		System.out.println(msgTime);
		return msgTime;
	}
	
	public void hoverOverMsg(String msgTime) {
		Actions actions = new Actions(driver);
		WebElement msg = driver.findElement(By.xpath("//li[@data-timestamp='"+msgTime+"']"));
		actions.moveToElement(msg).build().perform();
	}


	public void clickThreeDots(String msgTime) {
		WebElement threeDots = driver.findElement(By.xpath("//li[@data-timestamp='"+msgTime+"']//div[@class='message-actions']//div[@class='message-actions__menu']"));
		threeDots.click();
	}
	
	public void printListOptions(String msgTime) {
		clickThreeDots(msgTime);		 
		 for (WebElement webElement : listOptions) {
	            String name = webElement.getText();
	            System.out.println(name);
	        }
	}
	
	public void clickStarMsg(String msgTime) {
		//clickThreeDots(msgTime);
		starMsg.click();
		
	}
	public String verifyRemoveStarMsg(String msgTime) throws InterruptedException {
		String removeStarMsg="";
		clickThreeDots(msgTime);	
		System.out.println("Three dots clicked....");
		Thread.sleep(1000);
		removeStarMsg=unstarMsg.getText();
		return removeStarMsg;
		
	}
	
	public void clickRemoveStarMsg(String msgTime) {
		//clickThreeDots(msgTime);
		unstarMsg.click();
		
	}
	public void closeSearch() {
		closeSearch.click();
	}

}
