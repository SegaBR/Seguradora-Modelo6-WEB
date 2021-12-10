package br.edu.ifsul.testes;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Teste {
	
	private static ChromeDriver driver;

	@BeforeClass
	public static void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:/c/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setAcceptInsecureCerts(true);
		
		driver = new ChromeDriver(chromeOptions);
	}
	
	@AfterClass
	public static void finalizar() {
		driver.quit();
	}
	
	@Test
	public void testarLogin() {
		
		
		try {
			
			driver.get("https://localhost:8181/Seguradora-Model6-WEB/login.xhtml");
			
			driver.findElement(By.name("frmLogin:usuario")).sendKeys("chris");
			driver.findElement(By.name("frmLogin:senha")).sendKeys("123");
			driver.findElement(By.name("frmLogin:submit")).click();
			
			ExpectedCondition<Boolean> formLoginSerRemovido = ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.id("frmLogin")));
			new WebDriverWait(driver, 5).until(formLoginSerRemovido);
			
			Assert.assertTrue(driver.findElements(By.cssSelector("body > h1")).size() > 0); // Verifica se encontrou o tÃ­tulo da dashboard
		
		} finally {
			
			driver.close();
			
		}
		
	}

}