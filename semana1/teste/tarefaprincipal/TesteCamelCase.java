package tarefaprincipal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Test;

public class TesteCamelCase {

	List<String> resultados;

	@After
	public void limpaArrayCamelCase() {
		if (resultados != null)
			resultados.clear();
	}

	@Test
	public void testUmaPalavra() {
		resultados = CamelCase.converteCamelCase("nome");
		assertEquals("nome", resultados.get(0));
	}

	@Test
	public void testUmaPalavraInicialMaiuscula() {
		resultados = CamelCase.converteCamelCase("Nome");
		assertEquals("nome", resultados.get(0));
	}

	@Test
	public void testNomeComposto() {
		resultados = CamelCase.converteCamelCase("nomeComposto");
		assertEquals("nome", resultados.get(0));
		assertEquals("composto", resultados.get(1));
	}

	@Test
	public void testNomeCompostoComNumeros() {
		resultados = CamelCase.converteCamelCase("recupera10Primeiros");
		assertEquals("recupera", resultados.get(0));
		assertEquals("10", resultados.get(1));
		assertEquals("primeiros", resultados.get(2));
	}

	@Test(expected = ComecaComNumeroException.class)
	public void testComecaComNumero() {
		resultados = CamelCase.converteCamelCase("91Primeiros");
		fail();
		assertEquals("91", resultados.get(0));
		assertEquals("primeiros", resultados.get(1));
	}
	
	@Test(expected = CharacterInvalidoException.class)
	public void testCaractereInvalido() {
		resultados = CamelCase.converteCamelCase("nome#Composto");
		fail();
		assertEquals("nome", resultados.get(0));
	}
	
	@Test(expected = PalavraVaziaException.class)
	public void testPalavraVazia() {
		resultados = CamelCase.converteCamelCase("");
		fail();
		assertEquals("", resultados.get(0));
	}

}
