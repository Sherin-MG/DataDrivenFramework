package loginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BothIncorrect {
	@Test
	@Parameters({"Username","Password"})
	public void LoginBI(String username, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		WebElement usernametextbox= driver.findElement(By.xpath("//*[@name='username']"));
		usernametextbox.sendKeys("Username");
		
		WebElement passwordtextbox= driver.findElement(By.xpath("//*[@name='password']"));
		passwordtextbox.sendKeys("Password");
		
		WebElement loginbutton=	driver.findElement(By.xpath("//*[@type='submit']"));
		loginbutton.click();
		driver.quit();
	}

}
