package rocket.chat.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class BaseTest {
	private XSSFWorkbook workbook= null;
	private XSSFSheet spreadsheet= null;
	private XSSFCell Cell= null;
	private XSSFRow Row= null;
	private FileOutputStream fos = null;
	private FileInputStream fis =null;
	
	
	
	 public boolean setCellData(String fileName,String sheetName, String row,String value)
	    {
	        try
	        {
	        	
	        	 fis = new FileInputStream(fileName);
				 workbook = new XSSFWorkbook(fis);
				 spreadsheet = workbook.getSheet(sheetName);
	             	
	        	
	            int rowCount = spreadsheet.getLastRowNum() - spreadsheet.getFirstRowNum() + 1;
	            System.out.println(rowCount);
	            int colNumber=3;
	           
	           // for (int i = 0; i < rowCount; i++) {
	            int i = Integer.parseInt(row);
	            Row = spreadsheet.getRow(i);
	            Cell = Row.getCell(colNumber);
	            if(Cell == null)
	                Cell = Row.createCell(colNumber);
	 	            Cell.setCellValue(value);
	            //}
	            fos = new FileOutputStream(fileName);
	            workbook.write(fos);
	            fos.close();
	        }
	            
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return  false;
	        }
	        return true;
	    }
	
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayActualData = null;
		
		
		try {
			 fis = new FileInputStream(fileName);
			 workbook = new XSSFWorkbook(fis);
			 spreadsheet = workbook.getSheet(sheetName);

			int rowCount = spreadsheet.getLastRowNum() - spreadsheet.getFirstRowNum() + 1;
			int colCount = spreadsheet.getRow(0).getLastCellNum();

			arrayActualData = new String[rowCount][colCount];

			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					arrayActualData[i][j] = spreadsheet.getRow(i).getCell(j).getStringCellValue();
				}
			}

			workbook.close();
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		

		return arrayActualData;
	}
	
    
	
	public void upload(String imagePath) {
    	
   	 StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
   }
	
	
   
}
