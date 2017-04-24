package tarefaprincipal;

public class UsuarioTipo extends Usuario {

	private final String tipo;

	public UsuarioTipo(final String nome, final String tipo) {
		super(nome);
		this.tipo = tipo;
	}

	public String getTipo() {
		return this.tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.tipo == null) ? 0 : this.tipo.hashCode());
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
		if (getClass() != object.getClass()) {
			if (object instanceof String) {
				final String[] split = ((String) object).split(" ");
				return split[0].equals(getNome()) && split[1].equals(getTipo());
			}
			if (object instanceof UsuarioTipoPontos) {
				final UsuarioTipoPontos user = (UsuarioTipoPontos) object;
				return getNome().equals(user.getNome()) && getTipo().equals(user.getTipo());
			}
			return false;
		}
		final UsuarioTipo other = (UsuarioTipo) object;
		if (this.tipo == null) {
			if (other.tipo != null) {
				return false;
			}
		} else if (!this.tipo.equals(other.tipo)) {
			return false;
		}
		return true;
	}
}
