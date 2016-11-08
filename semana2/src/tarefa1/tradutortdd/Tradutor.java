package tarefa1.tradutortdd;

import java.util.HashMap;
import java.util.Map;

public class Tradutor {

	private Map<String, String> traducoes = new HashMap<String, String>();

	public boolean estaVazio() {
		return (this.traducoes.isEmpty());
	}

	public void adicionaTraducao(String palavra, String traducao) {
		if (traducoes.containsKey(palavra)) {
			traducao = traduzir(palavra) + ", " + traducao;
		}
		this.traducoes.put(palavra, traducao);
	}

	public String traduzir(String palavra) {
		return this.traducoes.get(palavra);
	}

	public String traduzirFrase(String frase) {
		String[] palavras = frase.split(" ");
		String fraseTraduzida = "";
		for (String palavra : palavras) {
			String traducao = primeiraTraducao(palavra);
			fraseTraduzida += " " + traducao;
		}
		return fraseTraduzida.trim();
	}

	private String primeiraTraducao(String palavra) {
		String traducao = traduzir(palavra);
		if(traducao.contains(","))
			traducao = traducao.substring(0, traducao.indexOf(","));
		return traducao;
	}

}
