import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteDeAlertas {

	
private WebDriver driver;
	
	
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	@Test
	public void deveInteragirComAlertSimples() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	
		driver.findElement(By.id("Alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		
	
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		
		alert.dismiss();
		
		driver.quit();
	}
	
	
	@Test
	public void deveInteragirComAlertPrompt() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		
		alert.accept();
		
		driver.quit();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
