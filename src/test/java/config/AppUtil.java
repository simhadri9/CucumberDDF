 package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static Properties pop;
public static WebDriver driver;
@BeforeTest
public static void setUp() throws Throwable, IOException
{
	pop = new Properties();
	pop.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(pop.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	else if(pop.getProperty("Browser").equalsIgnoreCase("firefox"))
		
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	else 
	{
		Reporter.log("Browser value is not matching",true);
	}
	
}

public static void tearDown()
{
	driver.quit();
}
}
