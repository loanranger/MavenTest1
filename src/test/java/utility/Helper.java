package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	public static  String captureScreenshot(WebDriver d,String screenShotName)
	{
		TakesScreenshot ts=(TakesScreenshot)d;
		
		File src= ts.getScreenshotAs(OutputType.FILE);
		
		String dest ="./ScreenShots/"+screenShotName+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to capture screen shot"+e.getMessage());
		}
		
		return dest; 
		
	}

}
