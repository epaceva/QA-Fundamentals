package eObshtestvo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class logIn {
	
	
	public static void main(String[] args) {
		String exePath = "C:/eclipse/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);

	}
	public WebDriver driver;
	
	@Before
	public void setUp(){
	driver = new FirefoxDriver();
	
	driver.get("http://cms.avalchev.com/");
	driver.manage().window().maximize();
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
