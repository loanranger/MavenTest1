package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {

	
	WebDriver d;
	public Login(WebDriver driver )
	{
		this.d=driver;
	}
	
	@FindBy(xpath=".//*[@id='user_name']") WebElement userName;
	@FindBy(xpath=".//*[@id='user_password']") WebElement password;
	@FindBy(xpath=".//*[@id='login_button']") WebElement loginButton;
	By logout=By.xpath(".//*[@id='logout_link']");
	
	public void enterUsername(String user)
	{
           userName.sendKeys(user);
	}	
	
	public void enterPassword(String pass)
	{
		password.sendKeys(pass);
	}	
	
	public void clickOnLogin()
	{
		loginButton.click();
	}	
	
	public String getApplicationTitle()
	{
		return d.getTitle();
	}
	
	public void verifyLogOutLink()
	{
		WebDriverWait wait=new WebDriverWait(d,20);
	WebElement ele=	wait.until(ExpectedConditions.presenceOfElementLocated(logout));
	
	String text=ele.getText();
	Assert.assertEquals(text, "Log Out","Log Out link is not verified successfuly");
	}
}