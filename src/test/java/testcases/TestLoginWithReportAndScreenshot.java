package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.LoginPage;

public class TestLoginWithReportAndScreenshot {
	
	WebDriver d;
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeMethod
	public void setUp()
	{
		report= new ExtentReports("./Reports/LoginpageReport.html",true);
		logger = report.startTest("TestLogin");
		
		 d=BrowserFactory.getBrowser("firefox");
		   
		   d.get(DataProviderFactory.getConfig().getApplicationUrl());
	logger.log(LogStatus.INFO, "Application is started running");
	}

	@Test
   public void verifyLogin()
   {
	   LoginPage login=PageFactory.initElements(d, LoginPage.class);
	   String title=login.getApplicationTitle();
	   //System.out.println("My Application title is :"+title);
	   Assert.assertTrue(title.contains("127.0.0.1"));
	   logger.log(LogStatus.PASS, "Login Page is loaded and verified successfully");
	   
	   login.loginApplication(DataProviderFactory.getExcel().getData(0,0, 0),DataProviderFactory.getExcel().getData(0,0, 1));
	  logger.log(LogStatus.INFO, "click on Login button");
	  
	  //to verify the login is successful and presence of the log out link......
	  login.verifyLogOutLink();
	  
	  // to take screenshot after any validation
	 // logger.log(LogStatus.INFO, logger.addScreenCapture(utility.Helper.captureScreenshot(d, "Validation2")));
	  
	  // to fail the test case
	//  Assert.assertTrue(false);
	  
	  logger.log(LogStatus.PASS, "LogOut link is present");
   }
	
	@AfterMethod
	public void cleanUp(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String path=utility.Helper.captureScreenshot(d, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}
		 BrowserFactory.closeBrowser(d);
		 report.endTest(logger);
		 report.flush();
	}
}
