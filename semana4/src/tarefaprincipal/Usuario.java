package tarefaprincipal;

public class Usuario {

	private final String nome;

	protected Usuario(final String nome) {
		this.nome = nome;
	}

	public static Usuario criarUsuario(final String nome) {
		return new Usuario(nome);
	}

	public static Usuario criarUsuario(final String nome, final int pontos) {
		return new UsuarioPontos(nome, pontos);
	}

	public static Usuario criarUsuario(final String nome, final String tipo) {
		return new UsuarioTipo(nome, tipo);
	}

	public static Usuario criarUsuario(final String nome, final String tipo, final int pontos) {
		return new UsuarioTipoPontos(nome, tipo, pontos);
	}

	public String getNome() {
		return this.nome;
	}

	public void atualizar(final String dados) {
		// método hook
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Usuario)) {
			if (object instanceof String) {
				final String[] split = ((String) object).split(" ");
				return split[0].equals(getNome());
			}
			return false;
		}
		final Usuario other = (Usuario) object;
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}
		return true;
	}
}
