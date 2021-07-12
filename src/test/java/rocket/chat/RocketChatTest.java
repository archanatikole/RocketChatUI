package rocket.chat;


import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rocket.chat.pages.BaseTest;
import rocket.chat.pages.LoginPage;
import rocket.chat.pages.SendMessage;
import rocket.chat.pages.ToolTipPage;
import rocket.chat.pages.UploadImage;

public class RocketChatTest {

	String driverPath = "C:\\geckodriver.exe";

	WebDriver driver;

	LoginPage loginPage;
	ToolTipPage toolTipPage;
	UploadImage uploadImage;
	SendMessage sendMessage;
	
	BaseTest baseTest = new BaseTest();
	String path = System.getProperty("user.dir") + "\\src\\test\\resources";

	String imagePath = path + "\\archanat.jpg";
	String dataPath = path + "\\data.xlsx";
	String result="Fail";
	@BeforeTest
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://open.rocket.chat/home");
		

	}

	/**
	 * 
	 * Login to application
	 * 
	 * Verify the home page using Dashboard message
	 * 
	 * @throws InterruptedException
	 * 
	 */

	@Test(dataProvider = "myData")
	public void testRocketChat(String strUsername, String strPassword, String strSearch, String strDirectory,
			String strDisplay, String strCreate) throws InterruptedException {

		
		// Create Page object
		loginPage = new LoginPage(driver);
		toolTipPage = new ToolTipPage(driver);
		uploadImage = new UploadImage(driver);
		sendMessage = new SendMessage(driver);
		

		// login to application

		loginPage.loginToRocketChat(strUsername, strPassword);

		toolTipPage.verifyTooltips(strSearch, strDirectory, strDisplay, strCreate);

		String successmsg = uploadImage.uploadImage(imagePath);

		Assert.assertEquals(successmsg, "Avatar changed successfully");
		
		
	}
	
	@Test(dataProvider = "myData1")
	public void testRocketChatMessage(String row, String newMsg, String searchText) throws InterruptedException {

		boolean messageDispalyed=false,dataAdded=false;
		String strUser="",msgTime="",removeStarText="",sentMsgTime="";
		
		
		
		
		sendMessage.sendMessage(newMsg);
		
		Thread.sleep(1000);
		sendMessage.opernSearchText();
		sentMsgTime=sendMessage.getMessageStamp();
		System.out.println("Sent Msg Time "+sentMsgTime);
		
		Thread.sleep(2000);
		messageDispalyed=sendMessage.searchText(searchText);
		
						
		if(messageDispalyed) {
			System.out.println("Message found");
			strUser = sendMessage.getUser();
			if(strUser.contains("archana")) {
			 System.out.println("User is correct");
			}
			Assert.assertEquals(strUser, "archanatikole701");
			msgTime=sendMessage.getMsgTime();
			
			System.out.println("\nMsg Timestamp: "+msgTime);
			Assert.assertEquals(msgTime, sentMsgTime);
			
			sendMessage.closeSearch();
			sendMessage.hoverOverMsg(sentMsgTime);
			sendMessage.printListOptions(sentMsgTime);
			sendMessage.clickStarMsg(sentMsgTime);
			
			sendMessage.hoverOverMsg(sentMsgTime);
			System.out.println("Message Hovered");
			Thread.sleep(1000);
			removeStarText=sendMessage.verifyRemoveStarMsg(sentMsgTime);
			System.out.println(removeStarText);
			Assert.assertEquals(removeStarText, "Remove Star");
			sendMessage.clickRemoveStarMsg(sentMsgTime);
			result="Pass";
			
			}
			else if(messageDispalyed==false) {
			result="Fail";
			System.out.println("Message not found");
			sendMessage.closeSearch();
			}
		
		
		
		dataAdded=baseTest.setCellData(dataPath,"Sheet2",row,result);
		if(dataAdded) {
			System.out.println("Result updated for row " );
		}
		
		
		
	}

	@DataProvider(name = "myData")
	public Object[][] ReadData() {
		System.out.println(dataPath);
		Object[][] data = baseTest.getExcelData(dataPath, "Sheet1");
		return data;
	}
	@DataProvider(name = "myData1")
	public Object[][] ReadMsges() {
		System.out.println(dataPath);
		Object[][] data = baseTest.getExcelData(dataPath, "Sheet2");
		return data;
	}

	

	@AfterTest
	public void TearDown() {
		driver.close();
	}
}
