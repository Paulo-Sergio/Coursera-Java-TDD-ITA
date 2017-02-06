package tarefaprincipal;

import java.util.HashMap;
import java.util.Map;

public class ServicoRemotoMock implements ServicoRemoto{

	private Map<String, ContaCorrente> contas = new HashMap<>();
	
	public void adicionarConta(ContaCorrente conta) {
		this.contas.put(conta.getNumero(), conta);
	}

	@Override
	public ContaCorrente recuperarConta(String numeroConta) {
		return this.contas.get(numeroConta);
	}

	@Override
	public void persistirConta(ContaCorrente conta) {
		this.contas.put(conta.getNumero(), conta);
	}
}
