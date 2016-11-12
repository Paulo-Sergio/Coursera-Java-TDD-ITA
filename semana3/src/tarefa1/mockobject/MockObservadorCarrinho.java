package tarefa1.mockobject;

import static org.junit.Assert.*;

public class MockObservadorCarrinho implements ObservadorCarrinho {

	private String nomeRecebido;
	private int valorRecebido;
	private boolean darpau = false;

	@Override
	public void produtoAdicionado(String nome, int valor) {
		if (darpau)
			throw new RuntimeException("Problema simulado pelo mock!");
		nomeRecebido = nome;
		valorRecebido = valor;
	}

	public void verificaRecebimentoProduto(String nomeEsperado, int valorEsperado) {
		assertEquals(nomeEsperado, nomeRecebido);
		assertEquals(valorEsperado, valorRecebido);
	}

	public void queroQueVoceDePau() {
		darpau = true;
	}

}
