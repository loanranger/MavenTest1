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

public class VerifyLoginPageTitle {
	
	WebDriver d;
	
	@BeforeMethod
	public void setUp()
	{
		 d=BrowserFactory.getBrowser("firefox");
		   
		   d.get(DataProviderFactory.getConfig().getApplicationUrl());
	}

	@Test
   public void verifyLoginPageTitle()
   {
	   LoginPage login=PageFactory.initElements(d, LoginPage.class);
	   String title=login.getApplicationTitle();
	   //System.out.println("My Application title is :"+title);
	   Assert.assertTrue(title.contains("127.0.0.1"));
	  
	      
   }
	
	@AfterMethod
	public void cleanUp()
	{
		 BrowserFactory.closeBrowser(d);
	}

}
