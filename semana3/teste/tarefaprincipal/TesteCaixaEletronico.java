package tarefaprincipal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteCaixaEletronico {

	private CaixaEletronico caixaEletronico;
	private ServicoRemotoMock servicoRemotoMock;
	private HardwareMock hardwareMock;

	@Before
	public void init() {
		caixaEletronico = new CaixaEletronico();
		servicoRemotoMock = new ServicoRemotoMock();
		hardwareMock = new HardwareMock();

		servicoRemotoMock.adicionarConta(new ContaCorrente("456", 500));
		servicoRemotoMock.adicionarConta(new ContaCorrente("789", 900));
	}

	@Test
	public void testRealizarLogin() {
		caixaEletronico.addHardwareMock(this.hardwareMock);
		caixaEletronico.addServicoRemotoMock(this.servicoRemotoMock);
		assertEquals("Usuário Autenticado Corretamente", caixaEletronico.logar("456"));
	}

	@Test
	public void testRetornandoSaldo() {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		assertEquals("O saldo é R$ 900.0", caixaEletronico.saldo("789"));
	}

	@Test
	public void testSacarDinheiro() {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		ContaCorrente conta = caixaEletronico.getServicoRemoto().recuperarConta("456");

		assertEquals("Saque realizado com sucesso, retire o seu dinheiro!", caixaEletronico.sacar(300, "456"));
		assertEquals(200, (int) conta.getSaldo());
	}

	@Test
	public void testMostrarSaldoInsuficiente() {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		assertEquals("Saldo insuficiente para realizar o saque!", caixaEletronico.sacar(600, "456"));
	}

	@Test
	public void testDepositar() {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		ContaCorrente conta = caixaEletronico.getServicoRemoto().recuperarConta("456");

		assertEquals("Depósito recebido com sucesso!", caixaEletronico.depositar(150, "456"));
		assertEquals(650, (int) conta.getSaldo());
	}

	@Test(expected = HardwareEntregaDinheiroException.class)
	public void testFalhaHardwareEntregarDinheiro() {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.getHardware().entregarDinheiro(null);
	}

	@Test(expected = HardwareEnvelopeException.class)
	public void testFalhaHardwareLerEnvelope() {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.getHardware().lerEnvelope(-100);
	}

	@Test(expected = HardwareCartaoException.class)
	public void testFalhaHardwarePagarNumeroCartao() {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.logar(null);
	}

}
