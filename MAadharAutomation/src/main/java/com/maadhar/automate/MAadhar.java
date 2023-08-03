package com.maadhar.automate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MAadhar {
	

 	private WebDriver driver;
 	private final String baseUrl="http://localhost:4200/login";
 	
 	@BeforeClass
    public void setUp() throws InterruptedException {
    
        driver = new ChromeDriver();
			Thread.sleep(5000);
        driver.manage().window().maximize();
    }
 	
 	@Test
 	public void MAadharTest() throws InterruptedException{
 		
 		driver.get(baseUrl);
 		Thread.sleep(10000);
 		String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
 		driver.findElement(By.id("citizenId")).sendKeys("1000");
        driver.findElement(By.id("password")).sendKeys("9780456421");
        Thread.sleep(5000);
        driver.findElement(By.className("btn-primary")).click();
        System.out.println("User Login successful");
        Thread.sleep(10000);     
        String xpath = "/html/body/app-root/div/app-home/div/div/div[1]/div[2]/div/div[2]/table/tbody/tr[1]/td[2]";
        String actualData = driver.findElement(By.xpath(xpath)).getText();

        // Define the expected data
        String expectedData = "1000";

        // Compare the actual and expected data
        if (actualData.equals(expectedData)) {
            System.out.println("Inside citizen homepage");
        } else {
            System.out.println("Error:" + actualData);
       }
 		
 		Thread.sleep(10000);
        
        driver.get("http://localhost:4200/admin-login");
        Thread.sleep(10000);
        driver.findElement(By.id("adminId")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123@");
        Thread.sleep(10000);
        driver.findElement(By.className("btn-primary")).click();
        System.out.println("Admin login successful");
        Thread.sleep(10000);
        ///html/body/app-root/div/app-admin-home/div/div/h1
        String xpath2="/html/body/app-root/div/app-admin-home/div/div/h1";
        String Actualdt=driver.findElement(By.xpath(xpath2)).getText();
        if(Actualdt.equals("Admin Home")) {
        	System.out.println("Inside admin homepage");
        	
        }
        

        

 	}
}
