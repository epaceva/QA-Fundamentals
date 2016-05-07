package AutomationTestingComemnts;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testComemnts {
	
	private WebDriver  driver;
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
		driver.get("http://eobshtestvo.com/");
		driver.manage().window().maximize();
		driver.get("http://eobshtestvo.com/d-154114d40bb/Siriiskite-emigranti-turtzia-izviva-ratzete-na-evropa/");
	}
	
	@Test
	public void testSuccessfullPost() {
		this.CreateComment();
		
		WebElement summaryText = driver.findElement(By.id("commentText"));
		summaryText.sendKeys("В крайна сметка или турците ще ни завладеят или сирийските емигранти.");
		
		WebElement submitButton = driver.findElement(By.id("commentBtn"));
		submitButton.click();
	}

	private void CreateComment() {
		WebElement nameComment = driver.findElement(By.id("commentName"));
		nameComment.sendKeys("Eva");
		WebElement emailComment = driver.findElement(By.id("commentMail"));
		emailComment.sendKeys("sss_2005@abv.bg");
		
	}

	public static void main(String[] args) {
		

	}

}
