package tarefaprincipal;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

	private static final Path PATH = Paths.get("banco-dados");

	public static List<String> lerLinhas() {
		if (existeArquivo()) {
			try {
				return Files.readAllLines(TestUtils.PATH);
			} catch (final IOException exception) {
				fail("Erro ao ler o banco de dados:" + exception.getMessage());
			}
		}

		return new ArrayList<>();
	}

	public static void limparArquivo() {
		if (existeArquivo()) {
			try (BufferedWriter writer = Files.newBufferedWriter(TestUtils.PATH,
					StandardOpenOption.TRUNCATE_EXISTING)) {
				writer.write("");
			} catch (final IOException exception) {
				fail("Erro ao ler o banco de dados:" + exception.getMessage());
			}
		}
	}

	private static boolean existeArquivo() {
		return TestUtils.PATH.toFile().exists();
	}
}
