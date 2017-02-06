package tarefaprincipal;

public interface Hardware {

	public String pegarNumeroDaContaCartao(String numero, CaixaEletronico caixa);

	public void entregarDinheiro(String numero);
	
	public void lerEnvelope(double valor);
}
