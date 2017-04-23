package tarefaprincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by betorcs on 4/21/17.
 */
public class MockArmazenamento implements Armazenamento {
    private int salvarPontoDoUsuarioIteracoes = 0;
    private int recuperarPontosPorUsuarioIteracoes = 0;
    private Map<Usuario, Map<Ponto, Integer>> pontosPorUsuario;

    @Override
    public void salvarPontoDoUsuario(Usuario usuario, Ponto ponto, int quantidade) {
        salvarPontoDoUsuarioIteracoes++;
    }

    @Override
    public int recuperarPontosPorUsuario(Ponto ponto, Usuario usuario) {
        return pontosPorUsuario.get(usuario).get(ponto);
    }

    @Override
    public List<Usuario> recuperarTodosUsuarios() {
        return new ArrayList<>(pontosPorUsuario.keySet());
    }

    @Override
    public Map<Ponto, Integer> recuperarPontosPorUsuario(Usuario usuario) {
        recuperarPontosPorUsuarioIteracoes++;
        return pontosPorUsuario.get(usuario);
    }

    public int getSalvarPontoDoUsuarioIteracoes() {
        return salvarPontoDoUsuarioIteracoes;
    }

    public int getRecuperarPontosPorUsuarioIteracoes() {
        return recuperarPontosPorUsuarioIteracoes;
    }

    public void setPontosPorUsuario(Map<Usuario, Map<Ponto, Integer>> pontosPorUsuario) {
        this.pontosPorUsuario = pontosPorUsuario;
    }
}
