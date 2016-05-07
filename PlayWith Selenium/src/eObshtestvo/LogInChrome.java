package eObshtestvo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class LogInChrome {
	
	static WebDriver driver = new ChromeDriver();
	
	public static void main(String[] args) {
		String exePath = "C:\\eclipse\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://cms.avalchev.com/");
	}
	

	@Test
	public void sucessfullLogIn(){
		this.Login1();
		
		WebElement signIn = driver.findElement(By.xpath("/html/body/div/div/button"));
		System.out.println(signIn.getText());
		signIn.click();
	}
		
	private void Login1(){
		WebElement emailAddress = driver.findElement(By.id("mail"));
		emailAddress.sendKeys("aleksandar.valchev@gmail.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("123456");
		WebElement base = driver.findElement(By.id("base"));
		base.sendKeys("eo");
	}
	

}
