package tarefaprincipal;

public class UsuarioPontos extends Usuario {

	private final int pontos;

	public UsuarioPontos(final String nome, final int pontos) {
		super(nome);
		this.pontos = pontos;
	}

	public int getPontos() {
		return this.pontos;
	}
}
