package tarefaprincipal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlacarTest {

	private MockArmazenamento mock;
	private Placar placar;

	@Before
	public void inicializacao() {
		this.mock = new MockArmazenamento();
		this.placar = new Placar(this.mock);
	}

	@Test
	public void registraUmTipoDeUsuario() {
		final Usuario usuario = Usuario.criarUsuario("Paulo", "Vital", 10);

		this.placar.registarUsuario(usuario);

		final boolean resultado = this.mock.verificaEntradaDeDados("Paulo", "Vital", 10);
		assertTrue(resultado);
	}

	@Test
	public void retornaOsPontosDeUmUsuario() {
		final Usuario usuario = Usuario.criarUsuario("Paulo");
		final List<Usuario> usuarios = Arrays.asList(Usuario.criarUsuario("Paulo", "Vital", 10),
				Usuario.criarUsuario("Paulo", "Poder", 10), Usuario.criarUsuario("Pedro", "Vital", 10),
				Usuario.criarUsuario("Jose", "Energia", 10), Usuario.criarUsuario("Iza", "Especial", 10));

		this.mock.setarUsuarios(usuarios);
		final List<String> pontuacao = this.placar.obterPontuacao(usuario);

		assertEquals(2, pontuacao.size());
		assertEquals("Vital 10", pontuacao.get(0));
		assertEquals("Poder 10", pontuacao.get(1));
	}

	@Test
	public void retornaOsPontosDeUmUsuarioInexistente() {
		final Usuario usuario = Usuario.criarUsuario("Juliana");
		final List<Usuario> usuarios = Arrays.asList(Usuario.criarUsuario("Paulo", "Vital", 10),
				Usuario.criarUsuario("Paulo", "Poder", 10), Usuario.criarUsuario("Pedro", "Vital", 10),
				Usuario.criarUsuario("Jose", "Energia", 10), Usuario.criarUsuario("Iza", "Especial", 10));

		this.mock.setarUsuarios(usuarios);
		final List<String> pontuacao = this.placar.obterPontuacao(usuario);

		assertEquals(0, pontuacao.size());
	}

	@Test
	public void retornaRankingDoTipoVitalOrdenadoPorPontos() {
		final String tipo = "Vital";
		final List<Usuario> usuarios = Arrays.asList(Usuario.criarUsuario("Paulo", "Vital", 30),
				Usuario.criarUsuario("Paulo", "Poder", 10), Usuario.criarUsuario("Pedro", "Vital", 10),
				Usuario.criarUsuario("Jose", "Energia", 10), Usuario.criarUsuario("Iza", "Vital", 25));

		this.mock.setarUsuarios(usuarios);
		final List<String> ranking = this.placar.ranking(tipo);

		assertEquals(3, ranking.size());
		assertEquals("Paulo 30", ranking.get(0));
		assertEquals("Iza 25", ranking.get(1));
		assertEquals("Pedro 10", ranking.get(2));
	}
}
