package tarefaprincipal;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.JVM;

@FixMethodOrder(JVM)
public class ArmazenamentoTest {

    private File tmpDir;
    private Armazenamento armazenamento;

    @Before
    public void criarArmazenamento() throws IOException {
        String tmp = getClass().getClassLoader().getResource(".").getPath();
        tmpDir = new File(tmp, "database");

        // Clean up
        tmpDir.deleteOnExit();

        armazenamento = new ArmazenamentoImpl(tmpDir);
    }


    @Test
    public void salvar() {
        // Given
        Usuario usuario = new Usuario("guerra");
        Ponto ponto = new EstrelaPonto();
        int quantidade = 10;

        // When
        armazenamento.salvarPontoDoUsuario(usuario, ponto, quantidade);
        int pontos = armazenamento.recuperarPontosPorUsuario(ponto, usuario);

        // Then
        assertEquals(10, pontos);
    }

    @Test
    public void recuperar() {
        // Given
        Usuario usuario = new Usuario("joao");
        Ponto estrelaPonto = new EstrelaPonto();
        Ponto moedaPonto = new MoedaPonto();

        armazenamento.salvarPontoDoUsuario(usuario, estrelaPonto, 10);
        armazenamento.salvarPontoDoUsuario(usuario, moedaPonto, 8);
        armazenamento.salvarPontoDoUsuario(usuario, estrelaPonto, 5);

        // When
        int pontos = armazenamento.recuperarPontosPorUsuario(estrelaPonto, usuario);

        // Then
        assertEquals(15, pontos);
    }


    @Test
    public void recuperarTodosUsuarios() {
        // Given
        armazenamento.salvarPontoDoUsuario(new Usuario("maria"), new EstrelaPonto(), 10);
        armazenamento.salvarPontoDoUsuario(new Usuario("roberto"), new EstrelaPonto(), 5);
        armazenamento.salvarPontoDoUsuario(new Usuario("sebastião"), new MoedaPonto(), 10);

        // When
        List<Usuario> usuarios = armazenamento.recuperarTodosUsuarios();

        // Then
        assertNotNull(usuarios);
        assertEquals(3, usuarios.size());
    }

    @Test
    public void recuperarPorUsuario() {
        // Given
        Usuario usuario = new Usuario("carlos");
        armazenamento.salvarPontoDoUsuario(usuario, new MoedaPonto(), 30);
        armazenamento.salvarPontoDoUsuario(usuario, new EstrelaPonto(), 12);

        // When
        Map<Ponto, Integer> pontos = armazenamento.recuperarPontosPorUsuario(usuario);

        // Then
        assertNotNull(pontos);
        assertFalse(pontos.isEmpty());
        assertEquals(2, pontos.size());
    }


    @Test
    public void testSeDadosEstaoSendoSalvos() {
        // Given
        Usuario beto = new Usuario("beto");
        armazenamento.salvarPontoDoUsuario(beto, new EstrelaPonto(), 10);

        // When
        List<Usuario> usuarios = new ArmazenamentoImpl(tmpDir).recuperarTodosUsuarios();

        // Then
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }

    @After
    public void cleanUp() {
        for (File file : tmpDir.listFiles()) {
            file.delete();
        }
        tmpDir.delete();
    }

}
