package tarefaprincipal;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArmazenamentoTest {

	private IArmazenamento armazenamento;

	@Before
	public void inicializacao() {
		TestUtils.limparArquivo();
		this.armazenamento = new Armazenamento();
	}

	@Test
	public void armazenarUmUsuario() {
		final Usuario usuario = Usuario.criarUsuario("Paulo", "Vital", 10);

		this.armazenamento.armazenar(usuario);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals(1, linhas.size());
		assertEquals("Paulo Vital 10", linhas.get(0));
	}

	@Test
	public void armazenarUmUsuarioEmDoisTipos() {
		final Usuario usuarioVital = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuarioPoder = Usuario.criarUsuario("Paulo", "Poder", 10);

		this.armazenamento.armazenar(usuarioVital);
		this.armazenamento.armazenar(usuarioPoder);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals(2, linhas.size());
		assertEquals("Paulo Vital 10", linhas.get(0));
		assertEquals("Paulo Poder 10", linhas.get(1));
	}

	@Test
	public void atualizarPontosDeUmTipo() {
		final Usuario usuarioVital = Usuario.criarUsuario("Paulo", "Vital", 10);

		this.armazenamento.armazenar(usuarioVital);
		this.armazenamento.armazenar(usuarioVital);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals(1, linhas.size());
		assertEquals("Paulo Vital 20", linhas.get(0));
	}

	@Test
	public void atualizarPontosDeDoisTipos() {
		final Usuario usuarioVital = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuarioPoder = Usuario.criarUsuario("Paulo", "Poder", 10);

		this.armazenamento.armazenar(usuarioVital);
		this.armazenamento.armazenar(usuarioVital);
		this.armazenamento.armazenar(usuarioPoder);
		this.armazenamento.armazenar(usuarioPoder);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals(2, linhas.size());
		assertEquals("Paulo Vital 20", linhas.get(0));
		assertEquals("Paulo Poder 20", linhas.get(1));
	}

	@Test
	public void atualizarOsPontosDeDoisUsuarios() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);
		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);

		final List<String> linhas = TestUtils.lerLinhas();

		assertEquals(2, linhas.size());
		assertEquals("Paulo Vital 20", linhas.get(0));
		assertEquals("Franca Vital 20", linhas.get(1));
	}

	@Test
	public void recuperarPontosPorTipo() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);

		final Usuario consulta = Usuario.criarUsuario("Paulo", "Vital");
		final int resultado = this.armazenamento.recuperarPontos(consulta);

		assertEquals(10, resultado);
	}

	@Test
	public void recuperarPontosDeTipoInexistente() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);

		final Usuario consulta = Usuario.criarUsuario("Pedro", "Vital");
		final int resultado = this.armazenamento.recuperarPontos(consulta);

		assertEquals(0, resultado);
	}

	@Test
	public void recuperarUsuariosComPontos() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);

		final List<Usuario> usuarios = this.armazenamento.recuperarUsuariosComPontos();

		assertEquals(2, usuarios.size());
		assertEquals("Paulo", usuarios.get(0).getNome());
		assertEquals("Franca", usuarios.get(1).getNome());
	}

	@Test
	public void recuperarUsuariosEmBancoVazio() {
		final List<Usuario> usuarios = this.armazenamento.recuperarUsuariosComPontos();

		assertEquals(0, usuarios.size());
	}

	@Test
	public void recuperarTiposDeUmUsuario() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Paulo", "Poder", 10);
		final Usuario usuario3 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);
		this.armazenamento.armazenar(usuario3);

		final Usuario consulta = Usuario.criarUsuario("Paulo");
		final List<String> tipos = this.armazenamento.recuperarTipos(consulta);

		assertEquals(2, tipos.size());
		assertEquals("Vital", tipos.get(0));
		assertEquals("Poder", tipos.get(1));
	}

	@Test
	public void recuperarTiposDeUmUsuarioInexistente() {
		final Usuario usuario1 = Usuario.criarUsuario("Paulo", "Vital", 10);
		final Usuario usuario2 = Usuario.criarUsuario("Paulo", "Poder", 10);
		final Usuario usuario3 = Usuario.criarUsuario("Franca", "Vital", 10);

		this.armazenamento.armazenar(usuario1);
		this.armazenamento.armazenar(usuario2);
		this.armazenamento.armazenar(usuario3);

		final Usuario consulta = Usuario.criarUsuario("Pedro");
		final List<String> tipos = this.armazenamento.recuperarTipos(consulta);

		assertEquals(0, tipos.size());
	}
}
