package tarefaprincipal;

import java.util.List;
import java.util.Map;

public interface Armazenamento {

    void salvarPontoDoUsuario(Usuario usuario, Ponto ponto, int quantidade);

    int recuperarPontosPorUsuario(Ponto ponto, Usuario usuario);

    List<Usuario> recuperarTodosUsuarios();

    Map<Ponto, Integer> recuperarPontosPorUsuario(Usuario usuario);
}
