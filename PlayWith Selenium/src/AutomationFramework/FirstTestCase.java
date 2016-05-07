package AutomationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;  

public class FirstTestCase {

	public static void main(String[] args) throws InterruptedException  {
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://store.demoqa.com");
		
		//Print message
		System.out.println("Successfully opened the website www.Store.Demoqa.com");
		
		//Wait 5 sec
		Thread.sleep(20);
		
		//Close diver
		driver.quit();

	}

}


