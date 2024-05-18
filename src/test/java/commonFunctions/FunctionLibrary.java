package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil
{

	public static boolean AdminLogin(String user,String pass) throws Throwable
	{
	driver.get(pop.getProperty("Url"));	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(pop.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(pop.getProperty("ObjUser"))).sendKeys(user);
	driver.findElement(By.xpath(pop.getProperty("ObjPass"))).sendKeys(pass);
	driver.findElement(By.xpath(pop.getProperty("ObjLogin"))).click();
	Thread.sleep(2000);
	String Expected = "dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("login success::"+Expected+"  "+Actual,true);
		driver.findElement(By.xpath(pop.getProperty("ObjLogout"))).click();
		return true;
	}
	else
	{
		String errormsg = driver.findElement(By.xpath(pop.getProperty("ObjErrormsg"))).getText();
		driver.findElement(By.xpath(pop.getProperty("ObjOKbutton"))).click();
		Reporter.log(errormsg+"  "+Expected+"  "+Actual,true);
		return false;
	}
	
	}
	
}
