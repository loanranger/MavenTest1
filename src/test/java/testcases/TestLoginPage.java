package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.LoginPage;

public class TestLoginPage {
	
	WebDriver d;
	
	@BeforeMethod
	public void setUp()
	{
		 d=BrowserFactory.getBrowser("chrome");
		   
		   d.get(DataProviderFactory.getConfig().getApplicationUrl());
	}

	@Test
   public void verifyLogin()
   {
	   LoginPage login=PageFactory.initElements(d, LoginPage.class);
	   String title=login.getApplicationTitle();
	   //System.out.println("My Application title is :"+title);
	   Assert.assertTrue(title.contains("127.0.0.1"));
	  
	  login.loginApplication(DataProviderFactory.getExcel().getData(0,0, 0),DataProviderFactory.getExcel().getData(0,0, 1));
	  
	  
	  //to verify the login is successful and presence of the log out link......
	  login.verifyLogOutLink();
   }
	
	@AfterMethod
	public void cleanUp()
	{
		 BrowserFactory.closeBrowser(d);
	}

}
