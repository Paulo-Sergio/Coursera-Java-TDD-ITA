package tarefaprincipal;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Armazenamento implements IArmazenamento {

	private static final String ARQUIVO = "banco-dados";
	private static final Path PATH = Paths.get(Armazenamento.ARQUIVO);

	@Override
	public void armazenar(final Usuario usuario) {
		try {

			final List<String> dados;

			if (existeArquivo()) {
				dados = Files.readAllLines(Armazenamento.PATH);
			} else {
				dados = new ArrayList<>();
			}

			if (dados.contains(usuario)) {
				atualizarDados(usuario, dados);
			} else {
				escreverDados(usuario);
			}

		} catch (final IOException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public int recuperarPontos(final Usuario usuario) {
		OptionalInt resultado = OptionalInt.empty();

		if (existeArquivo()) {
			try {
				resultado = Files.lines(Armazenamento.PATH).filter(usuario::equals).map(x -> x.split(" ")[2])
						.mapToInt(Integer::parseInt).findFirst();
			} catch (NumberFormatException | IOException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		return resultado.orElse(0);
	}

	@Override
	public List<Usuario> recuperarUsuariosComPontos() {
		final List<Usuario> usuarios = new ArrayList<>();

		if (existeArquivo()) {
			try {
				for (final String linha : Files.readAllLines(Armazenamento.PATH)) {
					final String[] colunas = linha.split(" ");
					usuarios.add(Usuario.criarUsuario(colunas[0], colunas[1], Integer.valueOf(colunas[2])));
				}
			} catch (final IOException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		return usuarios;
	}

	@Override
	public List<String> recuperarTipos(final Usuario usuario) {
		List<String> resultados = new ArrayList<>();

		if (existeArquivo()) {
			try {
				resultados = Files.lines(Armazenamento.PATH).filter(usuario::equals).map(x -> x.split(" ")[1])
						.collect(Collectors.toList());
			} catch (final IOException exception) {
				throw new RuntimeException(exception.getMessage());
			}
		}

		return resultados;
	}

	private boolean existeArquivo() {
		return Armazenamento.PATH.toFile().exists();
	}

	private void escreverDados(final Usuario usuario) throws IOException {
		final PrintWriter printWriter = new PrintWriter(
				Files.newOutputStream(Armazenamento.PATH, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
		printWriter.println(usuario);
		printWriter.close();
	}

	private void atualizarDados(final Usuario usuario, final List<String> linhas) throws IOException {
		final int indice = linhas.indexOf(usuario);

		usuario.atualizar(linhas.get(indice));
		linhas.remove(indice);
		linhas.add(usuario.toString());

		Files.write(Armazenamento.PATH, linhas, StandardOpenOption.WRITE);
	}
}
