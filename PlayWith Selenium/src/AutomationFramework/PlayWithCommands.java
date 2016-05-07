package AutomationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlayWithCommands {
	
	public static void main(){
		
		WebDriver driver = new ChromeDriver();
		String URL = "http://www.google.com";
		//launch site
		driver.get(URL);
		
	}

}
