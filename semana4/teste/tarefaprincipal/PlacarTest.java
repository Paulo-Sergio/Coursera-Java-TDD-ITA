package tarefaprincipal;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PlacarTest {

    private MockArmazenamento armazenamento;
    private Placar placar;

    @Before
    public void criaMockEPlacar() {
        armazenamento = new MockArmazenamento();
        placar = new Placar(armazenamento);
    }


    @Test
    public void registrarPontoParaUsuario() {
        // Given
        int qtd = 10;
        Ponto ponto = new EstrelaPonto();
        Usuario usuario = new Usuario("guerra");


        // When
        placar.registrarPontoParaUsuario(qtd, ponto, usuario);

        // Then
        assertEquals(1, armazenamento.getSalvarPontoDoUsuarioIteracoes());
    }

    @Test
    public void retornarTodosPontosDeUmUsuario() {
        // Given
        Usuario usuario = new Usuario("guerra");
        LinkedHashMap<Ponto, Integer> guerraPontos = new LinkedHashMap<>();
        guerraPontos.put(new EstrelaPonto(), 25);
        guerraPontos.put(new MoedaPonto(), 20);

        armazenamento.setPontosPorUsuario(new HashMap<>(Collections.singletonMap(usuario, guerraPontos)));

        // When
        Map<Ponto, Integer> pontos = placar.retornarTodosPontosDoUsuario(usuario);

        // Then
        assertEquals(pontos.size(), 2);
        assertThat(pontos.keySet(), is(guerraPontos.keySet()));
        assertEquals(1, armazenamento.getRecuperarPontosPorUsuarioIteracoes());
    }


    @Test
    public void rakingPorPonto() {
        // Given
        Ponto estrelaPonto = new EstrelaPonto();

        Map<Usuario, Map<Ponto, Integer>> usuarioPontos = new HashMap<>();
        usuarioPontos.put(new Usuario("fernandes"), Collections.singletonMap(estrelaPonto, 19));
        usuarioPontos.put(new Usuario("rodrigo"), Collections.singletonMap(estrelaPonto, 17));
        usuarioPontos.put(new Usuario("guerra"), Collections.singletonMap(estrelaPonto, 25));

        armazenamento.setPontosPorUsuario(usuarioPontos);


        // When
        List<ParDeValor<Usuario, Integer>> ranking =  placar.rakingPorPonto(estrelaPonto);

        // Then
        assertEquals("guerra", ranking.get(0).valor1.getNome());
        assertEquals("fernandes", ranking.get(1).valor1.getNome());
        assertEquals("rodrigo", ranking.get(2).valor1.getNome());

        assertEquals(25L, (long) ranking.get(0).valor2);
        assertEquals(19L, (long) ranking.get(1).valor2);
        assertEquals(17L, (long) ranking.get(2).valor2);

    }


}
