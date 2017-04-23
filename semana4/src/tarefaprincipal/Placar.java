package tarefaprincipal;

import java.util.*;

/**
 * Created by betorcs on 4/21/17.
 */
public class Placar {

    private final Armazenamento armazenamento;

    public Placar(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void registrarPontoParaUsuario(int qtd, Ponto ponto, Usuario usuario) {
        armazenamento.salvarPontoDoUsuario(usuario, ponto, qtd);
    }

    public Map<Ponto, Integer> retornarTodosPontosDoUsuario(Usuario usuario) {
        return armazenamento.recuperarPontosPorUsuario(usuario);
    }

    public List<ParDeValor<Usuario, Integer>> rakingPorPonto(Ponto ponto) {
        List<ParDeValor<Usuario, Integer>> ranking = new ArrayList<>();
        for (Usuario usuario : armazenamento.recuperarTodosUsuarios()) {
            int pontos = armazenamento.recuperarPontosPorUsuario(ponto, usuario);
            ranking.add(new ParDeValor<>(usuario, pontos));
        }

        ranking = ordernaRanking(ranking);

        return ranking;
    }

    private List<ParDeValor<Usuario, Integer>> ordernaRanking(List<ParDeValor<Usuario, Integer>> ranking) {
        Collections.sort(ranking, (l, r) -> {
            if (l.valor2 > r.valor2) return -1;
            else if (l.valor2 < r.valor2) return 1;
            else return 0;
        });
        return ranking;
    }
}
