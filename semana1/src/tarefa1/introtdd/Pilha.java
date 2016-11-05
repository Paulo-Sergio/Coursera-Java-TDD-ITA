package tarefa1.introtdd;

public class Pilha {

	private Object[] elementos;
	private int quantidade = 0;

	public Pilha(int maximo) {
		elementos = new Object[maximo];
	}

	public boolean estaVazia() {
		return (this.quantidade == 0);
	}

	public int tamanho() {
		return this.quantidade;
	}

	public void empilha(Object elemento) {
		if(this.quantidade == elementos.length)
			throw new PilhaCheiaException("Pilha já esta cheia");
		this.elementos[quantidade] = elemento;
		this.quantidade++;
	}

	public Object topo() {
		return this.elementos[quantidade - 1];
	}

	public Object desempilha() {
		if (estaVazia())
			throw new PilhaVaziaException("Não é possivel desempilhar");
		Object topo = topo();
		this.quantidade--;
		return topo;
	}

}
