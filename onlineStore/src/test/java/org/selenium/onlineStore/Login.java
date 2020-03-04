package org.selenium.onlineStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class Login 
{
	WebDriver driver=null;
	Properties prop=null;

	@BeforeClass
	public void open_online_store() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		InputStream file = new FileInputStream("D:/Preetha/Eclipse/Formy_app/src/main/java/utils/login.properties");
		prop = new Properties();
		prop.load(file);
		
		String url = prop.getProperty("Weburl");
		System.out.println(url);
		
	
	}
	
	@Test(groups="login",priority=1)
	public void VerifyTitle()
	{
		//driver.get("http://store.demoqa.com/");
//		String url = prop.getProperty("Weburl");
	//	System.out.println(url);
		
		
		//driver.get(url);
	
		driver.manage().window().maximize();
		String Title = driver.getTitle();
		System.out.println(Title);
		Assert.assertEquals(driver.getTitle(),"RedBook | Login");
		//Assert.assertEquals(driver.getTitle(),"ONLINE STORE | Toolsqa Dummy Test site");
	}
	
	
	@Test(groups="login",priority=2)
	public void LogIn()
	{
		System.out.println("logged in");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("redbook");
		driver.findElement(By.id("company")).sendKeys("RET01");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertEquals(driver.getTitle(),"RedBook       Shortcuts:  F1-Sales Bill  F2-Purchase Bill  F3-Upload Purchase Bill  F4-Returns  F5-Payments  F6-Stocks  F7-Transactions  F8-Medicines  F9-Test Bill  F10-Search  Ctrl+m-Add medicine");
	}

	//check the default tab
	@Test(groups="default",priority=3)
	public void DefaultTab()
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		@SuppressWarnings("unused")
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/li[@id='nav_tab_0']/a/label")));
		String tabname = driver.findElement(By.xpath("//*/li[@id='nav_tab_0']/a/label")).getText();
		System.out.println(tabname);
		Assert.assertEquals(tabname,"Sale(1)");
	}
	
	@Test(groups="default",priority=4)
	public void DefaultPlaceHolder()
	{
		String patient = driver.findElement(By.id("pat")).getAttribute("placeholder");
		System.out.println(patient);
		Assert.assertEquals(patient,"Patient");
		
	}

	@Test(groups="default",priority=5)
	public void DefaultLabel()
	{
		String patLabel = driver.findElement(By.xpath("//*[@id='pat']/following-sibling::label")).getText();
		Assert.assertEquals(patLabel,"[ALT+1]");
		
		System.out.println(patLabel);
	}
	
	@AfterClass
	public void Close()
	{
		driver.quit();
	}
}
