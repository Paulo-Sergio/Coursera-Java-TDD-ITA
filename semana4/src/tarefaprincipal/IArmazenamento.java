package tarefaprincipal;

import java.util.List;

public interface IArmazenamento {

	void armazenar(Usuario usuario);

	int recuperarPontos(Usuario usuario);

	List<Usuario> recuperarUsuariosComPontos();

	List<String> recuperarTipos(Usuario usuario);
}
