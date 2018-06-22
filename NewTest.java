package com.qait.automation.MavenTatoc;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.util.Cookie;

import junit.framework.Assert;

public class NewTest {
	WebDriver driver;
	public NewTest()
	{
		String exepath = "/home/qainfotech/Downloads/chromedriver";
		System.setProperty("webdriver.chrome.driver",exepath);
		driver=new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc/");
		
	}
  @Test
  public void Basiccourse() 
  {
	
	  Assert.assertEquals(driver.findElement(By.linkText("Basic Course")).isDisplayed(), true);
	  driver.findElement(By.linkText("Basic Course")).click();
	  Assert.assertEquals("Grid Gate - Basic Course - T.A.T.O.C", driver.getTitle());
	  
  }
  
  @Test(dependsOnMethods = {"Basiccourse"})
  public void Grid()
  {
	  
	  Assert.assertEquals(driver.findElement(By.className("greenbox")).isDisplayed(), true);
	  driver.findElement(By.className("greenbox")).click();
	  Assert.assertEquals("Frame Dungeon - Basic Course - T.A.T.O.C",driver.getTitle());
  }
 @Test(dependsOnMethods= {"Grid"})
  public void frame()
  { Assert.assertEquals(driver.findElement(By.id("main")).isDisplayed(),true);
     driver.switchTo().frame("main");
     //System.out.print(driver.getCurrentUrl());
     Assert.assertEquals(driver.findElement(By.id("answer")).isDisplayed(),true);
     String box1=driver.findElement(By.id("answer")).getAttribute("class");
     System.out.print(box1);
     String box2="";
     while(! box2.equals(box1))
     {
    	 Assert.assertEquals(driver.findElement(By.linkText("Repaint Box 2")).isDisplayed(),true );
    	 System.out.print(driver.findElement(By.linkText("Repaint Box 2")).getTagName());
    	 driver.findElement(By.linkText("Repaint Box 2")).click();
    	 driver.switchTo().frame(0);
    	 Assert.assertEquals(driver.findElement(By.id("answer")).isDisplayed(),true);
    	 box2=driver.findElement(By.id("answer")).getAttribute("class");
    	 driver.switchTo().parentFrame();
    	 
    	 
    	
    	 
     
     }
     Assert.assertTrue(driver.findElement(By.linkText("Proceed")).isDisplayed());
     driver.findElement(By.linkText("Proceed")).click();
     Assert.assertEquals("Drag - Basic Course - T.A.T.O.C",driver.getTitle());
     
  }
 @Test(dependsOnMethods= {"frame"})
public void drag()
{
	 Assert.assertEquals(driver.findElement(By.id("dragbox")).isDisplayed(),true);
	 Assert.assertEquals(driver.findElement(By.id("dragbox")).isDisplayed(), true);
	 //WebDriver drag= (WebDriver) driver.findElement(By.id("dragbox"));
	 WebElement drop = driver.findElement(By.id("dropbox"));
	 WebElement drag = driver.findElement(By.id("dragbox"));
	 Actions act=new Actions(driver);
	 act.dragAndDrop(drag, drop).build().perform();;
	// Assert.assertEquals("position: relative; left: 28px; top: -82px;", drag.getAttribute("style"));
	 
	 Assert.assertEquals(driver.findElement(By.linkText("Proceed")).isDisplayed(),true);
	 driver.findElement(By.linkText("Proceed")).click();
	 
}
 @Test(dependsOnMethods= {"drag"})
 public void pop()
 {
	 Assert.assertEquals("Windows - Basic Course - T.A.T.O.C", driver.getTitle());
     Assert.assertEquals(driver.findElement(By.linkText("Launch Popup Window")).isDisplayed(), true);
     driver.findElement(By.linkText("Launch Popup Window")).click();
     ArrayList windowsList=new ArrayList(driver.getWindowHandles());
     String windows1= ((String)windowsList.get(1));
     String windows2= ((String)windowsList.get(0));
     driver.switchTo().window(windows1);
     Assert.assertEquals("Popup - Basic Course - T.A.T.O.C", driver.getTitle());
     WebElement ele=driver.findElement(By.id("name"));
     ele.sendKeys("shivam chopra");
    // Assert.assertEquals("shivam chopra", ele.getText());
     Assert.assertEquals(driver.findElement(By.id("submit")).isDisplayed(),true);
     driver.findElement(By.id("submit")).click();
     driver.switchTo().window(windows2);
     Assert.assertEquals("Windows - Basic Course - T.A.T.O.C", driver.getTitle());
     Assert.assertEquals(driver.findElement(By.linkText("Proceed")).isDisplayed(),true);
     driver.findElement(By.linkText("Proceed")).click();
     Assert.assertEquals("Cookie Handling - Basic Course - T.A.T.O.C", driver.getTitle()); 
 
  
 }
 @Test(dependsOnMethods= {"pop"})
 public void cookie()
 {
	 //
	 Assert.assertEquals(driver.findElement(By.linkText("Generate Token")).isDisplayed(),true);
	 driver.findElement(By.linkText("Generate Token")).click();
	 Assert.assertEquals(driver.findElement(By.id("token")).isDisplayed(), true);	
		String Token = driver.findElement(By.id("token")).getText();
     String substring1=Token.substring(7);
     Cookie name = new Cookie("Token", substring1);
		driver.manage().addCookie(name);
		Assert.assertEquals(driver.findElement(By.linkText("Proceed")).isDisplayed(), true);
driver.findElement(By.linkText("Proceed")).click();
Assert.assertEquals("End - T.A.T.O.C", driver.getTitle()); 

 }
  
}
