package tarefaprincipal;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GamificacaoIntegrationTest {

	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Placar placar;

	@Before
	public void inicializacao() {
		TestUtils.limparArquivo();

		this.usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		this.usuario2 = Usuario.criarUsuario("Iza", "Vital", 15);
		this.usuario3 = Usuario.criarUsuario("Franca", "Vital", 20);

		final Armazenamento armazenamento = new Armazenamento();
		this.placar = new Placar(armazenamento);
	}

	@Test
	public void registroDosUsuarios() {
		this.placar.registarUsuario(this.usuario1);
		this.placar.registarUsuario(this.usuario2);
		this.placar.registarUsuario(this.usuario3);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals("Paulo Vital 10", linhas.get(0));
		assertEquals("Iza Vital 15", linhas.get(1));
		assertEquals("Franca Vital 20", linhas.get(2));
	}

	@Test
	public void obtemPontuacaoDosUsuarios() {
		this.placar.registarUsuario(this.usuario1);
		this.placar.registarUsuario(this.usuario2);
		this.placar.registarUsuario(this.usuario3);

		final Usuario u1 = Usuario.criarUsuario("Paulo", "Vital");
		final Usuario u2 = Usuario.criarUsuario("Iza", "Vital");
		final Usuario u3 = Usuario.criarUsuario("Franca", "Vital");
		final List<String> p1 = this.placar.obterPontuacao(u1);
		final List<String> p2 = this.placar.obterPontuacao(u2);
		final List<String> p3 = this.placar.obterPontuacao(u3);

		assertEquals("Vital 10", p1.get(0));
		assertEquals("Vital 15", p2.get(0));
		assertEquals("Vital 20", p3.get(0));
	}

	@Test
	public void geraRankingDePontuacao() {
		this.placar.registarUsuario(this.usuario1);
		this.placar.registarUsuario(this.usuario2);
		this.placar.registarUsuario(this.usuario3);

		final String tipo = "Vital";
		final List<String> ranking = this.placar.ranking(tipo);

		assertEquals("Franca 20", ranking.get(0));
		assertEquals("Iza 15", ranking.get(1));
		assertEquals("Paulo 10", ranking.get(2));
	}
}
