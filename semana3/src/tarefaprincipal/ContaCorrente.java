package tarefaprincipal;

public class ContaCorrente {

	private int numero;
	private double saldo;

	public ContaCorrente(int numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

}
