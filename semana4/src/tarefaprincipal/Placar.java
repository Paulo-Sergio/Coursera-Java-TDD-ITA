package tarefaprincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Placar {

	private final IArmazenamento armazenamento;

	public Placar(final IArmazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registarUsuario(final Usuario usuario) {
		this.armazenamento.armazenar(usuario);
	}

	public List<String> obterPontuacao(final Usuario usuario) {
		final ArrayList<String> pontuacao = new ArrayList<>();

		final List<String> tipos = this.armazenamento.recuperarTipos(usuario);
		for (final String tipo : tipos) {
			final Usuario pesquisa = Usuario.criarUsuario(usuario.getNome(), tipo);

			final int pontos = this.armazenamento.recuperarPontos(pesquisa);
			pontuacao.add(String.format("%s %d", tipo, pontos));
		}

		return pontuacao;
	}

	public List<String> ranking(final String tipo) {
		final List<Usuario> usuarios = this.armazenamento.recuperarUsuariosComPontos();
		return usuarios.stream().map(u -> (UsuarioTipoPontos) u).filter(u -> u.getTipo().equals(tipo))
				.sorted((u1, u2) -> u2.getPontos() - u1.getPontos())
				.map(u -> String.format("%s %s", u.getNome(), u.getPontos())).collect(Collectors.toList());
	}
}
