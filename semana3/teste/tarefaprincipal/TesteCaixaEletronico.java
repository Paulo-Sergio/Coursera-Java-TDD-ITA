package tarefaprincipal;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCaixaEletronico {

	@Test
	public void exibirSaldoCaixaEletronico() {
		CaixaEletronico ce = new CaixaEletronico();
		ce.saldo();
	}

}
