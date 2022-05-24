import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteDeFramesEJanelas {

	
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
	public void deveInteragirComComFrames() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg );
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	
	
	
	
	}
	
	
	@Test
	public void deveInteragirComJanelas() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window(windowHandle);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo!");
		 

	}
	
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		
		
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		driver.quit();
	}
}
