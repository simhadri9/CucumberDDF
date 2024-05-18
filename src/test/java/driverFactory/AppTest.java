package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath = "./FileInput/LoginData.xlsx";
	String outputpath = "./FileOutput/DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void stsrtTest() throws Throwable
	{
		report = new ExtentReports("./target/ExtentReports/Login.html");
		
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("Login");
		Reporter.log("no of rows::"+rc,true);
		for(int i=1;i<=rc;i++)
		
		{
			logger = report.startTest("validate Login");
			logger.assignAuthor("smd");
			
			String username = xl.getCellData("Login",i,0);
			String password = xl.getCellData("Login",i,1);
			logger.log(LogStatus.INFO,username+"-------"+password);
			boolean res = FunctionLibrary.AdminLogin(username, password);
			if(res)
			{
				xl.setCellData("Login",i,2,"valid credentials",outputpath);
				xl.setCellData("Login",i,3,"pass",outputpath);
				logger.log(LogStatus.PASS,"valid username and password");
			}
			else 
			{
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/iterations/"+i+"Loginpage.png"));
				xl.setCellData("Login",i,2,"invalid credentials",outputpath);
				xl.setCellData("Login",i,3,"fail",outputpath);
				logger.log(LogStatus.FAIL,"Invalid username and password");
				
				
			}
			report.endTest(logger);
			report.flush();
		}
		
		
	}

}
