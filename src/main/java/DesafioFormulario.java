import java.util.List;

import com.sun.tools.javac.util.Assert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioFormulario {

	
	@Test
	public void ResolucaoDesafio() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Italo");
		Assert.assertEquals("Italo", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Trindade");
		Assert.assertEquals("Trindade", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		Assert.assertEquals("M", driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value"));
		
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		Assert.assertEquals("pizza", driver.findElement(By.id("elementosForm:comidaFavorita:2")).getAttribute("value"));
		
		
		WebElement escolaridade = driver.findElement(By.name("elementosForm:escolaridade"));
		Select combo = new Select(escolaridade);
		combo.selectByVisibleText("Superior");
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
		
		
		
		WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
		Select esporte = new Select(esportes);
		esporte.selectByVisibleText("Natacao");
		esporte.selectByVisibleText("Futebol");
		
		List<WebElement> allSelectedOptions = esporte.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Italo"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Trindade"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao Futebol", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit();
	}
}
