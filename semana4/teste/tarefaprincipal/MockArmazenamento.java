package tarefaprincipal;

import java.util.List;
import java.util.stream.Collectors;

public class MockArmazenamento implements IArmazenamento {

	private Usuario usuario;
	private List<Usuario> usuarios;

	@Override
	public void armazenar(final Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int recuperarPontos(final Usuario usuario) {
		return this.usuarios.stream().filter(usuario::equals).mapToInt(u -> Integer.valueOf(u.toString().split(" ")[2]))
				.findFirst().orElse(0);
	}

	@Override
	public List<Usuario> recuperarUsuariosComPontos() {
		return this.usuarios;
	}

	@Override
	public List<String> recuperarTipos(final Usuario usuario) {
		return this.usuarios.stream().filter(usuario::equals).map(u -> u.toString().split(" ")[1])
				.collect(Collectors.toList());
	}

	public boolean verificaEntradaDeDados(final String nome, final String tipo, final int pontos) {
		return this.usuario.equals(Usuario.criarUsuario(nome, tipo, pontos));
	}

	public void setarUsuarios(final List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
