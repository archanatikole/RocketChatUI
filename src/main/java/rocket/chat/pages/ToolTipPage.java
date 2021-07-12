package rocket.chat.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolTipPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[@data-qa='sidebar-search']")
	WebElement searchTool;
	
	@FindBy(xpath="//div[@role='group']//button[3]")
	WebElement directoryTool;
	
	@FindBy(xpath="//button[@data-qa='sidebar-search']")
	WebElement displayTool;
	
	@FindBy(xpath="//button[@data-qa='sidebar-create']")
	WebElement createNewTool;
	
	
	
	
	
	public ToolTipPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}
	
	public String getSearchTooltip() {
		
		String actualSearchTooltip = searchTool.getAttribute("title");
		System.out.println(actualSearchTooltip);
		return actualSearchTooltip;
	}
	public String getDirectoryTooltip() {
		
		String actualDirectoryTooltip = directoryTool.getAttribute("title");
		System.out.println(actualDirectoryTooltip);
		return actualDirectoryTooltip;
	}
	
	public String getDisplayTooltip() {
		
		String actualDisplayTooltip = displayTool.getAttribute("title");
		System.out.println(actualDisplayTooltip);
		return actualDisplayTooltip;
	}
	
	public String getCreateNewTooltip() {
		
		String actualCreateNewTooltip = createNewTool.getAttribute("title");
		System.out.println(actualCreateNewTooltip);
		return actualCreateNewTooltip;
	}
	
	
	 public void verifyTooltips(String strSearchTooltip,String strDirectoryTooltip,String strDisplayTooltip,String strCreateNewTooltip) {
	    	String actualSearchTooltip = getSearchTooltip();
	    	String actualDirectoryTooltip = getDirectoryTooltip();
	    	String actualDisplayTooltip = getDisplayTooltip();
	    	String actualCreateNewTooltip = getCreateNewTooltip();
	    	
	    	if(actualSearchTooltip.equalsIgnoreCase(strSearchTooltip)) {
	    		System.out.println("Search tooltip matched");
	    	}
	    	
	    	if(actualDirectoryTooltip.equalsIgnoreCase(strDirectoryTooltip)) {
	    		System.out.println("Directory tooltip matched");
	    	}
	    	
	    	if(actualDisplayTooltip.equalsIgnoreCase(strDisplayTooltip)) {
	    		System.out.println("Display tooltip matched");
	    	}
	    	
	    	if(actualCreateNewTooltip.equalsIgnoreCase(strCreateNewTooltip)) {
	    		System.out.println("CreateNew tooltip matched");
	    	}
	    	
	    	
	    }
	 
	
}
