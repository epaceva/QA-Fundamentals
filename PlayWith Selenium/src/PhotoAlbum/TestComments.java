package PhotoAlbum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestComments {
	
	private WebDriver driver;
	private static final String DEFAULT_PASSWORD = "123";
	private String currentUsername;
	private String currentCategory;
	private String currentAlbum;
	

	@Before
	public void setUp(){
		this.driver = new FirefoxDriver();
		
		this.driver.get("http://localhost/photoalbum/home/index");
		
	}
	
	private void register(){
		this.driver.get("http://localhost/photoalbum/users/register");
		WebElement usernameField = this.driver.findElement(By.id("inputUserName"));
		WebElement passwordField = this.driver.findElement(By.id("inputPassword"));
		String uniqueUsername = UUID.randomUUID().toString();
		
		usernameField.sendKeys(uniqueUsername);
		passwordField.sendKeys(DEFAULT_PASSWORD);
		
		this.currentUsername = uniqueUsername;
		
		WebElement registerButton = this.driver.findElement(By.name("register"));
		
		registerButton.click();
		
	}
	
	private void login (String username, String password) {
		this.driver.get("http://localhost/photoalbum/users/login");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement usernameField = this.driver.findElement(By.id("inputUserName"));
		WebElement passwordField = this.driver.findElement(By.id("inputPassword"));
		WebElement loginButton = this.driver.findElement(By.name("login"));
		
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	
	private void createCategory(){
		this.login("admin", "admin");
		this.driver.get("http://localhost/photoalbum/categories/add");
		
		WebElement categoryNameField = this.driver.findElement(By.name("name"));
		String uniqueCategory = UUID.randomUUID().toString();
		this.currentCategory = uniqueCategory;
		categoryNameField.sendKeys();

		WebElement createCategoryButton = this.driver.findElement(By.name("create"));
		createCategoryButton.click();
		this.logout();
		
	}
	
	private void logout(){
		this.driver.get("http://localhost/photoalbum/users/logout");
	}

	private void createAlbum(String categoryName) {
		this.driver.get("http://localhost/photoalbum/albums/add");
		
		WebElement albumNameField = this.driver.findElement(By.name("name"));
		WebElement categoryNameField = this.driver.findElement(By.name("categoryName"));
		WebElement descriptionField = this.driver.findElement(By.name("description"));
		WebElement createButton = this.driver.findElement(By.name("create"));
		
		String uniqueAlbum = UUID.randomUUID().toString();
		String description = "aa";
		
		albumNameField.sendKeys(uniqueAlbum);
		this.currentAlbum = uniqueAlbum;
		categoryNameField.sendKeys(categoryName);
		descriptionField.sendKeys(description);
		
		createButton.click();
	}
	
	public void navigateToAlbum(String albumName) {
		this.driver.get("http://localhost/photoalbum/albums/showall");
		List<WebElement> albumNamesElements = this.driver.findElements(By.className("album-title"));
		
		for (WebElement albumNameElement : albumNamesElements) {
			String text = albumNameElement.getText();
			
			if (albumNameElement.getText().equals(albumName)) {
				albumNameElement.click();
				break;
			}
			
		}
	}

	private void navigateToComment() {
		WebElement commentButton = this.driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div[3]/span[2]/a"));
		commentButton.click();
		
	}
	
	private void createComemnt(String commentText) {
		WebElement commentField = this.driver.findElement(By.name("comment"));
		commentField.sendKeys(commentText);
		
		WebElement createCommentButton = this.driver.findElement(By.name("create"));
		createCommentButton.click();
		
	}
	
	@Test
	public void testComment_validCommentText_expectedCommentBeThere() {
		
		this.createCategory();
		this.register();
		this.login(this.currentUsername, DEFAULT_PASSWORD);
		this.createAlbum(this.currentCategory);
		this.logout();
		this.navigateToAlbum(this.currentAlbum);
		this.navigateToComment();
		this.createComemnt("nov text");
		
		WebElement errorBox = this.driver.findElement(By.xpath("/html/body/div/form/div/div[2]"));
		Assert.assertEquals("You are not logged in", errorBox.getText());
	}
	
	@Test
	public void testComment_loggedUserValidText_expectedCommentAdded() {
		
		this.createCategory();
		this.register();
		this.login(this.currentUsername, DEFAULT_PASSWORD);
		this.createAlbum(this.currentCategory);
		this.navigateToAlbum(this.currentAlbum);
		this.navigateToComment();
		this.createComemnt("nov text");
		
		WebElement commentInfo = this.driver.findElement(By.xpath("/html/body/div/div[4]/ul/li[last()]"));

		DateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String expectedComment = String.format("Comment text: %s, User: %s, Created On: %s", "nov text", 
				currentUsername,  currentDate);
		System.out.println(commentInfo.getText());
		System.out.println(expectedComment);

		Assert.assertEquals(expectedComment.trim(), commentInfo.getText().trim());
	}
	
	@After
	
	public void tearDown() {
		this.driver.quit();
	}
}


