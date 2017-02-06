package tarefaprincipal;

public class HardwareMock implements Hardware {

	@Override
	public String pegarNumeroDaContaCartao(String numero, CaixaEletronico caixa) {
		if (numero == null)
			throw new HardwareCartaoException();

		ContaCorrente conta = caixa.getServicoRemoto().recuperarConta(numero);
		return conta.getNumero();
	}

	@Override
	public void entregarDinheiro(String numero) {
		if (numero == null)
			throw new HardwareEntregaDinheiroException();
		// System.out.println("Dinheiro entregue.");
	}

	@Override
	public void lerEnvelope(double valor) {
		if (valor < 0)
			throw new HardwareEnvelopeException();
		// System.out.println("Valor: " + valor);
	}

}
