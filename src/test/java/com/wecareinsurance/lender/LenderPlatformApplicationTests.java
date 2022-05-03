package com.wecareinsurance.lender;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;


/**
 * test for lenderplatform used selenium+junit
 * after all the test case,
 * a account with email: test@unomaha.edu, password: test will be created,
 **/
 


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
class LenderPlatformApplicationTests {

	private WebDriver driver;
	private String url;
	private boolean acceptNextAlert = true;
	
	/**
	 * pre setup for open driver window and set url, this will run before every test.
	 */
	@BeforeEach
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		url = "http://129.146.167.23/lender/login.html";
		//below three line code is to hide chrome window.
		//ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true);
		//driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * login before any other function might be tested.
	 * @throws Exception 
	 */
	public void login() throws Exception{
		driver.get(url);
		Thread.sleep(1500);
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("test@unomaha.edu");
		Thread.sleep(1500);
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("test");
		Thread.sleep(1500);
		driver.findElement(By.id("login")).click();
	}

	/**
	 * test register function create a account email: test@unomaha.edu, password: test.
	 * @throws Exception 
	 */
	@Test
	public void test01() throws Exception {
		driver.get(url);
		driver.findElement(By.linkText("No account? Register")).click();
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("test@unomaha.edu");
		Thread.sleep(1500);
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys("test");
		Thread.sleep(1500);
		driver.findElement(By.id("phone")).click();
		driver.findElement(By.id("phone")).sendKeys("1111111111");
		Thread.sleep(1500);
		driver.findElement(By.id("phone")).click();
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("test");
		Thread.sleep(1500);
		driver.findElement(By.id("register")).click();
		Thread.sleep(1500);
	}

	/**
	 * test add lender function via home add lender button.
	 * @throws Exception 
	 */
	@Test
	public void test02() throws Exception{
		login();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[contains(text(),'Add New\n                            Lender')]")).click();
		driver.get("http://129.146.167.23/lender/addLender.html");
		Thread.sleep(1500);
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys("lender 1");
		Thread.sleep(1500);
		driver.findElement(By.id("type")).sendKeys("lineholder");
		Thread.sleep(1500);
		driver.findElement(By.id("a1")).click();
		driver.findElement(By.id("a1")).sendKeys("6001 dodge street");
		Thread.sleep(1500);
		driver.findElement(By.id("city")).click();
		driver.findElement(By.id("city")).sendKeys("omaha");
		Thread.sleep(1500);
		driver.findElement(By.id("state")).click();
		new Select(driver.findElement(By.id("state"))).selectByVisibleText("Nebraska");
		Thread.sleep(1500);
		driver.findElement(By.id("zip")).click();
		driver.findElement(By.id("zip")).sendKeys("68182");
		Thread.sleep(1500);
		driver.findElement(By.id("deductible")).sendKeys("500");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1500);
	}

	/**
	 * test add lender function via add customer page add lender button.
	 * @throws Exception 
	 */
	@Test
	public void test03() throws Exception{
		login();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[contains(text(),'Add\n                            New Customer')]")).click();
		driver.get("http://129.146.167.23/lender/addC.html");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@onclick=\"location.href='addLender.html?creating';\"]")).click();
		driver.get("http://129.146.167.23/lender/addLender.html?creating");
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys("lender 2");
		Thread.sleep(1500);
		driver.findElement(By.id("type")).sendKeys("Additional Insuranced");
		Thread.sleep(1500);
		driver.findElement(By.id("a1")).click();
		driver.findElement(By.id("a1")).sendKeys("6001 dodge street");
		Thread.sleep(1500);
		driver.findElement(By.id("city")).click();
		driver.findElement(By.id("city")).sendKeys("omaha");
		Thread.sleep(1500);
		driver.findElement(By.id("state")).click();
		new Select(driver.findElement(By.id("state"))).selectByVisibleText("Nebraska");
		Thread.sleep(1500);
		driver.findElement(By.id("zip")).click();
		driver.findElement(By.id("zip")).sendKeys("68182");
		Thread.sleep(1500);
		driver.findElement(By.id("deductible")).sendKeys("1200");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.get("http://129.146.167.23/lender/home.html");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[contains(text(),'Lender\n                            Lists')]")).click();
		driver.get("http://129.146.167.23/lender/lenderList.html");
		Thread.sleep(1500);
		driver.findElement(By.linkText("lender 2")).click();
		Thread.sleep(1500);
	}

	/**
	 * add, email, delete customer.
	 */
	@Test
	  public void test04() throws Exception {
		login();
		Thread.sleep(1500);
	    driver.get("http://129.146.167.23/lender/home.html");
	    driver.findElement(By.xpath("//a[contains(text(),'Add\n                            New Customer')]")).click();
	    driver.get("http://129.146.167.23/lender/addC.html");
	    Thread.sleep(1500);
	    driver.findElement(By.id("ln")).click();
	    driver.findElement(By.id("ln")).clear();
	    driver.findElement(By.id("ln")).sendKeys("Smith");
	    driver.findElement(By.id("fn")).click();
	    driver.findElement(By.id("fn")).clear();
	    driver.findElement(By.id("fn")).sendKeys("John");
	    driver.findElement(By.id("a1")).click();
	    driver.findElement(By.id("a1")).clear();
	    driver.findElement(By.id("a1")).sendKeys("6907 Oak Plaza");
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("Omaha");
	    driver.findElement(By.id("state")).click();
	    new Select(driver.findElement(By.id("state"))).selectByVisibleText("Nebraska");
	    driver.findElement(By.id("zip")).click();
	    driver.findElement(By.id("zip")).clear();
	    driver.findElement(By.id("zip")).sendKeys("68106");
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@gmail.com");
	    driver.findElement(By.id("phone")).click();
	    driver.findElement(By.id("phone")).clear();
	    driver.findElement(By.id("phone")).sendKeys("(402) 222-2222");
	    driver.findElement(By.id("vin")).click();
	    driver.findElement(By.id("vin")).clear();
	    driver.findElement(By.id("vin")).sendKeys("1HGCM66594A076065");
	    Thread.sleep(1500);
	    driver.findElement(By.id("lender")).click();
	    new Select(driver.findElement(By.id("lender"))).selectByVisibleText("lender 1 deductible: $500");
	    Thread.sleep(1500);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(1500);
	    Alert alt = driver.switchTo().alert();
        alt.accept();
	    Thread.sleep(1500);
	    driver.findElement(By.xpath("//button/span")).click();
	    Thread.sleep(1500);
	    assertEquals("email sent", closeAlertAndGetItsText());
	    Thread.sleep(1500);
	    driver.findElement(By.xpath("//a[2]/span")).click();
	    Thread.sleep(1500);
	    assertEquals("Customer deleted successfully: deleted", closeAlertAndGetItsText());
	    Thread.sleep(1500);
	    driver.get("http://129.146.167.23/lender/list.html");
	    Thread.sleep(1500);
	  }
	
	/**
	 * test re-login after change email to test2@unomaha and password to test2 in profile page.
	 * @throws Exception 
	 */
	@Test
	public void test05() throws Exception {
		login();
		Thread.sleep(1500);
		driver.findElement(By.linkText("Profile")).click();
		driver.get("http://129.146.167.23/lender/profile.html");
		Thread.sleep(1500);
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("test2@unomaha.edu");
		Thread.sleep(1500);
		driver.findElement(By.id("pwd")).click();
		driver.findElement(By.id("pwd")).sendKeys("test2");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.get("http://129.146.167.23/lender/login.html");
		Thread.sleep(1500);
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("test2@unomaha.edu");
		Thread.sleep(1500);
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("test2");
		Thread.sleep(1500);
		driver.findElement(By.id("login")).click();
		Thread.sleep(1500);
	}

	/**
	 * close the window, this will run after every test.
	 */
	@AfterEach
	public void quit(){
		driver.quit();
	}
	
	private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	
}