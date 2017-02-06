package tarefaprincipal;

public class CaixaEletronico {

	private Hardware hardware;
	private ServicoRemoto servicoRemoto;

	public String sacar(int valorSacado, String numeroConta) {
		ContaCorrente conta = this.servicoRemoto.recuperarConta(numeroConta);

		if (conta.getSaldo() >= valorSacado) {
			conta.setSaldo(conta.getSaldo() - valorSacado);
			this.servicoRemoto.persistirConta(conta);
			this.hardware.entregarDinheiro(numeroConta);
			return "Saque realizado com sucesso, retire o seu dinheiro!";
		}

		return "Saldo insuficiente para realizar o saque!";
	}

	public String depositar(int valorDepositado, String numeroConta) {
		ContaCorrente conta = this.servicoRemoto.recuperarConta(numeroConta);
		this.hardware.lerEnvelope(valorDepositado);
		conta.setSaldo(conta.getSaldo() + valorDepositado);
		this.servicoRemoto.persistirConta(conta);

		return "Depósito recebido com sucesso!";
	}

	public String saldo(String numero) {
		return "O saldo é R$ " + this.servicoRemoto.recuperarConta(numero).getSaldo();
	}

	public void addHardwareMock(HardwareMock hardwareMock) {
		this.hardware = hardwareMock;
	}

	public void addServicoRemotoMock(ServicoRemotoMock servicoRemotoMock) {
		this.servicoRemoto = servicoRemotoMock;
	}

	public String logar(String numeroConta) {
		if (this.hardware.pegarNumeroDaContaCartao(numeroConta, this) != null)
			return "Usuário Autenticado Corretamente";
		return null;
	}

	public ServicoRemoto getServicoRemoto() {
		return this.servicoRemoto;
	}

	public Hardware getHardware() {
		return this.hardware;
	}
}
