package tarefaprincipal;

public class UsuarioTipoPontos extends Usuario {

	private int pontos;
	private final String tipo;

	public UsuarioTipoPontos(final String nome, final String tipo, final int pontos) {
		super(nome);
		this.tipo = tipo;
		this.pontos = pontos;
	}

	public int getPontos() {
		return this.pontos;
	}

	public String getTipo() {
		return this.tipo;
	}

	@Override
	public void atualizar(final String dados) {
		this.pontos += Integer.valueOf(dados.split(" ")[2]);
	}

	@Override
	public String toString() {
		return String.format("%s %s %d", getNome(), getTipo(), getPontos());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
				return split[0].equals(getNome()) && split[1].equals(getTipo())
						&& Integer.valueOf(split[2]) == getPontos();
			}
			return false;
		}
		final UsuarioTipoPontos other = (UsuarioTipoPontos) object;
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
