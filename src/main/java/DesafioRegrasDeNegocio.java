import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class DesafioRegrasDeNegocio {

	
private WebDriver driver;
private DSL dsl;

	//Caso não execute, trocar import org.junit.jupiter.api.Test; por import org.junit.Test;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void DeveRealizarCadastroComSucesso() {
		dsl.escrever("elementosForm:nome", "Italo");
		dsl.escrever("elementosForm:sobrenome", "Trindade");
		dsl.clicarRadio("elementosForm:sexo:0");	
		dsl.clicarRadio("elementosForm:comidaFavorita:2");	
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
			
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Italo"));
		Assert.assertEquals("Sobrenome: Trindade", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
		
	}
	
	
	@Test
	public void ValidarCampoNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	
	}
	
	
	@Test
	public void ValidarCampoSobrenomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	
	
	@Test
	public void ValidarCampoSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Texto2");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
	}
	
	@Test
	public void ValidarCampoComidaObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Texto2");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
	
	
	@Test
	public void ValidarCampoEsporteObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Texto2");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
	}
	
}