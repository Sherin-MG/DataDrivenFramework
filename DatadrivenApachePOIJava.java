package loginTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatadrivenApachePOIJava {
	static	List<String>usernamelist = new ArrayList<String>();
	static	List<String>passwordlist = new ArrayList<String>();
	WebDriver driver;
	public void loginfunctionality(String username, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(5000);
		WebElement usernametextbox= driver.findElement(By.xpath("//*[@name='username']"));
		usernametextbox.sendKeys(username);
		WebElement passwordtextbox= driver.findElement(By.xpath("//*[@name='password']"));
		passwordtextbox.sendKeys(password);
		Thread.sleep(3000);
		WebElement loginbutton=	driver.findElement(By.xpath("//*[@type='submit']"));
		loginbutton.click();
		Thread.sleep(3000);
		driver.quit();
	
	}
	public void executetest() throws InterruptedException {
		for(int i=0; i<usernamelist.size(); i++){
			loginfunctionality(usernamelist.get(i), passwordlist.get(i));
	}
	}
	public void readexcel() throws IOException {
		FileInputStream exceldata = new FileInputStream("C:\\DemoProject\\excel//logindata1.xls");
		Workbook wbook = new HSSFWorkbook(exceldata) ;
		Sheet shet=	wbook.getSheetAt(0);
		Iterator<Row>rowiterator=	shet.iterator();
		while (rowiterator.hasNext()) {
		Row rowvalue=	rowiterator.next();
		Iterator<Cell>columniterator=rowvalue.iterator();
		int i = 2;
			while (columniterator.hasNext()) {
				if (i%2==0) {
					usernamelist.add(columniterator.next().toString())	;
					
				}else {
					passwordlist.add(columniterator.next().getStringCellValue());
				}
				i++;
		
		
			}
		}
		
	}
	
	
	

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DatadrivenApachePOIJava usingPOI = new DatadrivenApachePOIJava();
		usingPOI.readexcel();
		System.out.println("usernamelist is"+usernamelist);
		System.out.println("passwordlist is"+passwordlist);
		usingPOI.executetest();
	}

}
