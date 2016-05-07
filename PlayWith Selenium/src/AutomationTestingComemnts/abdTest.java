package AutomationTestingComemnts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class abdTest {
	private  WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		
	}
		
	
	
	@Test
	public void TestLogin() {
		driver.get("https://abv.bg");
	}
	
	@After
	public void terDown(){
		
	}

	

}
