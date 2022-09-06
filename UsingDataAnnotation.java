package loginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsingDataAnnotation {
		String[][]	data={	
		{"Admin","admin123"},
		{"admin","admin1234"},
		{"admin","admin123"},
		{"Admin","admin1234"}
		};
		@DataProvider(name="dataprovider1")
		public String[][] logindataprovider() {
			return data;
		}
		
		@Test(dataProvider = "dataprovider1")
		public void Login(String username, String password) throws InterruptedException {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			Thread.sleep(3000);
			WebElement usernametextbox= driver.findElement(By.xpath("//*[@name='username']"));
			usernametextbox.sendKeys(username);
			WebElement passwordtextbox= driver.findElement(By.xpath("//*[@name='password']"));
			passwordtextbox.sendKeys(password);
			Thread.sleep(3000);
			WebElement loginbutton=	driver.findElement(By.xpath("//*[@type='submit']"));
			loginbutton.click();
			Thread.sleep(3000);
			Thread.sleep(10000);
			driver.quit();
			
		}


}

