package loginTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.print.attribute.standard.SheetCollate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UsingExcel {
	WebDriver driver;
	@DataProvider(name="logindata")
	public String[][] getexceldata() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream
				("C:\\DemoProject\\excel/logindata.xls");
		Workbook wbook=	Workbook.getWorkbook(excel);
		Sheet shet=	wbook.getSheet(0);
		int rowcount=	shet.getRows();
		int columncount= shet.getColumns();
		String testdata[][] = new String[rowcount-1][columncount];//space allocation for 4x2
		for(int i=1; i<rowcount;i++) {
			for(int j=0; j<columncount;j++) {
		testdata[i-1][j]=shet.getCell(j, i).getContents();
			}
			
		}
		return testdata;
	}
	@BeforeTest
	public void openbrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(5000);
	}
	@Test(dataProvider = "logindata")
	public void functionalites(String username, String password) throws InterruptedException {
		
		Thread.sleep(5000);
		WebElement usernametextbox= driver.findElement(By.xpath("//*[@name='username']"));
		usernametextbox.sendKeys(username);
		WebElement passwordtextbox= driver.findElement(By.xpath("//*[@name='password']"));
		passwordtextbox.sendKeys(password);
		Thread.sleep(3000);
		WebElement loginbutton=	driver.findElement(By.xpath("//*[@type='submit']"));
		loginbutton.click();
		
		
	}
	@AfterTest
	public void closebrowser() {
		driver.quit();
	}
}
